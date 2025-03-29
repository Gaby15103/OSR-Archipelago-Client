package gg.archipelago.aprandomizer.customquest;

import dev.ftb.mods.ftblibrary.snbt.SNBT;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.customquest.quest.QuestChapter;
import gg.archipelago.aprandomizer.customquest.RewardTable.RewardTable;
import net.minecraft.nbt.CompoundTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CustomQuestManager {
    // Logger instance
    private static final Logger LOGGER = LogManager.getLogger();

    // Paths to the FTB Quests configuration
    private static final String FTB_SOURCE_FOLDER_STRING = APRandomizer.getServer().getServerDirectory() + "/config/ftbquests/quests";
    private static final String QUESTS_FOLDER_STRING = FTB_SOURCE_FOLDER_STRING + "/chapters";
    private static final String REWARD_TABLE_FOLDER_STRING = FTB_SOURCE_FOLDER_STRING + "/reward_tables";

    // Custom quest directory
    private static final String CUSTOM_QUEST_FOLDER_STRING = APRandomizer.getServer().getServerDirectory() + "/config/aprandomizer/customquests";
    private static final String CUSTOM_CHAPTER_FOLDER_STRING = CUSTOM_QUEST_FOLDER_STRING + "/chapters";
    private static final String CUSTOM_REWARD_TABLE_FOLDER_STRING = CUSTOM_QUEST_FOLDER_STRING + "/reward_tables";

    // ftbQuest list of files
    private static List<File> ftbQuestQuestFiles = new ArrayList<>();
    private static List<File> ftbQuestRewardTableFiles = new ArrayList<>();

    // custom list of files
    private static List<File> customQuestFiles = new ArrayList<>();
    private static List<File> customRewardTableFiles = new ArrayList<>();

    // Stores loaded quest chapters
    private static final Map<Long, QuestChapter> loadedQuestChapters = new HashMap<>();
    private static final Map<String, RewardTable> loadedRewardTables = new HashMap<String, RewardTable>();


    private CustomQuestManager() {
        throw new IllegalStateException("Utility class - instantiation not allowed.");
    }

    public static void init() {
        File questFolder = new File(QUESTS_FOLDER_STRING);
        LOGGER.info("FTB Quest Folder {}", questFolder.getPath());
        File rewardTableFolder = new File(REWARD_TABLE_FOLDER_STRING);
        LOGGER.info("FTB reward table folder {}", rewardTableFolder.getPath());
        File customQuestFolder = new File(CUSTOM_QUEST_FOLDER_STRING);
        File customChapterFolder = new File(CUSTOM_CHAPTER_FOLDER_STRING);
        File customRewardTableFolder = new File(CUSTOM_REWARD_TABLE_FOLDER_STRING);

        // Ensure custom quest directory and subdirectories exist
        if (!customQuestFolder.exists() && customQuestFolder.mkdirs()) {
            LOGGER.info("Created custom quest folder at: {}", CUSTOM_QUEST_FOLDER_STRING);
        }
        if (customQuestFolder.isDirectory()) {
            if (isDirEmpty(customQuestFolder.toPath())) {
                if (!customChapterFolder.exists() && customChapterFolder.mkdirs()) {
                    LOGGER.info("Created chapters folder at: {}", CUSTOM_CHAPTER_FOLDER_STRING);
                }
                if (!customRewardTableFolder.exists() && customRewardTableFolder.mkdirs()) {
                    LOGGER.info("Created reward tables folder at: {}", CUSTOM_REWARD_TABLE_FOLDER_STRING);
                }

            }
            // Load chapters quests files
            if (questFolder.exists() && questFolder.isDirectory()) {
                ftbQuestQuestFiles = listNBTFiles(questFolder);
                LOGGER.info("Loaded {} quest files from chapters.", ftbQuestQuestFiles.size());
            } else {
                LOGGER.error("FTB Quest chapters folder not found at: {}", QUESTS_FOLDER_STRING);
            }

            // Load reward table files
            if (rewardTableFolder.exists() && rewardTableFolder.isDirectory()) {
                ftbQuestRewardTableFiles = listNBTFiles(rewardTableFolder);
                LOGGER.info("Loaded {} reward table files.", ftbQuestRewardTableFiles.size());
            } else {
                LOGGER.error("FTB Quest reward table folder not found at: {}", REWARD_TABLE_FOLDER_STRING);
            }

            //add chapter files to custom directory
            if (customChapterFolder.isDirectory() &&
                    Objects.requireNonNull(customChapterFolder.listFiles()).length == 0
                    && !ftbQuestQuestFiles.isEmpty()) {
                LOGGER.info("Custom chapter folder is empty. Initializing folder...");
                customQuestFiles = ftbQuestQuestFiles;
                loadChapterFromFtb();
                saveChaptersToCustomFolder();

            } else if (customQuestFolder.isDirectory() &&
                    Objects.requireNonNull(customChapterFolder.listFiles()).length > 0) {
                LOGGER.info("Initializing default chapter quest...");
                customQuestFiles = List.of(Objects.requireNonNull(customChapterFolder.listFiles()));
                loadChapter();
            }

            //add rewards tables to custom directory
            if (customRewardTableFolder.isDirectory() &&
                    Objects.requireNonNull(customRewardTableFolder.listFiles()).length ==0
                    && !ftbQuestRewardTableFiles.isEmpty()){
                LOGGER.info("Custom reward table folder is empty. Initializing folder...");
                customRewardTableFiles = ftbQuestRewardTableFiles;
                loadRewardFromFtbTable();
                saveRewardTableToCustomFolder();
            } else if (customRewardTableFolder.isDirectory() &&
                    Objects.requireNonNull(customRewardTableFolder.listFiles()).length > 0) {
                LOGGER.info("Initializing default reward table...");
                customRewardTableFiles = List.of(Objects.requireNonNull(customRewardTableFolder.listFiles()));
                loadRewardTable();
            }
        }
    }
    public static void save(){
        saveChaptersToCustomFolder();
    }
    private static boolean isDirEmpty(final Path directory) {
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }catch (IOException e){
            LOGGER.error(e);
        }
        return false;
    }

    // List all NBT files in a directory
    private static List<File> listNBTFiles(File directory) {
        List<File> nbtFiles = new ArrayList<>();
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".snbt"));
        if (files != null) {
            nbtFiles.addAll(Arrays.asList(files));
        }
        return nbtFiles;
    }

    // Copy initial files to customquests directory if needed
    private static List<File> copyFilesToCustomFolder(List<File> sourceFiles, File destinationFolder) {
        if (!destinationFolder.exists() && destinationFolder.mkdirs()) {
            LOGGER.info("Created destination folder at: " + destinationFolder.getPath());
        }
        List<File> copiedfilesList = new ArrayList<>();
        for (File file : sourceFiles) {
            File destinationFile = new File(destinationFolder, file.getName());
            try (FileInputStream fis = new FileInputStream(file); FileOutputStream fos = new FileOutputStream(destinationFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                LOGGER.info("Copied: " + file.getName() + " to " + destinationFile.getPath());
                copiedfilesList.add(file);
            } catch (IOException e) {
                LOGGER.error("Error copying file: " + file.getName(), e);
            }
        }
        return copiedfilesList;
    }

    private static void loadChapterFromFtb(){
        LOGGER.info("loading chapters from ftb...");

        if (customQuestFiles != null){
            Long index = 0L;
            for (File chapterFile : customQuestFiles){
                SNBTCompoundTag snbtTag = SNBT.read(chapterFile.toPath());
                assert snbtTag != null;
                CompoundTag nbtData = snbtTag.copy();

                QuestChapter chapter = QuestChapter.fromFtbNbt(nbtData, index);
                index++;
                loadedQuestChapters.put(chapter.getChapterID(), chapter);

                LOGGER.info("Loaded chapter: {} with {} quests.", chapter.getChapterName(), chapter.getQuests().size());
            }
        }
    }
    private static void loadChapter(){
        LOGGER.info("loading chapters...");

        if (customQuestFiles != null){
            for (File chapterFile : customQuestFiles){
                SNBTCompoundTag snbtTag = SNBT.read(chapterFile.toPath());
                assert snbtTag != null;
                CompoundTag nbtData = snbtTag.copy();

                QuestChapter chapter = QuestChapter.fromNBT(nbtData);
                loadedQuestChapters.put(chapter.getChapterID(), chapter);

                LOGGER.info("Loaded chapter: {} with {} quests.", chapter.getChapterName(), chapter.getQuests().size());
            }
        }
    }
    private static void loadRewardFromFtbTable(){
        LOGGER.info("loading reward table...");
        if (customRewardTableFiles != null){
            for (File rewardTableFile : customRewardTableFiles){
                SNBTCompoundTag snbtTag = SNBT.read(rewardTableFile.toPath());
                assert snbtTag != null;
                CompoundTag nbtTag = snbtTag.copy();

                LOGGER.info("reward table file : {} \n compundTag : {}", rewardTableFile.getName(), nbtTag);
                RewardTable rewardTable = RewardTable.fromFtbNBT(nbtTag);
                loadedRewardTables.put(rewardTable.getId(), rewardTable);

                LOGGER.info("Loaded reward table: {} with {} rewards.", rewardTable.getTitle(), rewardTable.getRewards().size());
            }
        }
    }
    private static void loadRewardTable(){
        LOGGER.info("loading reward table...");
        if (customRewardTableFiles != null){
            for (File rewardTableFile : customRewardTableFiles){
                SNBTCompoundTag snbtTag = SNBT.read(rewardTableFile.toPath());
                assert snbtTag != null;
                CompoundTag nbtTag = snbtTag.copy();

                LOGGER.info("reward table file : {} \n compundTag : {}", rewardTableFile.getName(), nbtTag);
                RewardTable rewardTable = RewardTable.fromNBT(nbtTag);
                loadedRewardTables.put(rewardTable.getId(), rewardTable);

                LOGGER.info("Loaded reward table: {} with {} rewards.", rewardTable.getTitle(), rewardTable.getRewards().size());
            }
        }
    }

    private static void saveChaptersToCustomFolder() {
        LOGGER.info("Saving chapters to custom folder...");

        // Ensure the custom chapter folder exists
        File customChapterFolder = new File(CUSTOM_CHAPTER_FOLDER_STRING);
        if (!customChapterFolder.exists()) {
            if (customChapterFolder.mkdirs()) {
                LOGGER.info("Created custom chapter folder at: {}", CUSTOM_CHAPTER_FOLDER_STRING);
            } else {
                LOGGER.error("Failed to create custom chapter folder.");
                return;
            }
        }

        for (Map.Entry<Long, QuestChapter> entry : loadedQuestChapters.entrySet()) {
            QuestChapter chapter = entry.getValue();
            File chapterFile = new File(customChapterFolder, chapter.getChapterName() + ".snbt");

            try {
                CompoundTag nbtData = chapter.toNBT();

                Path path = chapterFile.toPath();
                if (SNBT.write(path, nbtData)) {
                    LOGGER.info("Saved chapter: {} to {}", chapter.getChapterName(), chapterFile.getPath());
                } else {
                    LOGGER.error("Failed to save chapter: {}", chapter.getChapterName());
                }
            } catch (Exception e) {
                LOGGER.error("Error saving chapter: " + chapter.getChapterName(), e);
            }
        }
    }

    private static void saveRewardTableToCustomFolder() {
        LOGGER.info("Saving rweward tables to custom folder...");

        File rewardTableFolder = new File(CUSTOM_REWARD_TABLE_FOLDER_STRING);
        if (!rewardTableFolder.exists()) {
            if (rewardTableFolder.mkdirs()) {
                LOGGER.info("Created custom reward tables folder at: {}", CUSTOM_REWARD_TABLE_FOLDER_STRING);
            } else {
                LOGGER.error("Failed to create custom reward table folder.");
                return;
            }
        }

        for (Map.Entry<String, RewardTable> entry : loadedRewardTables.entrySet()) {
            RewardTable rewardTable = entry.getValue();
            // Create the file path for each rewardTable
            File rewardTableFile = new File(rewardTableFolder, rewardTable.getTitle() + ".snbt");

            try {
                CompoundTag nbtData = rewardTable.toNBT();
                Path path = rewardTableFile.toPath();
                if (SNBT.write(path, nbtData)) {
                    LOGGER.info("Saved reward Table: {} to {}", rewardTable.getTitle(), rewardTableFile.getPath());
                } else {
                    LOGGER.error("Failed to save reward Table: {}", rewardTable.getTitle());
                }
            } catch (Exception e) {
                LOGGER.error("Error saving rewardTable: " + rewardTable.getTitle(), e);
            }
        }
    }
}

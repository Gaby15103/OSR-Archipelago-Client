package gg.archipelago.aprandomizer.customquest.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.customquest.quest.Quest;
import gg.archipelago.aprandomizer.customquest.quest.QuestChapter;
import gg.archipelago.aprandomizer.customquest.task.ItemTask;
import gg.archipelago.aprandomizer.customquest.task.Task;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class QuestChapterAdvancementGenerator {
    private final String ARCHIPELAGO_BASEADVANCEMENT_QUEST = APRandomizer.MODID+":archipelago/";
    private static final Logger LOGGER = LogManager.getLogger();
    private final List<QuestChapter> questChapters;
    private final Gson gson;
    private final String outputFolder = "src/main/resources/data/aprandomizer/advancements/";

    public QuestChapterAdvancementGenerator(List<QuestChapter> questChapters) {
        this.questChapters = questChapters;
        this.gson = new GsonBuilder().setPrettyPrinting().create();

    }

    // Main entry point to generate all advancements
    public void generateAdvancements() {
        for (QuestChapter chapter : questChapters) {
            createChapterAdvancement(chapter);
        }
    }

    public void createChapterAdvancement(QuestChapter chapter) {
        JsonObject advancementJson = new JsonObject();
        JsonObject displayJson = new JsonObject();
        JsonObject iconJson = new JsonObject();
        JsonObject criteriaJson = new JsonObject();
        JsonObject autoJson = new JsonObject();

        // Set the chapter icon or fallback to structure block
        String iconItem = chapter.getIcon().isEmpty() ?
                "minecraft:structure_block" :
                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(chapter.getIcon().getItem())).toString();

        // Display properties
        iconJson.addProperty("item", iconItem);
        displayJson.add("icon", iconJson);
        displayJson.addProperty("title", "Archipelago Quest :" + chapter.getChapterName());
        displayJson.addProperty("description", "Complete this chapter to progress.");
        displayJson.addProperty("background", "minecraft:textures/block/basalt_side.png");
        displayJson.addProperty("frame", "task");
        displayJson.addProperty("show_toast", false);
        displayJson.addProperty("announce_to_chat", false);
        displayJson.addProperty("hidden", false);

        // Criteria to auto-unlock the chapter using "tick" as a trigger
        autoJson.addProperty("trigger", "minecraft:tick");
        criteriaJson.add("auto", autoJson);

        // Add everything to the main JSON object
        advancementJson.add("display", displayJson);
        advancementJson.add("criteria", criteriaJson);

        // Generate file name: chapter_<chapter_name>.json
        String chapterFileName = "chapter_" + normalizeName(chapter.getChapterName());

        // Save to file
        saveToFile(chapterFileName, advancementJson);
        for (Quest quest : chapter.getQuests()){

            createQuestAdvancement(quest, chapter);
        }
    }

    private void createQuestAdvancement(Quest quest, QuestChapter chapter) {
        JsonObject questAdvancement = new JsonObject();
        JsonObject displayJson = new JsonObject();

        ItemStack icon = getIconForQuest(quest);
        displayJson.add("icon", getIcon(icon));
        displayJson.addProperty("title", quest.getTitle());
        displayJson.addProperty("description", quest.getDescription());
        displayJson.addProperty("frame", "task");
        displayJson.addProperty("show_toast", true);
        displayJson.addProperty("announce_to_chat", true);
        displayJson.addProperty("hidden", false);

        questAdvancement.add("display", displayJson);
        questAdvancement.addProperty("parent", getParentQuestLocation(quest, chapter));
        questAdvancement.add("criteria", impossibleCriteria());

        String questFileName = "quest_" + normalizeName(quest.getTitle());
        saveToFile(questFileName, questAdvancement);
    }

    private String getParentQuestLocation(Quest quest, QuestChapter chapter) {
        for (String dependencyId : quest.getDependencies()) {
            Quest dependencyQuest = findQuestById(dependencyId);
            if (dependencyQuest != null && chapter.has_quest(dependencyId)) {
                return ARCHIPELAGO_BASEADVANCEMENT_QUEST + "quest_" + normalizeName(dependencyQuest.getTitle());
            }
        }

        return ARCHIPELAGO_BASEADVANCEMENT_QUEST + "chapter_" + normalizeName(chapter.getChapterName());
    }

    private Quest findQuestById(String questId) {
        for (QuestChapter chapter : questChapters) {
            for (Quest quest : chapter.getQuests()) {
                if (Objects.equals(quest.getId(), questId)) {
                    return quest;
                }
            }
        }
        return null;
    }

    private JsonObject getIcon(ItemStack icon) {
        JsonObject iconJson = new JsonObject();
        String itemName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(icon.getItem())).toString();
        iconJson.addProperty("item", itemName != null ? itemName : "minecraft:book");
        return iconJson;
    }

    private JsonObject impossibleCriteria() {
        JsonObject criteria = new JsonObject();
        JsonObject impossible = new JsonObject();
        impossible.addProperty("trigger", "minecraft:impossible");
        criteria.add("impossible", impossible);
        return criteria;
    }

    private void saveToFile(String fileName, JsonObject advancementJson) {
        File directory = new File(outputFolder);

        // Create directories if they don't exist
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                LOGGER.info("Created directories: {}", directory.getAbsolutePath());
            } else {
                LOGGER.error("Failed to create directories for: {}", directory.getAbsolutePath());
                return; // Stop if we can't create the directories
            }
        }

        // Define file path
        File file = new File(outputFolder + fileName + ".json");

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(advancementJson, writer);
            LOGGER.info("Saved advancement: {}", fileName);
        } catch (IOException e) {
            LOGGER.error("Failed to save advancement: {}", fileName, e);
        }
    }


    private String normalizeName(String name) {
        return name.toLowerCase()
                .replace(" ", "_")
                .replaceAll("[^a-z0-9._-]", "")   // Remove any invalid characters
                .replace("/", "_");  // Explicitly replace forward slash with an underscore
    }

    private ItemStack getIconForQuest(Quest quest) {
        if (!quest.getTasks().isEmpty()) {
            Task firstTask = quest.getTasks().get(0);
            if (firstTask instanceof ItemTask task) {
                return task.getItem();
            }
        }

        return new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:book")))); // Default icon
    }
}


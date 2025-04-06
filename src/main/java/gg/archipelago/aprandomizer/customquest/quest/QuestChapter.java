package gg.archipelago.aprandomizer.customquest.quest;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestChapter {
    // directly reference a log4j logger.
    private static  final Logger LOGGER = LogManager.getLogger();
    private final String chapterName;
    private ItemStack icon;
    private Long chapterID;
    private ChapterBackgroundConfig backgroud;
    private int orderIndex;
    private List<Quest> quests;
    private boolean completed;

    public QuestChapter(String chapterName, ItemStack icon, Long chapterID, ChapterBackgroundConfig background,
                        int orderIndex, List<Quest> quests) {
        this.chapterName = chapterName;
        this.icon = icon;
        this.chapterID = chapterID;
        this.backgroud = background;
        this.orderIndex = orderIndex;
        this.quests = quests;
        this.completed = false;
    }
    public QuestChapter(String chapterName, ItemStack icon, Long chapterID, ChapterBackgroundConfig background,
                        int orderIndex, List<Quest> quests, boolean completed) {
        this.chapterName = chapterName;
        this.icon = icon;
        this.chapterID = chapterID;
        this.backgroud = background;
        this.orderIndex = orderIndex;
        this.quests = quests;
        this.completed = completed;
    }

    public String getChapterName() {
        return chapterName;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public Long getChapterID() {
        return chapterID;
    }

    public void setChapterID(Long chapterID) {
        this.chapterID = chapterID;
    }

    public ChapterBackgroundConfig getBackground() {
        return backgroud;
    }

    public void setBackground(ChapterBackgroundConfig background) {
        this.backgroud = background;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Serialize this chapter to NBT
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("title", chapterName);
        tag.put("icon", icon.save(new CompoundTag())); // Save icon as NBT
        tag.putLong("chapter_id", chapterID);
        tag.put("background", backgroud.toNBT()); // Assuming ChapterBackgroundConfig has its own NBT serialization
        tag.putInt("order_index", orderIndex);
        // Serialize the list of quests here
        // Assuming Quest class has a toNBT method for serialization
        ListTag questsTag = new ListTag();
        for (Quest quest : quests ){
            questsTag.add(quest.toNBT());
        }
        tag.put("quests", questsTag);
        tag.putBoolean("completed", completed);

        return tag;
    }
    public static QuestChapter fromFtbNbt(CompoundTag tag, Long id){
        String chapterName = tag.getString("title");
        CompoundTag iconTag = tag.getCompound("icon");
        ItemStack icon = null;
        if (iconTag.isEmpty()){
            String itemString = tag.getString("icon");
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
            assert item != null;
            icon = new ItemStack(item, 1);
        }else{
            icon = ItemStack.of(tag.getCompound("icon"));
        }
        ChapterBackgroundConfig background = null;
        if (tag.contains("images")) {
            ListTag imagesTag = tag.getList("images", 10);
            if (!imagesTag.isEmpty()) {
                CompoundTag imageTag = imagesTag.getCompound(0);
                background = ChapterBackgroundConfig.fromNBT(imageTag);
            }
        }
        int orderIndex = tag.getInt("order_index");

        // Deserialize the quests
        List<Quest> quests = new ArrayList<>();
        ListTag questsTag = tag.getList("quests", 10); // 10 represents CompoundTag type

        if (!questsTag.isEmpty()){
            for (Tag questTag : questsTag) {
                if (questTag instanceof CompoundTag compoundTag) {
                    Quest quest = Quest.fromFtbNBT(compoundTag, id);
                    quests.add(quest);
                }
            }
        }

        return new QuestChapter(chapterName, icon, id, background, orderIndex, quests);
    }

    // Deserialize from NBT
    public static QuestChapter fromNBT(CompoundTag tag, Long id) {
        String chapterName = tag.getString("title");
        CompoundTag iconTag = tag.getCompound("icon");
        ItemStack icon = null;
        if (iconTag.isEmpty()){
            String itemString = tag.getString("icon");
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
            assert item != null;
            icon = new ItemStack(item, 1);
        }else{
            icon = ItemStack.of(tag.getCompound("icon"));
        }
        Long chapterId = tag.getLong("chapter_id");
        ChapterBackgroundConfig background = ChapterBackgroundConfig.fromNBT(tag.getCompound("background"));
        int orderIndex = tag.getInt("order_index");

        // Deserialize the quests
        List<Quest> quests = new ArrayList<>();
        ListTag questsTag = tag.getList("quests", 10); // 10 represents CompoundTag type

        if (!questsTag.isEmpty()){
            for (Tag questTag : questsTag) {
                if (questTag instanceof CompoundTag compoundTag) {
                    Quest quest = Quest.fromNBT(compoundTag, id);
                    quests.add(quest);
                }
            }
        }
        boolean completed = tag.getBoolean("completed");

        return new QuestChapter(chapterName, icon, chapterId, background, orderIndex, quests, completed);
    }

    public boolean has_quest(String questId) {
        for (Quest quest : this.quests){
            if (Objects.equals(quest.getId(), questId)){
                LOGGER.info("has quest ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                return true;
            }
        }
        return false;
    }

    public Quest getQuestById(String dependencyId) {
        for (Quest quest : this.quests){
            if (Objects.equals(dependencyId, quest.getId())){
                return quest;
            }
        }
        return null;
    }
}

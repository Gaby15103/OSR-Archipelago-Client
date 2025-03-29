package gg.archipelago.aprandomizer.customquest.quest;

import gg.archipelago.aprandomizer.customquest.reward.*;
import gg.archipelago.aprandomizer.customquest.task.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quest {
    // directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private List<String> dependencies;
    private String title;
    private String subTitle;
    private String description;
    private String id;
    private List<Reward> rewards;
    private List<Task> tasks;
    private double x;
    private double y;

    // Constructor to initialize all properties
    public Quest(List<String> dependencies, String title, String subTitle, String description, String id,
                 List<Reward> rewards, List<Task> tasks, double x, double y) {
        this.dependencies = dependencies;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.id = id;
        this.rewards = rewards;
        this.tasks = tasks;
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Serialize the Quest to NBT
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();

        // Store simple values
        tag.putString("title", title);
        if (subTitle != null) {
            tag.putString("subtitle", subTitle);
        }
        tag.putString("description", description);
        tag.putString("id", id);
        tag.putDouble("x", x);
        tag.putDouble("y", y);

        ListTag dependenciesTag = new ListTag();
        for (String dependency : dependencies) {
            dependenciesTag.add(StringTag.valueOf(dependency)); // Add each dependency as a string tag
        }
        tag.put("dependencies", dependenciesTag);

        // Serialize rewards
        ListTag rewardsTag = new ListTag();
        for (Reward reward : rewards) {
            rewardsTag.add(reward.toNBT());
        }
        tag.put("rewards", rewardsTag);

        // Serialize tasks
        ListTag tasksTag = new ListTag();
        for (Task task : tasks) {
            tasksTag.add(task.toNBT());
        }
        tag.put("tasks", tasksTag);

        return tag;
    }

    // Deserialize the Quest from NBT
    public static Quest fromFtbNBT(CompoundTag tag) {
        String subTitle = tag.contains("subtitle") ? tag.getString("subtitle") : null;
        ListTag descriptionTag = tag.contains("description") ? tag.getList("description", 8) : null;
        StringBuilder description = new StringBuilder();
        if (descriptionTag != null && !descriptionTag.isEmpty()) {
            for (int i = 0; i < descriptionTag.size(); i++) {
                if (i == 0) {
                    description.append(descriptionTag.getString(i));
                } else {
                    description.append("\n").append(descriptionTag.getString(i));
                }
            }
        }


        String questId = tag.getString("id");
        double x = tag.getDouble("x");
        double y = tag.getDouble("y");

        ListTag dependenciesTag = tag.getList("dependencies", 8);
        List<String> dependencies = new ArrayList<>();
        for (int i = 0; i < dependenciesTag.size(); i++) {
            dependencies.add(dependenciesTag.getString(i));
        }

        // Deserialize rewards
        List<Reward> rewards = new ArrayList<>();
        ListTag rewardsTag = tag.getList("rewards", 10);

        for (int i = 0; i < rewardsTag.size(); i++) {
            CompoundTag rewardTag = rewardsTag.getCompound(i);
            String typeString = rewardTag.getString("type");
            RewardType type = RewardType.valueOf(typeString.toUpperCase());

            Reward reward = null;
            switch (type) {
                case ITEM:
                    reward = ItemReward.fromFtbNBT(rewardTag);
                    break;
                case LOOT, RANDOM:
                    reward = LootReward.fromFtbNBT(rewardTag, LootType.RANDOM);
                    break;
                case CHOICE:
                    reward = LootReward.fromFtbNBT(rewardTag, LootType.CHOICE);
                    break;
                case EXPERIENCE:
                    reward = ExperienceReward.fromFtbNBT(rewardTag);
                    break;
                default:
                    break;
            }

            if (reward != null) {
                rewards.add(reward);
            }
        }

        // Deserialize tasks
        List<Task> tasks = new ArrayList<>();
        ListTag tasksTag = tag.getList("tasks", 10); // Get list of compounds

        for (int i = 0; i < tasksTag.size(); i++) {
            CompoundTag taskTag = tasksTag.getCompound(i); // Get each task compound tag
            String typeString = taskTag.getString("type"); // Get the task type from the individual task tag
            TaskType type = TaskType.valueOf(typeString.toUpperCase());
            // other than item task all other will not be implemented because in the current modpacks they are not used
            Task task = null;
            switch (type) {
                case ITEM:
                    task = ItemTask.fromFtbNBT(taskTag);
                    break;
                case XP_LEVELS:
                    task = XPLevelTask.fromNBT(taskTag);
                    break;
                case VISIT_DIMENSION:
                    task = VisitDimensionTask.fromNBT(taskTag);
                    break;
                case KILL_ENTITY:
                    task = KillEntityTask.fromNBT(taskTag);
                    break;
                case CHECKMARK:
                    task = CheckmarkTask.fromNBT(taskTag);
                    break;
                case ADVANCEMENT:
                    task = AdvancementTask.fromNBT(taskTag);
                    break;
                case FLUID:
                    task = FluidTask.fromNBT(taskTag);
                    break;
                case FORGE_ENERGY:
                    task = ForgeEnergyTask.fromNBT(taskTag);
                    break;
                default:
                    break;
            }

            if (task != null) {
                tasks.add(task);
            }
        }
        String title = tag.contains("title") ? tag.getString("title") :
                getFirstTaskItemNameAsTitle(tasks);

        // Return the deserialized Quest
        return new Quest(dependencies, title, subTitle, description.toString(), questId, rewards, tasks, x, y);
    }

    private static String getFirstTaskItemNameAsTitle(List<Task> tasks) {
        ItemTask itemTask = null;
        for (Task task : tasks) {
            if (task instanceof ItemTask itemTask1) {
                itemTask = itemTask1;
                break;
            } else if (task instanceof CheckmarkTask checkmarkTask) {
                return checkmarkTask.getTitle();
            }
        }
        if (itemTask != null) {
            ItemStack item = itemTask.getItem();
            return item.getHoverName().getString();
        } else {
            return "";
        }
    }

    // Deserialize the Quest from NBT
    public static Quest fromNBT(CompoundTag tag) {
        String title = tag.getString("title");
        String subTitle = tag.contains("subtitle") ? tag.getString("subtitle") : null;
        String description = tag.getString("description");
        String questId = tag.getString("questId");
        double x = tag.getDouble("x");
        double y = tag.getDouble("y");

        // Deserialize dependencies
        List<String> dependencies = new ArrayList<>();
        CompoundTag dependenciesTag = tag.getCompound("dependencies");
        for (String key : dependenciesTag.getAllKeys()) {
            dependencies.add(dependenciesTag.getString(key));
        }

        // Deserialize rewards
        List<Reward> rewards = new ArrayList<>();
        CompoundTag rewardsTag = tag.getCompound("rewards");

        for (String key : rewardsTag.getAllKeys()) {
            CompoundTag rewardTag = rewardsTag.getCompound(key);
            String typeString = rewardTag.getString("type");
            RewardType type = RewardType.valueOf(typeString.toUpperCase());

            Reward reward = null;
            switch (type) {
                case ITEM:
                    reward = ItemReward.fromNBT(rewardTag);
                    break;
                case LOOT, RANDOM:
                    reward = LootReward.fromNBT(rewardTag, LootType.RANDOM);
                    break;
                case CHOICE:
                    reward = LootReward.fromNBT(rewardTag, LootType.CHOICE);
                    break;
                case EXPERIENCE:
                    reward = ExperienceReward.fromNBT(rewardTag);
                    break;
                default:
                    break;
            }

            if (reward != null) {
                rewards.add(reward);
            }
        }
        // Deserialize rewards
        List<Task> tasks = new ArrayList<>();
        CompoundTag tasksTag = tag.getCompound("tasks");

        for (String key : tasksTag.getAllKeys()) {
            CompoundTag taskTag = tasksTag.getCompound(key);
            String typeString = tasksTag.getString("type");
            TaskType type = TaskType.valueOf(typeString.toUpperCase());

            Task task = null;
            switch (type) {
                case ITEM:
                    task = ItemTask.fromNBT(taskTag);
                    break;
                case XP_LEVELS:
                    task = XPLevelTask.fromNBT(taskTag);
                    break;
                case VISIT_DIMENSION:
                    task = VisitDimensionTask.fromNBT(taskTag);
                    break;
                case KILL_ENTITY:
                    task = KillEntityTask.fromNBT(taskTag);
                    break;
                case CHECKMARK:
                    task = CheckmarkTask.fromNBT(taskTag);
                    break;
                case ADVANCEMENT:
                    task = AdvancementTask.fromNBT(taskTag);
                    break;
                case FLUID:
                    task = FluidTask.fromNBT(taskTag);
                    break;
                case FORGE_ENERGY:
                    task = ForgeEnergyTask.fromNBT(taskTag);
                    break;
                default:
                    break;
            }

            if (task != null) {
                tasks.add(task);
            }
        }
        // Return the deserialized Quest
        return new Quest(dependencies, title, subTitle, description, questId, rewards, tasks, x, y);
    }
}

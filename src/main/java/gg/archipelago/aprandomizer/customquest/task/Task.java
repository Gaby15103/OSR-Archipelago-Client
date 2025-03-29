package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;

public abstract class Task {
    private String id;
    private TaskType type;
    public Task(String ID, TaskType type) {
        this.id = ID;
        this.type = type;
    }

    public abstract CompoundTag toNBT(); // Each subclass must implement this

    public String getId() {
        return id;
    }

    public TaskType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(TaskType type) {
        this.type = type;
    }
}

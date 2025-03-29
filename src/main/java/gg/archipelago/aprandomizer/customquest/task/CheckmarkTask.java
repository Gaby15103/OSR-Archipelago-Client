package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;

public class CheckmarkTask extends Task{
    private String title;
    public CheckmarkTask(String ID, String title) {
        super(ID, TaskType.CHECKMARK);
        this.title = title;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();  // Save common fields
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putString("title", this.title);
        return tag;
    }
    public static CheckmarkTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");// Retrieve the dimension
        String title = tag.getString("title");
        return new CheckmarkTask(id, title);
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
}

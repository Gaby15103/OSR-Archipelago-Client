package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;

public class XPLevelTask extends Task{
    private int requiredXP;

    public XPLevelTask(String ID, int requiredXP) {
        super(ID, TaskType.XP_LEVELS);
        this.requiredXP = requiredXP;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();  // Save common fields
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putInt("requiredXP", requiredXP);  // Save required XP
        return tag;
    }

    public static XPLevelTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        int requiredXP = tag.getInt("requiredXP");  // Load required XP
        return new XPLevelTask(id,requiredXP);
    }

    public int getRequiredXP() {
        return requiredXP;
    }

    public void setRequiredXP(int requiredXP) {
        this.requiredXP = requiredXP;
    }
}

package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;

public class ForgeEnergyTask extends Task {
    private long energyAmount;  // Store the energy amount

    public ForgeEnergyTask(String ID, long energyAmount) {
        super(ID, TaskType.FORGE_ENERGY);
        this.energyAmount = energyAmount;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();  // Save common fields
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putLong("energy_amount", energyAmount);  // Store the energy amount
        return tag;
    }

    public static ForgeEnergyTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        long energyAmount = tag.getLong("energy_amount");  // Retrieve the energy amount
        return new ForgeEnergyTask(id,energyAmount);
    }

    public long getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(long energyAmount) {
        this.energyAmount = energyAmount;
    }
}
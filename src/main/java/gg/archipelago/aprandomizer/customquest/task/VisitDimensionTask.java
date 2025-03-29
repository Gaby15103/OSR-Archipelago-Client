package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class VisitDimensionTask extends Task {
    private ResourceLocation dimensionId;

    public VisitDimensionTask(String ID, ResourceLocation dimensionId) {
        super(ID, TaskType.VISIT_DIMENSION);
        this.dimensionId = dimensionId;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();  // Save common fields
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putString("dimension", dimensionId.toString());  // Store the dimension
        return tag;
    }

    public static VisitDimensionTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        String dimensionName = tag.getString("dimension");
        ResourceLocation dimensionId = new ResourceLocation(dimensionName);  // Retrieve the dimension
        return new VisitDimensionTask(id , dimensionId);
    }

    public ResourceLocation getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(ResourceLocation dimensionId) {
        this.dimensionId = dimensionId;
    }
}
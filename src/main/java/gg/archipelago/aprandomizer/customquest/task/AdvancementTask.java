package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class AdvancementTask extends Task {
    private ResourceLocation advancement;

    public AdvancementTask(String ID, ResourceLocation advancement) {
        super(ID, TaskType.ADVANCEMENT);
        this.advancement = advancement;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();  // Save common fields
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putString("advancement", advancement.toString());  // Save advancement ID
        return tag;
    }

    public static AdvancementTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        ResourceLocation advancement = new ResourceLocation(tag.getString("advancement"));  // Load advancement ID
        return new AdvancementTask(id, advancement);
    }

    public ResourceLocation getAdvancement() {
        return advancement;
    }

    public void setAdvancement(ResourceLocation advancement) {
        this.advancement = advancement;
    }
}
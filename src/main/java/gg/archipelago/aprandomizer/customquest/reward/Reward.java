package gg.archipelago.aprandomizer.customquest.reward;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.lang.Long;
public abstract class Reward {
    private String id;
    private RewardType type;

    public Reward(String id, RewardType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }
    public abstract CompoundTag toNBT(); // Each subclass must implement this
}

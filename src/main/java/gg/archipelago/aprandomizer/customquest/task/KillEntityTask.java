package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;

public class KillEntityTask extends Task {
    private EntityType<?> entityType;

    public KillEntityTask(String ID, EntityType<?> entityType) {
        super(ID, TaskType.KILL_ENTITY);
        this.entityType = entityType;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();  // Save common fields
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putString("entityType", entityType.toString());
        return tag;
    }

    public static KillEntityTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        EntityType<?> entityType = EntityType.byString(tag.getString("entityType")).orElse(EntityType.ZOMBIE);  // Load entity type
        return new KillEntityTask(id, entityType);
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType<?> entityType) {
        this.entityType = entityType;
    }
}
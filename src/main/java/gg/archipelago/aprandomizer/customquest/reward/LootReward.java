package gg.archipelago.aprandomizer.customquest.reward;

import net.minecraft.nbt.CompoundTag;

public class LootReward extends Reward {
    private String lootTableId;
    private LootType type;

    public LootReward(String ID, String lootTableId, LootType type) {
        super(ID, RewardType.LOOT);
        this.lootTableId = lootTableId;
        this.type = type;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putString("table_id", lootTableId);
        return tag;
    }

    public static LootReward fromFtbNBT(CompoundTag tag, LootType type) {
        String id = tag.getString("id");
        String lootTable = String.format("%016X", tag.getLong("table_id"));
        return new LootReward(id, lootTable, type);
    }

    public static LootReward fromNBT(CompoundTag tag, LootType type) {
        String id = tag.getString("id");
        String lootTable = tag.getString("table_id");
        return new LootReward(id, lootTable, type);
    }

    public String getLootTable() {
        return lootTableId;
    }

    public void setLootTable(String lootTableId) {
        this.lootTableId = lootTableId;
    }
    public LootType getLootType(){
        return this.type;
    }
    public void setLootType(LootType type){
       this.type = type;
    }
}

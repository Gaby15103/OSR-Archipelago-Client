package gg.archipelago.aprandomizer.customquest.RewardTable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class RewardTableReward {
    private ItemStack item;
    public RewardTableReward(ItemStack item) {
        this.item = item;
    }

    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("item", item.save(new CompoundTag()));
        return tag;
    }

    public static RewardTableReward fromFtbNBT(CompoundTag tag) {
        CompoundTag itemTag = tag.getCompound("item");
        ItemStack itemStack = null;
        if (itemTag.isEmpty()){
            String itemString = tag.getString("item");
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
            assert item != null;
            itemStack = new ItemStack(item, 1);
        }else{
            itemStack = ItemStack.of(tag.getCompound("item"));
        }
        return new RewardTableReward(itemStack);
    }

    public static RewardTableReward fromNBT(CompoundTag tag) {
        ItemStack item = ItemStack.of(tag.getCompound("item"));
        return new RewardTableReward(item);
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}

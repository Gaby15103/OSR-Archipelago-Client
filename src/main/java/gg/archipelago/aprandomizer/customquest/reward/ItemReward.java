package gg.archipelago.aprandomizer.customquest.reward;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.Long;

public class ItemReward extends Reward{
    private ItemStack item;
    public ItemReward(String id, ItemStack item) {
        super(id, RewardType.ITEM);
        this.item = item;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.put("item", item.save(new CompoundTag())); // Save item stack to NBT
        return tag;
    }

    public static ItemReward fromFtbNBT(CompoundTag tag) {
        String id = tag.getString("id");
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
        return new ItemReward(id, itemStack);
    }

    public static ItemReward fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        ItemStack item = ItemStack.of(tag.getCompound("item"));
        return new ItemReward(id, item);
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}

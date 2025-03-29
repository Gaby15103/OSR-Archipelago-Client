package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ItemTask extends Task{
    // directly reference a log4j logger.
    private static  final Logger LOGGER = LogManager.getLogger();
    private ItemStack item;
    public ItemTask(String ID, ItemStack item) {
        super(ID, TaskType.ITEM);
        this.item = item;
    }
    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", getId());
        tag.put("item", item.save(new CompoundTag()));  // Save item stack
        tag.putString("type", getType().name());
        return tag;
    }

    public static ItemTask fromFtbNBT(CompoundTag tag) {
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
          // Load item stack
        return new ItemTask(id, itemStack);
    }

    public static ItemTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        ItemStack item = ItemStack.of(tag.getCompound("item"));
        if (item.isEmpty()) {
            item = ItemStack.EMPTY;
        }
        return new ItemTask(id, item);
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}

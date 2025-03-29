package gg.archipelago.aprandomizer.customquest.RewardTable;

import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.lang.Long;

public class RewardTable {
    private String title;
    private ItemStack icon;
    private String id;
    private Long junkItemId;
    private List<RewardTableReward> rewards;

    public RewardTable(String title, ItemStack icon, String id, Long junkItemId, List<RewardTableReward> rewards){
        this.title = title;
        this.icon = icon;
        this.id = id;
        this.junkItemId = junkItemId;
        this.rewards = rewards;
    }
    public RewardTable(String title, ItemStack icon, String id, List<RewardTableReward> rewards){
        this.title = title;
        this.icon = icon;
        this.id = id;
        this.junkItemId = 0L;
        this.rewards = rewards;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ItemStack getIcon(){
        return this.icon;
    }
    public void setIcon(ItemStack icon){
        this.icon = icon;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public Long getJunkItemId(){
        return this.junkItemId;
    }
    public void setJunkItemId(Long junkItemId){
        this.junkItemId = junkItemId;
    }
    public List<RewardTableReward> getRewards(){
        return this.rewards;
    }
    public void setRewards(List<RewardTableReward> rewards){
        this.rewards = rewards;
    }

    // Serialize this rewardTable to NBT
    public CompoundTag toNBT(){
        CompoundTag tag = new CompoundTag();
        tag.putString("title", this.title);
        tag.putString("id", this.id);
        tag.put("icon", icon.save(new CompoundTag()));
        tag.putLong("junkItemId", this.junkItemId);
        ListTag rewardTag = new ListTag();
        for(RewardTableReward reward : rewards){
            rewardTag.add(reward.toNBT());
        }
        tag.put("rewards", rewardTag);

        return tag;
    }

    public static RewardTable fromNBT(CompoundTag tag){
        String title = tag.getString("title");
        String id = tag.getString("id");
        CompoundTag iconTag = tag.getCompound("icon");
        ItemStack icon = null;
        if (iconTag.isEmpty()){
            String itemString = tag.getString("icon");
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
            assert item != null;
            icon = new ItemStack(item, 1);
        }else{
            icon = ItemStack.of(tag.getCompound("icon"));
        }
        Long junkItemId = tag.getLong("junkItemId");
        List<RewardTableReward> rewards = new ArrayList<>();
        ListTag rewardsTag = tag.getList("rewards", 10);
        for (int i = 0; i < rewardsTag.size(); i++) {
            rewards.add(RewardTableReward.fromNBT(rewardsTag.getCompound(i)));
        }
        return new RewardTable(title, icon,id,junkItemId,rewards);
    }
    public static RewardTable fromFtbNBT(CompoundTag tag){
        String title = tag.getString("title");
        String id = tag.getString("id");
        CompoundTag iconTag = tag.getCompound("icon");
        ItemStack icon = null;
        if (iconTag.isEmpty()){
            String itemString = tag.getString("icon");
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
            assert item != null;
            icon = new ItemStack(item, 1);
        }else{
            icon = ItemStack.of(tag.getCompound("icon"));
        }
        List<RewardTableReward> rewards = new ArrayList<>();
        ListTag rewardsTag = tag.getList("rewards", 10);
        for (int i = 0; i < rewardsTag.size(); i++) {
            rewards.add(RewardTableReward.fromFtbNBT(rewardsTag.getCompound(i)));
        }
        return new RewardTable(title, icon,id,rewards);
    }
}

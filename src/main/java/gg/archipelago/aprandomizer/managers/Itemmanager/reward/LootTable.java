package gg.archipelago.aprandomizer.managers.Itemmanager.reward;

import gg.archipelago.aprandomizer.common.Utils.Utils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LootTable extends HashMap<ItemStack, Double> {
    private static final Random random = new Random();
    public LootTable() {
        super();
    }

    public void award(ServerPlayer player) {
        ItemStack rewardItem = getRandomRewardItem();
        if (rewardItem != null) {
            // Assuming you have a method to give an item to the player
            Utils.giveItemToPlayer(player, rewardItem);
        }
    }

    // Method to get a random reward based on weights
    public ItemStack getRandomRewardItem() {
        if (this.isEmpty()) {
            return null;
        }

        List<ItemStack> rewardList = new ArrayList<>();
        List<Double> weightList = new ArrayList<>();
        double totalWeight = 0;

        for (Map.Entry<ItemStack, Double> entry : this.entrySet()) {
            totalWeight += entry.getValue();
            weightList.add(totalWeight);
            rewardList.add(entry.getKey());
        }

        double randomWeight = random.nextDouble() * totalWeight;

        for (int i = 0; i < weightList.size(); i++) {
            if (randomWeight <= weightList.get(i)) {
                return rewardList.get(i);
            }
        }

        return rewardList.get(rewardList.size() - 1);
    }
}

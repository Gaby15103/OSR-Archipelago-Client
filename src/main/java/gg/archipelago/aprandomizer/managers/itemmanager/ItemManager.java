package gg.archipelago.aprandomizer.managers.itemmanager;

import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.reward.RewardType;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.APStructures;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.managers.itemmanager.reward.CustomReward;
import gg.archipelago.aprandomizer.managers.itemmanager.traps.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Callable;

public class ItemManager {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final long DRAGON_EGG_SHARD = 45043L;

    private final HashMap<Long, ItemStack> itemStacks = new HashMap<>() {{
        //ADD mysterious cube as reward
        put(45052L, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ae2", "mysterious_cube"))), 1));
    }};

    private final HashMap<Long,TagKey<Structure>> compasses = new HashMap<>() {{
        put(45063L, APStructures.VILLAGE_TAG);
        put(45064L, APStructures.OUTPOST_TAG);
        put(45065L, APStructures.FORTRESS_TAG);
        put(45066L, APStructures.BASTION_REMNANT_TAG);
        put(45067L, APStructures.END_CITY_TAG);

    }};

    long index = 45100L;
    private final HashMap<Long, Callable<Trap>> trapData = new HashMap<>() {{
        put(index++, BeeTrap::new);
        put(index++, CreeperTrap::new);
        put(index++, SandRain::new);
        put(index++, FakeWither::new);
        put(index++, GoonTrap::new);
        put(index++, FishFountainTrap::new);
        put(index++, MiningFatigueTrap::new);
        put(index++, BlindnessTrap::new);
        put(index++, PhantomTrap::new);
        put(index++, WaterTrap::new);
        put(index++, GhastTrap::new);
        put(index++, LevitateTrap::new);
        put(index++, AboutFaceTrap::new);
        put(index++, AnvilTrap::new);
    }};

    private ArrayList<Long> receivedItems = new ArrayList<>();

    private final ArrayList<TagKey<Structure>> receivedCompasses = new ArrayList<>();

    private void makeCompass(ItemStack iStack, TagKey<Structure> structureTag) {
        CompoundTag nbt = iStack.getOrCreateTag();
        nbt.put("structure", StringTag.valueOf(structureTag.location().toString()));

        BlockPos structureCords = new BlockPos(0,0,0);
        Utils.addLodestoneTags(Utils.getStructureWorld(structureTag),structureCords, iStack.getOrCreateTag());
        Utils.setItemName(iStack, "uninitialized structure compass");
        Utils.setItemLore(iStack, new ArrayList<>(){{
            add("oops, this compass is broken.");
            add("right click with compass in hand to fix.");
            add("hopefully it will point to the right place.");
            add("no guarantees.");
        }});
    }

    public void setReceivedItems(ArrayList<Long> items) {
        this.receivedItems = items;
        for (var item : items) {
            if(compasses.containsKey(item) && !receivedCompasses.contains(compasses.get(item))) {
                receivedCompasses.add(compasses.get(item));
            }
        }
        APRandomizer.getGoalManager().updateGoal(false);
    }

    public void giveItem(Long itemID, ServerPlayer player, int itemIndex) {
        if (APRandomizer.isJailPlayers()) {
            //dont send items to players if game has not started.
            return;
        }

        if (APRandomizer.getWorldData().getPlayerIndex(player.getStringUUID()) >= itemIndex) return;

        //update the player's index of received items for syncing later.
        APRandomizer.getWorldData().updatePlayerIndex(player.getStringUUID(),itemIndex);

        if (itemStacks.containsKey(itemID)) {
            ItemStack itemstack = itemStacks.get(itemID).copy();
            if(compasses.containsKey(itemID)){

                TagKey<Structure> tag = compasses.get(itemID);
                updateCompassLocation(tag, player , itemstack);
            }
            Utils.giveItemToPlayer(player, itemstack);
        } else if (trapData.containsKey(itemID)) {
            try {
                trapData.get(itemID).call().trigger(player);
            } catch (Exception ignored) {
            }
        } else if (CustomReward.hasLootReward(itemID)){
            CustomReward.getLootReward(itemID).award(player);
        }
    }


    public void giveItemToAll(long itemID, int index) {

        receivedItems.add(itemID);
        //check if this item is a structure compass, and we are not already tracking that one.
        if(compasses.containsKey(itemID) && !receivedCompasses.contains(compasses.get(itemID))) {
            receivedCompasses.add(compasses.get(itemID));
        }

        APRandomizer.getServer().execute(() -> {
            for (ServerPlayer serverplayerentity : APRandomizer.getTeamHelper().getTeam().getOnlineMembers()) {
                giveItem(itemID, serverplayerentity, index);
            }
        });

        APRandomizer.getGoalManager().updateGoal(true);
    }

    /***
     fetches the index form the player's capability then makes sure they have all items after that index.
     * @param player ServerPlayer to catch up
     */
    public void catchUpPlayer(ServerPlayer player) {
        if (APRandomizer.getTeamHelper().getTeam().getOnlineMembers().contains(player)){
            int playerIndex = APRandomizer.getWorldData().getPlayerIndex(player.getStringUUID());

            for (int i = playerIndex; i < receivedItems.size(); i++) {
                giveItem(receivedItems.get(i), player, i+1);
            }
        }
    }

    public ArrayList<TagKey<Structure>> getCompasses() {
        return receivedCompasses;
    }

    public ArrayList<Long> getAllItems() {
        return receivedItems;
    }

    public static void updateCompassLocation(TagKey<Structure> structureTag, Player player, ItemStack compass) {

        //get the actual structure data from forge, and make sure its changed to the AP one if needed.

        //get our local custom structure if needed.
        ResourceKey<Level> world = Utils.getStructureWorld(structureTag);

        //only locate structure if the player is in the same world as the one for the compass
        //otherwise just point it to 0,0 in said dimension.
        BlockPos structurePos = new BlockPos(0,0,0);
        ArrayList<String> lore = new ArrayList<>(){{
            add("Right click with compass in hand to");
            add("cycle though unlocked compasses.");
        }};
        var displayName = Component.literal(String.format("Structure Compass (%s)", Utils.getAPStructureName(structureTag)));
        if(player.getCommandSenderWorld().dimension().equals(world)) {
            try {
                structurePos = APRandomizer.getServer().getLevel(world).findNearestMapStructure(structureTag, player.blockPosition(), 75, false);
                lore.add(0,"Location X: " + structurePos.getX() + ", Z: " + structurePos.getZ());
            } catch (NullPointerException exception) {
                player.sendSystemMessage(Component.literal("Could not find a nearby " + Utils.getAPStructureName(structureTag)));
                displayName = Component.literal(
                        String.format("Structure Compass (%s) Not Found",
                                Utils.getAPStructureName(structureTag))
                ).withStyle(ChatFormatting.YELLOW);
            }
        }
        else {
            displayName = Component.literal(
                    String.format("Structure Compass (%s) Wrong Dimension",
                            Utils.getAPStructureName(structureTag))
                    ).withStyle(ChatFormatting.DARK_RED);
        }

        if(structurePos == null)
            structurePos = new BlockPos(0,0,0);
        //update the nbt data with our new structure.

        CompoundTag nbt = compass.getOrCreateTag();
        //update the nbt data with our new structure.
        nbt.put("structure", StringTag.valueOf(structureTag.location().toString()));
        Utils.addLodestoneTags(world,structurePos,nbt);
        Utils.setNameAndLore(compass, displayName, lore);
    }

        // refresh all compasses in player inventory
    public static void refreshCompasses(ServerPlayer player) {
        player.getInventory().items.forEach( (item) -> {
            if(item.getItem().equals(Items.COMPASS)) {
                if(!item.hasTag())
                    return;
                CompoundTag nbt = item.getOrCreateTag();
                if(nbt.get("structure") == null)
                    return;

                try {
                    TagKey<Structure> tagKey = TagKey.create(Registries.STRUCTURE, ResourceLocation.tryParse(nbt.getString("structure")));
                    updateCompassLocation(tagKey, player, item);
                } catch (Exception ignored) {}
            }
        });
    }
}

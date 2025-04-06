package gg.archipelago.aprandomizer.common.events;

import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
public class onCrafting {
    // directly reference a log4j logger.
    private static  final Logger LOGGER = LogManager.getLogger();
    @SubscribeEvent
    public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {
        LOGGER.info("crafting event");
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        Item restrictedItem = event.getCrafting().getItem();

        if (APRandomizer.getRecipeManager().isRecipeLocked(restrictedItem)) {
            LOGGER.info("❌ This recipe is locked for you: {}", restrictedItem.getName(new ItemStack(restrictedItem)));
        }else{
            LOGGER.info("This recipe is not locked for you: {}", restrictedItem.getName(new ItemStack(restrictedItem)));
        }
    }
    @SubscribeEvent
    public void onSmelted(PlayerEvent.ItemSmeltedEvent event) {
        LOGGER.info("smelting event");
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        Item restrictedItem = event.getSmelting().getItem();

        if (APRandomizer.getRecipeManager().isRecipeLocked(restrictedItem)) {
            LOGGER.info("❌ This recipe is locked for you: {}", restrictedItem.getName(new ItemStack(restrictedItem)));
        }else{
            LOGGER.info("This recipe is not locked for you: {}", restrictedItem.getName(new ItemStack(restrictedItem)));
        }
    }

}

package gg.archipelago.aprandomizer.common.events;

import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.managers.advancementmanager.AdvancementManager;
import net.minecraft.advancements.Advancement;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
public class onAdvancement {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    static void onAdvancementEvent(AdvancementEvent.AdvancementProgressEvent event) {
        /*
        for (String progress : event.getAdvancementProgress().getCompletedCriteria()) {
            for (ServerPlayer p : APRandomizer.server.getPlayerList().getPlayers()) {
                p.getAdvancements().award(event.getAdvancement(), progress);
            }
        }
         */

    }

    @SubscribeEvent
    static void onAdvancementEvent(AdvancementEvent.AdvancementEarnEvent event) {
        /*
if (APRandomizer.getApmcData().state != APMCData.State.VALID)
            return;

        ServerPlayer player = (ServerPlayer) event.getEntity();
        Advancement advancement = event.getAdvancement();
        String id = advancement.getId().toString();

        AdvancementManager am = APRandomizer.getAdvancementManager();
        //don't do anything if this advancement has already been had, or is not on our list of tracked advancements.
        if (!am.hasAdvancement(id) && am.getAdvancementID(id) != 0) {
            LOGGER.debug("{} has gotten the advancement {}", player.getDisplayName().getString(), id);
            am.addAdvancement(am.getAdvancementID(id));
            am.syncAdvancement(advancement);
            if(advancement.getDisplay() == null)
                return;
            APRandomizer.getServer().getPlayerList().broadcastSystemMessage(
                    Component.translatable(
                            "chat.type.advancement."
                                    + advancement.getDisplay().getFrame().getName(),
                            player.getDisplayName(),
                            advancement.getChatComponent()
                    ),
                    false
            );

        }
         */
        //dont do any checking if the apmcdata file is not valid.

    }
}

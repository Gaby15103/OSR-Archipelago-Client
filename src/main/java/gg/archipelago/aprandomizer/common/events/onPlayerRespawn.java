package gg.archipelago.aprandomizer.common.events;

import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.managers.itemmanager.ItemManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class onPlayerRespawn {

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ItemManager.refreshCompasses(player);

        if (APRandomizer.isJailPlayers()) {
            BlockPos jail = APRandomizer.getJailPosition();
            player.teleportTo(jail.getX(), jail.getY(), jail.getZ());
        }
        APRandomizer.getGoalManager().checkGoalCompletion();
    }

}

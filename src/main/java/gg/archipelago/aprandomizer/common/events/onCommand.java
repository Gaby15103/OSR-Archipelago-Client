package gg.archipelago.aprandomizer.common.events;

import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.managers.Itemmanager.ItemManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class onCommand {

    private static final ArrayList<String> allowedCommands = new ArrayList<>() {{
        add("connect");
        add("sync");
        add("start");
        add("stop");
        add("kick");
        add("ban");
        add("ban-ip");
        add("pardon");
        add("pardon-ip");
        add("whitelist");
        add("me");
        add("say");
    }};

    @SubscribeEvent
    static void onPlayerLoginEvent(CommandEvent event) {
        if (!APRandomizer.isRace()) {
            return;
        }
        CommandSourceStack source = event.getParseResults().getContext().getSource();
        String command = event.getParseResults().getReader().getRead();
        for (String allowedCommand : allowedCommands)
            if (command.startsWith(allowedCommand) || command.startsWith("/"+allowedCommand))
                return;

        event.setCanceled(true);
        source.sendFailure(Component.literal("Non-essential commands are disabled in race mode."));
    }

    @Mod.EventBusSubscriber
    public static class onDimensionChange {

        @SubscribeEvent
        public static void onChange1(PlayerEvent.PlayerChangedDimensionEvent event) {
            if(!(event.getEntity() instanceof ServerPlayer player)) return;
            ItemManager.refreshCompasses(player);
        }

        @SubscribeEvent
        public static void onChange1(PlayerEvent.PlayerRespawnEvent event) {
            if(!(event.getEntity() instanceof ServerPlayer player)) return;
            ItemManager.refreshCompasses(player);
        }
    }
}

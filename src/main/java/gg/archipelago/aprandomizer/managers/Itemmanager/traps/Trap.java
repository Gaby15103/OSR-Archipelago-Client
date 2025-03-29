package gg.archipelago.aprandomizer.managers.Itemmanager.traps;

import net.minecraft.server.level.ServerPlayer;

public interface Trap {

    void trigger(ServerPlayer player);
}
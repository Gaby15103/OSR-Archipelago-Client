package gg.archipelago.aprandomizer.ap.events;

import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.SlotData;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import dev.koifysh.archipelago.events.ArchipelagoEventListener;
import dev.koifysh.archipelago.events.ConnectionAttemptEvent;

public class AttemptedConnection {

    @ArchipelagoEventListener
    static public void onAttemptConnect(ConnectionAttemptEvent event) {
        try {
            SlotData temp = event.getSlotData(SlotData.class);
            Utils.sendMessageToAll("starting item: " + temp.startingItems + "\n client version : " + temp.client_version
                    + "\n death link : " + temp.deathlink + "\n MC35 : " + temp.MC35 +
                    "\n quest goal : " + temp.quest_goal + "\n world seed : " + temp.minecraft_world_seed);
            APMCData data = APRandomizer.getApmcData();
            if (!event.getSeedName().equals(data.seed_name)) {
                Utils.sendMessageToAll("Failed to Connect to Archipelago Server: Wrong .apmc file found. please stop the server, use the correct .apmc file, delete the world folder, then relaunch the server.");
                event.setCanceled(true);
            }
            if (!APRandomizer.getValidVersions().contains(temp.getClient_version())) {
                event.setCanceled(true);
                Utils.sendMessageToAll("Game was generated with an for an incompatible version of the Minecraft Randomizer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package gg.archipelago.aprandomizer.common.events;

import dev.architectury.event.EventResult;
import dev.ftb.mods.ftbquests.events.ObjectCompletedEvent;
import net.minecraft.network.chat.Component;
import gg.archipelago.aprandomizer.common.Utils.Utils;

public class onQuest {

    // Register the event listener
    public static void onInitialize() {
        ObjectCompletedEvent.QuestEvent.QUEST.register((event) -> {
            if (event != null){
                Utils.sendMessageToAll("got something");
                for (Component component : event.getQuest().getDescription()){
                    Utils.sendMessageToAll(component);
                }
            }
            return EventResult.pass();
        });

    }
}

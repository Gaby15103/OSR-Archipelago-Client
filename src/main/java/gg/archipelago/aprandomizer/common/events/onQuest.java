package gg.archipelago.aprandomizer.common.events;

import dev.architectury.event.EventResult;
import dev.ftb.mods.ftbquests.events.ObjectCompletedEvent;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbteams.api.Team;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.managers.questManager.QuestManager;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class onQuest {
    // directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // Register the event listener
    public static void onInitialize() {
        ObjectCompletedEvent.QuestEvent.QUEST.register((event) -> {
            if (APRandomizer.getApmcData().state != APMCData.State.VALID)
                return EventResult.pass();
            Team team = APRandomizer.getTeamHelper().getTeam();
            Quest quest = event.getQuest();
            String id = quest.toString();
            QuestManager qm = APRandomizer.getQuestManager();
            if (Objects.equals(team.getTeamId(), event.getData().getTeamId())) {
                if (!qm.hasQuest(id) && qm.getQuestId(id) != 0) {
                    qm.addQuest(qm.getQuestId(id));
                    qm.syncQuest(quest);
                }else {
                    Utils.sendMessageToAll("The quest is not needed");
                }
            } else {
                Utils.sendMessageToAll("you can't get the archipelago reward because you are not in the archipelago teams");
            }
            return EventResult.pass();
        });

    }
}

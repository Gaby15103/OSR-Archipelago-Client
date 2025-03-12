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
            Utils.sendMessageToAll("at least at the start of the function");
            Team team = APRandomizer.getTeamHelper().getTeam();
            Quest quest = event.getQuest();
            Long id = quest.id;
            QuestManager qm = APRandomizer.getQuestManager();
            Utils.sendMessageToAll("Team Is present");
            if (Objects.equals(team.getTeamId(), event.getData().getTeamId())) {
                Utils.sendMessageToAll("Quest Completed by a member of archipelago team");
                if (!qm.hasQuest(id) && qm.getQuestId(id) != 0) {
                    Utils.sendMessageToAll("The quest hasn't been completed before and the quest is needed");
                    String questTitle = "";
                    if (quest.getRawTitle() != null) {
                        questTitle = quest.getRawTitle();
                    } else {
                        questTitle = id.toString();
                    }
                    LOGGER.debug("the teams {} has completed the quest {}", team.getShortName(), questTitle);
                    qm.addQuest(qm.getQuestId(id));
                    Utils.sendMessageToAll("The Quest has been added");
                    qm.syncQuest(quest);
                    Utils.sendMessageToAll("the teams " + team.getShortName() + " has completed the quest "
                            + quest.getTasksAsList().get(0).id);
                }else {
                    Utils.sendMessageToAll("The Quest has Been Completed and or the quest is not needed");
                }
            } else {
                Utils.sendMessageToAll("you can't get the archipelago reward because you are not in the archipelago teams");
            }
            return EventResult.pass();
        });

    }
}

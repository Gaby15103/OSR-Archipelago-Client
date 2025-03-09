package gg.archipelago.aprandomizer.common.events;

import dev.architectury.event.EventResult;
import dev.ftb.mods.ftbquests.events.ObjectCompletedEvent;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.managers.questManager.QuestManager;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class onQuest {
    // directly reference a log4j logger.
    private static  final Logger LOGGER = LogManager.getLogger();

    // Register the event listener
    public static void onInitialize() {
        ObjectCompletedEvent.QuestEvent.QUEST.register((event) -> {
            if (APRandomizer.getApmcData().state != APMCData.State.VALID)
                return EventResult.pass();
            Optional<Team> optionalTeam = FTBTeamsAPI.api().getManager().getTeamByID(event.getData().getTeamId());
            Quest quest = event.getQuest();
            Long id = quest.id;
            QuestManager qm = APRandomizer.getQuestManager();
            if (optionalTeam.isPresent()){
               Team team = optionalTeam.get();
               if (Objects.equals(team.getShortName(), APRandomizer.getTeamHelper().getTeamName())){
                   if (!qm.hasQuest(id) && qm.isQuestNeeded(id)){
                       String questTitle = "";
                       if (quest.getRawTitle() == null) {
                           questTitle = quest.getRawTitle();
                       }else {
                           questTitle = id.toString();
                       }
                       LOGGER.debug("the teams {} has completed the quest {}",team.getShortName(), questTitle);
                       qm.addQuest(id);
                       qm.syncQuest(quest);
                       Utils.sendMessageToAll("the teams "+ team.getShortName()+" has completed the quest "
                               + quest.getTasksAsList().get(0).id);
                   }
               }else{
                   ServerPlayer player = APRandomizer.getServer().getPlayerList().getPlayer(team.getOwner());
                   Component message = Component.literal("you can't get the archipelago reward because you are not in the archipelago teams");
               }
            }else {
                Utils.sendMessageToAll("got something but teams not present");
            }
            return EventResult.pass();
        });

    }
}

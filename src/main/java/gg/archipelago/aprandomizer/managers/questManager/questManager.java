package gg.archipelago.aprandomizer.managers.questManager;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbquests.quest.BaseQuestFile;
import dev.ftb.mods.ftbquests.quest.Quest;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.common.events.onQuest;
import gg.archipelago.aprandomizer.data.WorldData;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class QuestManager {
    // directly reference a log4j logger.
    private static  final Logger LOGGER = LogManager.getLogger();
    private final Set<Long> completedQuests = new HashSet<>();
    private BaseQuestFile questFile;
    public QuestManager(){
        onQuest.onInitialize();
        questFile = FTBQuestsAPI.api().getQuestFile(false);
        LOGGER.info(questFile.getCodeString());
    }

    public void setCheckedQuests(Set<Long> checkedLocations) {
        completedQuests.addAll(checkedLocations);

        syncAllQuests();
    }

    public void syncAllQuests(){
        for (Quest q: Objects.requireNonNull(questFile.getQuestChapter()).getQuests()){
            syncQuest(q);
        }
    }

    public void syncQuest(Quest q){
        if (hasQuest(q.id)) {
            for (ServerPlayer serverPlayer : APRandomizer.getServer().getPlayerList().getPlayers()){

            }
        }
    }
    public boolean hasQuest(Long questId){
        return completedQuests.contains(questId);
    }

}

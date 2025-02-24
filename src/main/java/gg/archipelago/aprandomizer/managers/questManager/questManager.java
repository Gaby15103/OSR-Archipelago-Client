package gg.archipelago.aprandomizer.managers.questManager;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbquests.quest.BaseQuestFile;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.QuestObjectBase;
import dev.ftb.mods.ftbquests.quest.task.Task;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.common.events.onQuest;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.*;

import static gg.archipelago.aprandomizer.APRandomizer.*;

public class QuestManager {
    // directly reference a log4j logger.
    private static  final Logger LOGGER = LogManager.getLogger();
    private final HashMap<String, Long> questData = new HashMap<>(){{

    }};
    private final Set<Long> completedQuests = new HashSet<>();
    private static BaseQuestFile baseQuestFile;
    private static List<Quest> quests;
    public QuestManager(){
        onQuest.onInitialize();
        quests = new ArrayList<>();
    }

    public void initializeQuests(){
        for (int i = 0; i < 50; i++){
            Utils.sendMessageToAll("init");
        }
        try {
            baseQuestFile = FTBQuestsAPI.api().getQuestFile(false);
        }catch (Exception e){
            Utils.sendMessageToAll(e.getMessage());
        }

        if (baseQuestFile != null){
            for (QuestObjectBase object : baseQuestFile.getAllObjects()){
                if (object instanceof Quest){
                    Utils.sendMessageToAll(String.valueOf(object.id));
                    if (!((Quest) object).canBeRepeated())
                        quests.add(object.getQuestFile().getQuest(object.id));
                }else {
                    Utils.sendMessageToAll("object not an instance of Quest");
                }
            }
        }else {
            Utils.sendMessageToAll("QuestFile is null");
        }
        for (Quest quest : quests){
            Utils.sendMessageToAll(String.valueOf(quest.getCodeString()) + " : " + String.valueOf(quest.getRawTitle()));
        }
    }

    public void setCheckedQuests(Set<Long> checkedLocations) {
        completedQuests.addAll(checkedLocations);
        syncAllQuests();
    }

    public void syncAllQuests(){
        for (Quest q: quests){
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

    public void addQuest(Long id){
        completedQuests.add(id);
        APRandomizer.getAP().checkLocation(id);
        APRandomizer.getGoalManager().updateGoal(true);
        APRandomizer.getWorldData().addLocation(id);
        syncAllQuests();
    }

    public void resendQuests(){
        for (Long completedQuest : completedQuests){
            APRandomizer.getAP().checkLocation(completedQuest);
        }
    }


}

package gg.archipelago.aprandomizer.managers;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbquests.quest.TeamData;
import dev.koifysh.archipelago.ClientStatus;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.managers.advancementmanager.AdvancementManager;
import gg.archipelago.aprandomizer.managers.questManager.QuestManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.bossevents.CustomBossEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class GoalManager {
    String questGoal;
    private final QuestManager questManager;

    private CustomBossEvent questInfoBar;
    private CustomBossEvent connectionInfoBar;

    private APMCData apmc;

    public GoalManager () {
        apmc = APRandomizer.getApmcData();
        questManager = APRandomizer.getQuestManager();
        questGoal = apmc.quest_goal;
        initializeInfoBar();
    }

    public void initializeInfoBar() {
        CustomBossEvents bossInfoManager = APRandomizer.getServer().getCustomBossEvents();
        questInfoBar = bossInfoManager.create(new ResourceLocation(APRandomizer.MODID,"advancementinfobar"), Component.literal(""));
        questInfoBar.setColor(BossEvent.BossBarColor.PINK);
        questInfoBar.setOverlay(BossEvent.BossBarOverlay.NOTCHED_10);

        connectionInfoBar = bossInfoManager.create(new ResourceLocation(APRandomizer.MODID,"connectioninfobar"), Component.literal("Not connected to Archipelago").withStyle(Style.EMPTY.withColor(TextColor.parseColor("red"))));
        connectionInfoBar.setMax(1);
        connectionInfoBar.setValue(1);
        connectionInfoBar.setColor(BossEvent.BossBarColor.RED);
        connectionInfoBar.setOverlay(BossEvent.BossBarOverlay.PROGRESS);

        updateInfoBar();
        connectionInfoBar.setVisible(true);
    }

    public void updateGoal(boolean canFinish) {
        updateInfoBar();
        if(canFinish)
            checkGoalCompletion();
    }

    public void updateInfoBar() {
        if(questInfoBar == null)
            return;
        APRandomizer.getServer().execute(() -> {
            questInfoBar.setPlayers(APRandomizer.getServer().getPlayerList().getPlayers());
            connectionInfoBar.setPlayers(APRandomizer.getServer().getPlayerList().getPlayers());
        });

        questInfoBar.setValue(questManager.getFinishedAmount());

        connectionInfoBar.setVisible(!APRandomizer.isConnected());
    }

    public void checkGoalCompletion() {
        if(!APRandomizer.isConnected())
            return;
        //check to see if our goal is done! if so send compleatoin to AP server
        if(goalsDone()) {
            APRandomizer.getAP().setGameState(ClientStatus.CLIENT_GOAL);
        }
    }

    public boolean goalsDone() {
        long questId = Long.parseLong(questGoal.split(":")[3]);
        ServerPlayer owner = APRandomizer.getServer().getPlayerList().getPlayer(APRandomizer.getTeamHelper().getTeam().getOwner());
        assert owner != null;
        TeamData teamData = TeamData.get(owner);
        return teamData.isCompleted(FTBQuestsAPI.api().getQuestFile(false).getQuest(questId));
    }


    /*
    // KEEP IT JUSTE TO BE SURE BUT NORMALLY WILL NEVER BE USED

        //subscribe to living death event to check for wither/dragon kills;
    @SubscribeEvent
    static void onBossDeath(LivingDeathEvent event) {
        LivingEntity mob = event.getEntity();
        GoalManager goalManager = APRandomizer.getGoalManager();
        if(mob instanceof EnderDragon && goalManager.goalsDone()) {
            goalManager.dragonKilled = true;
            Utils.sendMessageToAll("She is no more...");
            goalManager.updateGoal(false);
        }
        if(mob instanceof WitherBoss && goalManager.goalsDone()) {
            goalManager.witherKilled = true;
            Utils.sendMessageToAll("The Darkness has lifted...");
            goalManager.updateGoal(true);
        }
    }
     */
}

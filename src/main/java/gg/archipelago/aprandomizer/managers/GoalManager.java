package gg.archipelago.aprandomizer.managers;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.koifysh.archipelago.ClientStatus;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.data.WorldData;
import gg.archipelago.aprandomizer.managers.advancementmanager.AdvancementManager;
import gg.archipelago.aprandomizer.managers.itemmanager.ItemManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.bossevents.CustomBossEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GoalManager {
    String questGoal;

    private final AdvancementManager advancementManager;

    private CustomBossEvent advancementInfoBar;
    private CustomBossEvent connectionInfoBar;

    private APMCData apmc;

    private boolean dragonKilled = false;
    private boolean witherKilled = false;

    public GoalManager () {
        apmc = APRandomizer.getApmcData();
        advancementManager = APRandomizer.getAdvancementManager();
        questGoal = apmc.quest_goal;
        initializeInfoBar();
    }

    public void initializeInfoBar() {
        CustomBossEvents bossInfoManager = APRandomizer.getServer().getCustomBossEvents();
        advancementInfoBar = bossInfoManager.create(new ResourceLocation(APRandomizer.MODID,"advancementinfobar"), Component.literal(""));
        advancementInfoBar.setColor(BossEvent.BossBarColor.PINK);
        advancementInfoBar.setOverlay(BossEvent.BossBarOverlay.NOTCHED_10);

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
        if(advancementInfoBar == null)
            return;
        APRandomizer.getServer().execute(() -> {
            advancementInfoBar.setPlayers(APRandomizer.getServer().getPlayerList().getPlayers());
            connectionInfoBar.setPlayers(APRandomizer.getServer().getPlayerList().getPlayers());
        });

        advancementInfoBar.setValue(advancementManager.getFinishedAmount());

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
        return FTBQuestsAPI.api().getQuestFile(false).getQuest(questId).isCompletedRaw();
    }


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

    public boolean isDragonDead() {
        return dragonKilled;
    }
    public boolean isWitherDead() {
        return witherKilled;
    }
}

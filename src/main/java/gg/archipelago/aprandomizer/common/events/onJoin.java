package gg.archipelago.aprandomizer.common.events;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

@Mod.EventBusSubscriber
public class onJoin {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    static void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) throws CommandSyntaxException {

        ServerPlayer player = (ServerPlayer) event.getEntity();
        if(APRandomizer.isRace())
                player.setGameMode(GameType.SURVIVAL);

        APMCData data = APRandomizer.getApmcData();
        if (data.state == APMCData.State.MISSING) {
            Utils.sendMessageToAll("No APMC file found, please only start the server via the APMC file.");
            return;
        }
        else if (data.state == APMCData.State.INVALID_VERSION) {
            Utils.sendMessageToAll("This Seed was generated using an incompatible randomizer version.");
            return;
        }
        else if (data.state == APMCData.State.INVALID_SEED) {
            Utils.sendMessageToAll("Supplied APMC file does not match world loaded. something went very wrong here.");
            return;
        }
        if(!APRandomizer.getTeamHelper().isPlayerInArchipelagoTeam(player)){
            Utils.sendMessageToAll("You need to join the Archipelago teams to" +
                    " be able to receive and send archipelago location and items");
            Utils.sendMessageToAll("To join the Archipelago team enter : " +
                    "/ftbteams parties join "+APRandomizer.getTeamHelper().getTeam().getShortName());
            return;
        };
        APRandomizer.getAdvancementManager().syncAllAdvancements();
        APRandomizer.getQuestManager().syncAllQuests();
        Set<Recipe<?>> restricted = APRandomizer.getRecipeManager().getRestrictedRecipes();
        Set<Recipe<?>> granted = APRandomizer.getRecipeManager().getGrantedRecipes();
        player.awardRecipes(granted);
        player.resetRecipes(restricted);
        if (player.getRecipeBook().contains(new ResourceLocation("minecraft:oak_planks"))){
            Utils.sendMessageToAll("oak plank recipe is apparently in this player recipes book");
        }else {
            Utils.sendMessageToAll("oak plank recipe is not in thios player recipes book");
        }


        APRandomizer.getGoalManager().updateInfoBar();
        APRandomizer.getItemManager().catchUpPlayer(player);

        if(APRandomizer.isJailPlayers()) {
            BlockPos jail = APRandomizer.getJailPosition();
            player.teleportTo(jail.getX(),jail.getY(),jail.getZ());
            player.setGameMode(GameType.SURVIVAL);
        }
    }
}

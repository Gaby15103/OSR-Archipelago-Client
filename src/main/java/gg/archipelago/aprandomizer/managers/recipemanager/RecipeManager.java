package gg.archipelago.aprandomizer.managers.recipemanager;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.Recipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class RecipeManager {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    //have a lookup of every advancement
    private final RecipeData recipeData;

    private final Set<Recipe<?>> initialRestricted = new HashSet<>();
    private final Set<Recipe<?>> initialGranted = new HashSet<>();

    private Set<Recipe<?>> restricted = new HashSet<>();
    private Set<Recipe<?>> granted = new HashSet<>();

    private Set<ResourceLocation> itemQuests = new HashSet<>();


    public RecipeManager() {
        recipeData = new RecipeData();
        Collection<Recipe<?>> recipeList = APRandomizer.getServer().getRecipeManager().getRecipes();
        for (Recipe<?> iRecipe : recipeList) {
            if (recipeData.injectIRecipe(iRecipe)) {
                initialRestricted.add(iRecipe);
            } else {
                initialGranted.add(iRecipe);
            }
        }
        restricted = initialRestricted;
        granted = initialGranted;
    }

    public void grantRecipeList(List<Long> recipes) {
        for (var id : recipes) {
            if (!recipeData.hasID(id))
                continue;
            grantRecipe(id);
            return;
        }
    }

    public void grantRecipe(long id) {
        if (!recipeData.hasID(id))
            return;
        Set<Recipe<?>> toGrant = recipeData.getID(id).getGrantedRecipes();

        granted.addAll(toGrant);
        restricted.removeAll(toGrant);
        itemQuests.addAll(recipeData.getID(id).getUnlockedTrackingQuests());

        for (UUID playerUUID : APRandomizer.getTeamHelper().getTeam().getMembers()) {

            ServerPlayer player = APRandomizer.getServer().getPlayerList().getPlayer(playerUUID);
            assert player != null;
            player.resetRecipes(restricted);
            player.awardRecipes(granted);
            for (Recipe<?> recipe : granted){
                Utils.sendMessageToAll("Granted : " + recipe.getId());
            }

            /*
                        var serverQuests = FTBQuestsAPI.api().getQuestFile(false);
            recipeData.getID(id).getUnlockedTrackingQuests().forEach(
                    location -> {
                        var trackingQuest = serverQuests.get;
                        if (trackingQuest != null) {
                            APRandomizer.getAdvancementManager().syncAdvancement(trackingQuest);
                            APRandomizer.getQuestManager().syncQuest(trackingQuest);
                        }

                    });
             */
        }
    }

    public Set<Recipe<?>> getRestrictedRecipes() {
        return restricted;
    }

    public Set<Recipe<?>> getGrantedRecipes() {
        return granted;
    }

    public void resetRecipes() {
        restricted = initialRestricted;
        granted = initialGranted;
        recipeData.reset();
    }

    public boolean hasReceived(ResourceLocation id) {
        return itemQuests.contains(id);
    }
}

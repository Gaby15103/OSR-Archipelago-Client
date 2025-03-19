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
            //player.resetRecipes(restricted);
            //player.awardRecipes(granted);
            for(Recipe<?> recipe : granted){
                player.getRecipeBook().add(recipe);
            }

            var serverAdvancements = APRandomizer.getServer().getAdvancements();
            recipeData.getID(id).getUnlockedTrackingQuests().forEach(
                    location -> {
                        Utils.sendMessageToAll(location.toString());
                        var trackingAdvancement = serverAdvancements.getAdvancement(location);
                        if (trackingAdvancement != null) {
                            APRandomizer.getAdvancementManager().syncAdvancement(trackingAdvancement);
                        }

                    });
        }
    }
    /*
        return the recipes that are restricted from the initially restricted recipes in the recipes Set from recipeData
     */
    public Set<Recipe<?>> getRestrictedFromInitiallyRestricted(){
        return restricted;
    }

    /*
        return the recipes that are granted from the initially restricted recipes in the recipes Set from recipeData
    */
    public Set<Recipe<?>> getGrantedFromInitiallyRestricted(){
        Set<Recipe<?>> grantedRecipes = new HashSet<>();
        for (Map.Entry<Long, GroupRecipe> entry : recipeData.recipes.entrySet()) {
            GroupRecipe groupRecipe = entry.getValue();

            // Get the set of recipes from the GroupRecipe
            Set<Recipe<?>> iRecipes = groupRecipe.getIRecipes();

            // Filter and add granted recipes to the set
            for (Recipe<?> recipe : iRecipes) {
                if (granted.contains(recipe)) {
                    grantedRecipes.add(recipe);
                }
            }
        }
        return grantedRecipes;
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

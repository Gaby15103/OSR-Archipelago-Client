package gg.archipelago.aprandomizer.managers.recipemanager;

import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewProgressiveRecipe implements APRecipe {

    int id;
    String trackingAdvancementBase;
    ArrayList<String[]> namespaceIDs;
    List<Set<Recipe<?>>> recipes = new ArrayList<>();
    int currentTier = 0;
    int totalTiers;

    NewProgressiveRecipe(int id, String trackingAchievement, ArrayList<String[]> namespaceIDs) {
        super();
        this.id = id;
        this.namespaceIDs = namespaceIDs;
        this.totalTiers = namespaceIDs.size();
        this.trackingAdvancementBase = trackingAchievement;
        namespaceIDs.forEach((namespaceID) -> recipes.add(new HashSet<>()));
    }

    protected void addIRecipe(Recipe<?> iRecipe, int tier) {
        this.recipes.get(tier).add(iRecipe);
    }

    public int getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(int tier) {
        this.currentTier = tier;
    }

    public Set<Recipe<?>> getTier(int tier) {
        if (recipes.size() >= tier)
            return recipes.get(tier - 1);
        return recipes.get(recipes.size() - 1);
    }


    @Override
    public Set<Recipe<?>> getGrantedRecipes() {
        return getTier(++currentTier);
    }

    @Override
    public Set<ResourceLocation> getUnlockedTrackingAdvanements() {
        HashSet<ResourceLocation> trackingAdvancements = new HashSet<>();
        for (int i = 1; i <= currentTier; i++) {
            trackingAdvancements.add(new ResourceLocation(APRandomizer.MODID,"received/"+ trackingAdvancementBase + "_" + i));
        }
        return trackingAdvancements;
    }
}

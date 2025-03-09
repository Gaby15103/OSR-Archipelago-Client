package gg.archipelago.aprandomizer.managers.recipemanager;

import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.HashSet;
import java.util.Set;

public class GroupRecipe implements APRecipe {
    int id;
    ResourceLocation trackingQuest;
    String[] namespaceIDs;
    Set<Recipe<?>> iRecipes = new HashSet<>();

    GroupRecipe(int id, String trackingQuest, String[] namespaceIDs) {
        this.id = id;
        this.trackingQuest = new ResourceLocation(APRandomizer.MODID,"received/"+ trackingQuest);
        this.namespaceIDs = namespaceIDs;
    }

    protected void addIRecipe(Recipe<?> iRecipe) {
        this.iRecipes.add(iRecipe);
    }

    public Set<Recipe<?>> getIRecipes() {
        return iRecipes;
    }

    @Override
    public Set<Recipe<?>> getGrantedRecipes() {
        return iRecipes;
    }

    @Override
    public Set<ResourceLocation> getUnlockedTrackingAdvanements() {
        return Set.of(trackingQuest);
    }

}

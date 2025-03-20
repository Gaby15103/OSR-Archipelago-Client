package gg.archipelago.aprandomizer.common.commands.Suggestions;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.stream.Stream;

public class RecipeSuggestionProvider {
    // Suggest available recipe IDs
    public static final SuggestionProvider<CommandSourceStack> RECIPE_SUGGESTIONS = (CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) -> {
        // Get the server instance and recipe manager
        MinecraftServer server = context.getSource().getServer();

        if (server == null) {
            return Suggestions.empty();
        }

        RecipeManager recipeManager = server.getRecipeManager();
        if (recipeManager == null) {
            return Suggestions.empty();
        }

        // Suggest all known recipe IDs
        Stream<ResourceLocation> recipeIds = recipeManager.getRecipeIds();
        recipeIds.forEach(recipeId -> {
            builder.suggest(recipeId.toString());
        });

        return builder.buildFuture();
    };
}

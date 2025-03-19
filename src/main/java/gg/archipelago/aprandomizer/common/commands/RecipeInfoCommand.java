package gg.archipelago.aprandomizer.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.managers.recipemanager.RecipeManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber
public class RecipeInfoCommand {
    private static Set<Recipe<?>> allRecipes;
    // Register the command
    public static void Register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("recipecheck")
                        // Command for specific recipe or categories
                        .then(Commands.argument("recipe", StringArgumentType.greedyString())
                                .suggests((context, builder) -> {
                                    String partial = StringArgumentType.getString(context, "recipe");
                                    return suggestMatchingRecipes(partial, builder);
                                })
                                .executes(RecipeInfoCommand::checkRecipe)
                        )
                        // Check all recipes
                        .executes(context -> checkRecipes(context.getSource(), "all"))
        );
    }
    // Suggest matching recipes using TAB
    private static CompletableFuture<Suggestions> suggestMatchingRecipes(String partial, SuggestionsBuilder builder) {
        RecipeManager recipeManager = APRandomizer.getRecipeManager();
        allRecipes = recipeManager.getGrantedFromInitiallyRestricted();
        allRecipes.addAll(recipeManager.getRestrictedFromInitiallyRestricted());
        List<String> matchingRecipes = allRecipes.stream()
                .map(recipe -> recipe.getId().toString())
                .filter(name -> name.contains(partial)) // Suggest partial matches
                .sorted()
                .toList();

        for (String match : matchingRecipes) {
            builder.suggest(match);
        }

        // Suggest main categories
        if ("all".startsWith(partial)) builder.suggest("all");
        if ("granted".startsWith(partial)) builder.suggest("granted");
        if ("restricted".startsWith(partial)) builder.suggest("locked");

        return builder.buildFuture();
    }

    // Main method to check recipes based on type
    private static int checkRecipes(CommandSourceStack source, String type) {
        if (source.getEntity() instanceof ServerPlayer player) {
            if (!APRandomizer.isConnected()) {
                player.sendSystemMessage(Component.literal("Must be connected to an AP server to use this command"));
                return 0;
            }
            RecipeManager recipeManager = APRandomizer.getRecipeManager();

            Set<Recipe<?>> unlockedRecipes = recipeManager.getGrantedFromInitiallyRestricted();
            Set<Recipe<?>> lockedRecipes = recipeManager.getRestrictedFromInitiallyRestricted();

            if ("all".equals(type) || "restricted".equals(type)) {
                sendRecipeList(player, unlockedRecipes, "Restricted Recipes");
            }
            if ("all".equals(type) || "granted".equals(type)) {
                sendRecipeList(player, lockedRecipes, "Granted Recipes");
            }
        }
        return 1;
    }

    // Send a list of recipes to the player
    private static void sendRecipeList(ServerPlayer player, Set<Recipe<?>> recipes, String title) {
        player.sendSystemMessage(Component.literal("§6=== " + title + " ===§r"));
        if (recipes.isEmpty()) {
            player.sendSystemMessage(Component.literal("§cNo recipes found.§r"));
        } else {
            for (Recipe<?> recipe : recipes) {
                player.sendSystemMessage(Component.literal(" - " + recipe.getId().toString()));
            }
        }
    }
    private static int checkRecipe(CommandContext<CommandSourceStack> context) {
        String recipeName = StringArgumentType.getString(context, "recipe");
        CommandSourceStack source = context.getSource();

        // Check for category-based commands
        if ("all".equals(recipeName)) {
            return checkRecipes(source, "all");
        } else if ("granted".equals(recipeName)) {
            return checkRecipes(source, "granted");
        } else if ("locked".equals(recipeName)) {
            return checkRecipes(source, "locked");
        }

        // Handle specific recipe lookup
        if (source.getEntity() instanceof ServerPlayer player) {
            RecipeManager recipeManager = APRandomizer.getRecipeManager();
            allRecipes = recipeManager.getGrantedFromInitiallyRestricted();
            allRecipes.addAll(recipeManager.getRestrictedFromInitiallyRestricted());

            Set<Recipe<?>> unlockedRecipes = recipeManager.getGrantedFromInitiallyRestricted();
            Set<Recipe<?>> lockedRecipes = recipeManager.getRestrictedFromInitiallyRestricted();

            // Search for a specific recipe
            Recipe<?> foundRecipe = allRecipes.stream()
                    .filter(recipe -> recipe.getId().toString().equals(recipeName))
                    .findFirst()
                    .orElse(null);

            if (foundRecipe != null) {
                if (unlockedRecipes.contains(foundRecipe)) {
                    player.sendSystemMessage(Component.literal("§aGranted Recipe: §f" + foundRecipe.getId()));
                } else if (lockedRecipes.contains(foundRecipe)) {
                    player.sendSystemMessage(Component.literal("§cRestricted Recipe: §f" + foundRecipe.getId()));
                } else {
                    player.sendSystemMessage(Component.literal("§7Unknown state for recipe: " + foundRecipe.getId()));
                }
            } else {
                player.sendSystemMessage(Component.literal("§cRecipe not found or not in archipelago initially restricted recipe: " + recipeName));
            }
        }
        return 1;
    }
    //wait for register commands event then register ourself as a command.
    @SubscribeEvent
    static void onRegisterCommandsEvent(RegisterCommandsEvent event) {
        RecipeInfoCommand.Register(event.getDispatcher());
    }
}


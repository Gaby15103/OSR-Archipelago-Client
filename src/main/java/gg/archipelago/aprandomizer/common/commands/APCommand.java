package gg.archipelago.aprandomizer.common.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.common.Utils.TitleQueue;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.common.commands.Suggestions.RecipeSuggestionProvider;
import gg.archipelago.aprandomizer.managers.recipemanager.RecipeManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.RecipeBook;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Mod.EventBusSubscriber
public class APCommand {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    //build our command structure and submit it
    public static void Register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("ap") // Base slash command is "ap"
                // First sub-command to set/retrieve deathlink status
                .then(Commands.literal("deathlink")
                        .executes(APCommand::queryDeathLink)
                        .then(Commands.argument("value", BoolArgumentType.bool())
                                .executes(APCommand::setDeathLink)
                        )
                )

                // Second sub-command to set/retrieve MC35 status
                .then(Commands.literal("mc35")
                        .executes(APCommand::queryMC35)
                        .then(Commands.argument("value", BoolArgumentType.bool())
                                .executes(APCommand::setMC35)
                        )
                )

                // Third sub-command to stop title queue
                .then(Commands.literal("clearTitleQueue")
                        .executes(APCommand::clearTitleQueue)
                )

                // Recipe-related sub-commands
                .then(Commands.literal("recipe")
                        .then(Commands.literal("all")
                                .executes(APCommand::allRecipe)
                        )
                        .then(Commands.literal("restricted")
                                .executes(APCommand::restrictedRecipe)
                        )
                        .then(Commands.literal("granted")
                                .executes(APCommand::grantedRecipe)
                        )
                        .then(Commands.literal("recipeBook")
                                .then(Commands.argument("player", net.minecraft.commands.arguments.EntityArgument.player())
                                        .executes(APCommand::recipeBook)
                                )
                        )
                        .then(Commands.literal("lock")
                                .then(Commands.argument("player", EntityArgument.players())
                                        .then(Commands.argument("recipe", ResourceLocationArgument.id())
                                                .executes(ctx -> {
                                                    List<ServerPlayer> players = (List<ServerPlayer>) EntityArgument.getPlayers(ctx, "player");
                                                    ResourceLocation recipeId = ResourceLocationArgument.getId(ctx, "recipe");
                                                    return lockRecipe(players, recipeId);
                                                })
                                        )
                                )
                        )
                        .then(Commands.literal("unlock")
                                .then(Commands.argument("player", EntityArgument.players())
                                        .then(Commands.argument("recipe", ResourceLocationArgument.id())
                                                .executes(ctx -> {
                                                    List<ServerPlayer> players = (List<ServerPlayer>) EntityArgument.getPlayers(ctx, "player");
                                                    ResourceLocation recipeId = ResourceLocationArgument.getId(ctx, "recipe");
                                                    return unlockRecipe(players, recipeId);
                                                })
                                        )
                                )
                        )
                )

                // KubeJS recipe-related sub-command
                .then(Commands.literal("recipeKubeJs")
                        .then(Commands.argument("recipeId", ResourceLocationArgument.id()) // Argument for recipeId with suggestions
                                .suggests(SuggestionProviders.ALL_RECIPES) // Provides suggestions for recipe IDs
                                .then(Commands.argument("isRestricted", BoolArgumentType.bool()) // Argument for restriction flag (true/false)
                                        .executes(context -> handleRecipeCommand(context))
                                )
                        )
                )
        );


    }

    private static int handleRecipeCommand(com.mojang.brigadier.context.CommandContext<CommandSourceStack> context) {
        // Get the recipeId and isRestricted values
        String recipeId = ResourceLocationArgument.getId(context, "recipeId").toString();  // Get the recipeId as a string
        boolean isRestricted = BoolArgumentType.getBool(context, "isRestricted");

        // Get the player who executed the command
        CommandSourceStack source = context.getSource();
        if (source.getEntity() instanceof ServerPlayer player) {
            // Trigger the custom recipe event
            APRandomizer.getRecipeManager().triggerCustomRecipeEvent(recipeId, isRestricted);
            source.sendSuccess(() -> Component.literal("§aTriggered custom event for recipe: §6" + recipeId + " with restriction: " + isRestricted), false);
            return 1;
        } else {
            source.sendFailure(Component.literal("§cThis command must be used by a player."));
            return 0;
        }
    }

    private static int clearTitleQueue(CommandContext<CommandSourceStack> commandSourceStackCommandContext) {
        Utils.sendMessageToAll("Title Queue Cleared");
        TitleQueue.clearTitleQueue();
        return 1;
    }

    private static int queryDeathLink(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }
        String enabled = (APRandomizer.getAP().getSlotData().deathlink) ? "enabled" : "disabled";
        source.getSource().sendSuccess(() -> Component.literal("DeathLink is " + enabled), false);
        return 1;
    }

    private static int setDeathLink(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }

        APRandomizer.getAP().getSlotData().deathlink = BoolArgumentType.getBool(source, "value");
        boolean deathlink = APRandomizer.getAP().getSlotData().deathlink;
        if (deathlink) {
            APRandomizer.getAP().addTag("DeathLink");
        } else {
            APRandomizer.getAP().removeTag("DeathLink");
        }

        String enabled = (APRandomizer.getAP().getSlotData().deathlink) ? "enabled" : "disabled";
        source.getSource().sendSuccess(() -> Component.literal("DeathLink is " + enabled), false);
        return 1;
    }

    private static int queryMC35(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }

        String enabled = (APRandomizer.getAP().getSlotData().MC35) ? "enabled" : "disabled";
        source.getSource().sendSuccess(() -> Component.literal("MC35 is " + enabled), false);
        return 1;
    }

    private static int setMC35(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }

        APRandomizer.getAP().getSlotData().MC35 = BoolArgumentType.getBool(source, "value");
        boolean mc35 = APRandomizer.getAP().getSlotData().MC35;
        if (mc35) {
            APRandomizer.getAP().addTag("MC35");
        } else {
            APRandomizer.getAP().removeTag("MC35");
        }

        String enabled = (APRandomizer.getAP().getSlotData().MC35) ? "enabled" : "disabled";
        source.getSource().sendSuccess(() -> Component.literal("MC35 is " + enabled), false);
        return 1;
    }

    private static int allRecipe(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }
        RecipeManager recipeManager = APRandomizer.getRecipeManager();
        Set<Recipe<?>> grantedRecipes = recipeManager.getGrantedFromInitiallyRestricted();
        Set<Recipe<?>> restrictedRecipes = recipeManager.getRestrictedFromInitiallyRestricted();
        String message = sendRecipeList(restrictedRecipes, "Restricted Recipes") + "\n\n" + sendRecipeList(grantedRecipes, "Granted Recipes");

        source.getSource().sendSuccess(() -> Component.literal(message), false);
        return 1;
    }

    private static int grantedRecipe(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }
        RecipeManager recipeManager = APRandomizer.getRecipeManager();
        Set<Recipe<?>> grantedRecipes = recipeManager.getGrantedFromInitiallyRestricted();

        source.getSource().sendSuccess(() -> Component.literal(sendRecipeList(grantedRecipes, "Granted Recipes")), false);
        return 1;
    }

    private static int restrictedRecipe(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }
        RecipeManager recipeManager = APRandomizer.getRecipeManager();
        Set<Recipe<?>> restrictedRecipes = recipeManager.getRestrictedFromInitiallyRestricted();

        source.getSource().sendSuccess(() -> Component.literal(sendRecipeList(restrictedRecipes, "Restricted Recipes")), false);
        return 1;
    }

    private static String sendRecipeList(Set<Recipe<?>> recipes, String title) {
        StringBuilder message = new StringBuilder("§6=== " + title + " ===§r");
        if (recipes.isEmpty()) {
            message = new StringBuilder("§cNo recipes found.§r");
        } else {
            for (Recipe<?> recipe : recipes) {
                message.append("\n - ").append(recipe.getId().toString());
            }
        }
        return message.toString();
    }

    private static int recipeBook(CommandContext<CommandSourceStack> source) throws CommandSyntaxException {
        ServerPlayer player = net.minecraft.commands.arguments.EntityArgument.getPlayer(source, "player");
        // Get the RecipeBook of the player
        RecipeBook recipeBook = player.getRecipeBook();

        // Create a list to store unlocked recipes
        Set<Recipe<?>> unlockedRecipes = new HashSet<>();

        // Get all recipes from the recipe manager

        net.minecraft.world.item.crafting.RecipeManager recipeManager = Objects.requireNonNull(player.getServer()).getRecipeManager();

        // Iterate through all recipes and check if the player has unlocked them
        for (Recipe<?> recipe : recipeManager.getRecipes()) {
            if (recipeBook.contains(recipe)) {
                unlockedRecipes.add(recipe);
            }
        }
        File file = new File(player.getServer().getServerDirectory(), "unlocked_recipes.txt");

        // If the file doesn't exist, create it
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            // Write the unlocked recipes to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                if (unlockedRecipes.isEmpty()) {
                    writer.write("No unlocked recipes found.\n");
                } else {
                    writer.write("Unlocked Recipes:\n");
                    for (Recipe<?> recipe : unlockedRecipes) {
                        writer.write("- " + recipe.getId() + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        source.getSource().sendSuccess(() -> Component.literal(sendRecipeList(unlockedRecipes, "Recipe Book")), false);
        return 1;
    }

    private static int lockRecipe(List<ServerPlayer> players, ResourceLocation recipeId) {
        Optional<Recipe<?>> recipeOpt = (Optional<Recipe<?>>) APRandomizer.getServer().getRecipeManager().byKey(recipeId);
        for (ServerPlayer player : players){
            if (recipeOpt.isPresent()) {
                Recipe<?> recipe = recipeOpt.get();
                APRandomizer.getServer().execute(() -> {
                    player.getRecipeBook().remove(recipe);
                    if (!player.getRecipeBook().contains(recipe)) {
                        player.sendSystemMessage(Component.literal("🔓 "+ player.getName().getString() +" locked recipe: " + recipeId));
                    }
                });
                return Command.SINGLE_SUCCESS;
            } else {
                player.sendSystemMessage(Component.literal("❌ Recipe not found: " + recipeId));
                return 0;
            }
        }
        return 0;
    }

    private static int unlockRecipe(List<ServerPlayer> players, ResourceLocation recipeId) {
        Optional<Recipe<?>> recipeOpt = (Optional<Recipe<?>>) APRandomizer.getServer().getRecipeManager().byKey(recipeId);
        for (ServerPlayer player : players){
            if (recipeOpt.isPresent()) {
                Recipe<?> recipe = recipeOpt.get();
                APRandomizer.getServer().execute(() -> {
                    player.getRecipeBook().add(recipe);
                    if (player.getRecipeBook().contains(recipe)) {
                        player.sendSystemMessage(Component.literal("🔓 "+ player.getName().getString() +" Unlocked recipe: " + recipeId));
                    }
                });
                return Command.SINGLE_SUCCESS;
            } else {
                player.sendSystemMessage(Component.literal("❌ Recipe not found: " + recipeId));
                return 0;
            }
        }
        return 0;
    }


    //wait for register commands event then register ourself as a command.
    @SubscribeEvent
    static void onRegisterCommandsEvent(RegisterCommandsEvent event) {
        APCommand.Register(event.getDispatcher());
    }
}

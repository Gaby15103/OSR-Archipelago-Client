package gg.archipelago.aprandomizer.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.common.Utils.TitleQueue;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.managers.recipemanager.RecipeManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.C;

import java.util.Set;

@Mod.EventBusSubscriber
public class APCommand {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    //build our command structure and submit it
    public static void Register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("ap") //base slash command is "ap"
                        //First sub-command to set/retreive deathlink status
                        .then(Commands.literal("deathlink")
                                .executes(APCommand::queryDeathLink)
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(APCommand::setDeathLink)
                                )
                        )
                        //Second sub-command to set/retreive MC35 status
                        .then(Commands.literal("mc35")
                                .executes(APCommand::queryMC35)
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(APCommand::setMC35)
                                )
                        )
                        //third sub-command to stop titlequeue
                        .then(Commands.literal("clearTitleQueue")
                                .executes(APCommand::clearTitleQueue)
                        )
                        .then(Commands.literal("recipe")
                                .then(Commands.literal("all")
                                        .executes(APCommand::allRecipe))
                                .then(Commands.literal("restricted")
                                        .executes(APCommand::restrictedRecipe))
                                .then(Commands.literal("granted")
                                        .executes(APCommand::grantedRecipe))
                        )

        );

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
        String message = sendRecipeList(restrictedRecipes, "Restricted Recipes") + "\n\n" +
                sendRecipeList(grantedRecipes, "Granted Recipes");

        source.getSource().sendSuccess(() -> Component.literal(message),false);
        return 1;
    }

    private static int grantedRecipe(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }
        RecipeManager recipeManager = APRandomizer.getRecipeManager();
        Set<Recipe<?>> grantedRecipes = recipeManager.getGrantedFromInitiallyRestricted();

        source.getSource().sendSuccess(() -> Component.literal(sendRecipeList(grantedRecipes, "Granted Recipes")),false);
        return 1;
    }

    private static int restrictedRecipe(CommandContext<CommandSourceStack> source) {
        if (!APRandomizer.isConnected()) {
            source.getSource().sendFailure(Component.literal("Must be connected to an AP server to use this command"));
            return 0;
        }
        RecipeManager recipeManager = APRandomizer.getRecipeManager();
        Set<Recipe<?>> restrictedRecipes = recipeManager.getRestrictedFromInitiallyRestricted();

        source.getSource().sendSuccess(() -> Component.literal(sendRecipeList(restrictedRecipes, "Restricted Recipes")),false);
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

    //wait for register commands event then register ourself as a command.
    @SubscribeEvent
    static void onRegisterCommandsEvent(RegisterCommandsEvent event) {
        APCommand.Register(event.getDispatcher());
    }
}

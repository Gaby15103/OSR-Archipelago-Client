package gg.archipelago.aprandomizer.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import gg.archipelago.aprandomizer.APClient;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
public class ConnectCommand {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    //build our command structure and submit it
    public static void Register(CommandDispatcher<CommandSource> dispatcher) {

        dispatcher.register(
                Commands.literal("connect") //base slash command is "connect"
                //first make sure its NOT a dedicated server (aka single player or hosted via in game client, OR user has an op level of 1)
                .requires((CommandSource) -> (!CommandSource.getServer().isDedicatedServer() || CommandSource.hasPermission(1)))
                //take the first argument as a string and name it "Address"
                .then(Commands.argument("Address", StringArgumentType.string())
                        .then(Commands.argument("Name", StringArgumentType.string())
                                .executes(context -> connectToAPServer(
                                        context,
                                        StringArgumentType.getString(context,"Address"),
                                        StringArgumentType.getString(context,"Name"),
                                        null
                                ))
                                .then(Commands.argument("Password", StringArgumentType.string())
                                        .executes(context -> connectToAPServer(
                                                context,
                                                StringArgumentType.getString(context,"Address"),
                                                StringArgumentType.getString(context,"Name"),
                                                StringArgumentType.getString(context,"Password")
                                        ))
                                )
                        )
                )
        );

    }

    private static int connectToAPServer(CommandContext<CommandSource> commandContext, String address, String name, String password) {
        CommandSource source = commandContext.getSource();
        APClient apClient = APRandomizer.getAP();

        apClient.setName(name);
        apClient.setPassword(password);
        Utils.sendMessageToAll("Connecting to Archipelago server at " +address);
        apClient.connect(address);
        return 1;
    }

    //wait for register commands event then register ourself as a command.
    @SubscribeEvent
    static void onRegisterCommandsEvent(RegisterCommandsEvent event) {
        ConnectCommand.Register(event.getDispatcher());
    }
}

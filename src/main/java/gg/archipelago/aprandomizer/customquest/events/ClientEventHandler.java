package gg.archipelago.aprandomizer.customquest.events;

import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.customquest.keys.KeyBindings;
import gg.archipelago.aprandomizer.customquest.keys.KeyInputHandler;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = APRandomizer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    public static void onClientSetup(final FMLClientSetupEvent event) {
        // Enregistre l'écouteur pour les touches
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }

    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        // Enregistre les touches configurables
        KeyBindings.register(event);
    }
}

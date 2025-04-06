package gg.archipelago.aprandomizer.customquest.keys;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class KeyInputHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // Vérifie si la touche pour ouvrir le livre de quêtes est pressée
        if (KeyBindings.OPEN_QUEST_BOOK_KEY.consumeClick()) {
        }
    }
}

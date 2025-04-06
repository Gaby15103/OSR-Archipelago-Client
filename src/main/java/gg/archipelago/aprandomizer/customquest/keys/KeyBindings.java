package gg.archipelago.aprandomizer.customquest.keys;

import com.mojang.blaze3d.platform.InputConstants;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;


public class KeyBindings {

    // Catégorie et touche pour ouvrir le livre de quêtes
    public static final String KEY_CATEGORY_CUSTOM = "key.categories." + APRandomizer.MODID; // Catégorie dans les options
    public static final String KEY_OPEN_QUEST_BOOK = "key." + APRandomizer.MODID + ".open_quest_book"; // Nom de la touche

    // Déclaration de la touche
    public static KeyMapping OPEN_QUEST_BOOK_KEY;

    public static void register(RegisterKeyMappingsEvent event) {
        // Crée la touche avec la catégorie personnalisée
        OPEN_QUEST_BOOK_KEY = new KeyMapping(
                KEY_OPEN_QUEST_BOOK,                      // Clé de traduction pour la touche
                InputConstants.Type.KEYSYM,               // Type de touche (clavier)
                InputConstants.KEY_M,                     // Touche par défaut : M
                KEY_CATEGORY_CUSTOM                       // Catégorie dans les paramètres
        );

        // Enregistre la touche
        event.register(OPEN_QUEST_BOOK_KEY);
    }
}

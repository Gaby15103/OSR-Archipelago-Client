package gg.archipelago.aprandomizer.customquest.ui;

import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.GuiGraphics;


public class QuestBookScreen extends Screen {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(APRandomizer.MODID, "textures/gui/quest_book.png");
    private static final int IMAGE_WIDTH = 256;
    private static final int IMAGE_HEIGHT = 166;
    public QuestBookScreen() {
        super(Component.literal("Livre de Quêtes"));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        guiGraphics.drawString(this.font, "Bienvenue dans votre livre de quêtes !", this.width / 2 - 100, this.height / 2, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

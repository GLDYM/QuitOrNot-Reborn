package dev.polaris_light.quitornot.client.screen.confirm.style;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public abstract class BaseStyle {
    public abstract Button generateConfirmButtons(Screen screen, Button.OnPress onConfirm);

    public abstract Button generateCancelButtons(Screen screen, Button.OnPress onCancel);

    public abstract void render(
            Minecraft client, Font textRenderer, Screen screen, Component title, Component message,
            GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta);

    protected void renderTiledBackground(GuiGraphicsExtractor graphics, int startX, int startY, int endX, int endY, Identifier identifier) {
        int width = endX - startX;
        int height = endY - startY;
        graphics.blit(RenderPipelines.GUI_TEXTURED, identifier, startX, startY, 0, 0, width, height, width, height, 32, 32, 0xFF404040);
    }
}

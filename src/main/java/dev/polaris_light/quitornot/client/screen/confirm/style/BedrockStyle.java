package dev.polaris_light.quitornot.client.screen.confirm.style;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public final class BedrockStyle extends BaseStyle {
    private static final Identifier WINDOW_TEXTURE = Identifier.fromNamespaceAndPath("quitornot", "textures/gui/bedrock/window.png");
    private static final Identifier BACKGROUND = Identifier.withDefaultNamespace("textures/block/dirt.png");

    // 窗口宽度
    private static final int windowWidth = 252;
    // 窗口长度
    private static final int windowHeight = 140;
    // 按钮旁边距
    private static final int buttonLRMargin = 20;
    // 按钮下边距
    private static final int buttonBMargin = 20;
    // 按钮宽度
    private static final int buttonWidth = 100;
    // 按钮长度
    private static final int buttonHeight = 20;
    // 信息文本下边距
    private static final int messageBMargin = 100;

    @Override
    public Button generateConfirmButtons(Screen screen, Button.OnPress onConfirm) {
        return Button.builder(CommonComponents.GUI_YES, onConfirm)
                .bounds((screen.width - windowWidth) / 2 + windowWidth - buttonWidth - buttonLRMargin,
                        (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public Button generateCancelButtons(Screen screen, Button.OnPress onCancel) {
        return Button.builder(CommonComponents.GUI_NO, onCancel)
                .bounds((screen.width - windowWidth) / 2 + buttonLRMargin,
                        (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public void render(Minecraft client, Font textRenderer, Screen screen, Component title, Component message, GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        drawBackground(client, screen, graphics);
        drawWindow(textRenderer, title, graphics, (screen.width - windowWidth) / 2, (screen.height - windowHeight) / 2);
        drawMessage(textRenderer, screen, message, graphics);
    }

    private void drawBackground(Minecraft client, Screen screen, GuiGraphicsExtractor graphics) {
        if (client.level != null) {
            graphics.fillGradient(0, 0, screen.width, screen.height, -1072689136, -804253680);
        } else {
            screen.extractTransparentBackground(graphics);
            graphics.fill(0, 0, screen.width, screen.height, 0xB3101010);
        }
    }

    private void drawWindow(Font textRenderer, Component title, GuiGraphicsExtractor graphics, int x, int y) {
        renderTiledBackground(graphics, x, y, x + windowWidth, y + windowHeight, BACKGROUND);
        graphics.blit(RenderPipelines.GUI_TEXTURED, WINDOW_TEXTURE, x, y, 0, 0, 252, 140, 252, 140, 256, 256);
        graphics.text(textRenderer, title, x + 8, y + 6, 4210752, false);
    }

    private void drawMessage(Font textRenderer, Screen screen, Component message, GuiGraphicsExtractor graphics) {
        graphics.centeredText(textRenderer, message,
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin,
                10526880);
    }
}

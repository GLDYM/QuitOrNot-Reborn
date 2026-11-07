package dev.polaris_light.quitornot.client.toast;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;

public final class QuitToast extends BaseToast {
    public QuitToast(Component message, long keepTime) {
        super(Component.translatable("toast.quitornot.confirm.title"), message, keepTime);
    }

    @Override
    public int width() {
        return 241;
    }

    @Override
    protected void drawToast(GuiGraphicsExtractor graphics, Font font) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0, 0, width(), height(), width(), height(), 256, 256);
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 8, 0, 241, 0, 15, 30, 15, 30, 256, 256);
        graphics.text(font, title, 35, 7, -1, true);
        graphics.text(font, message, 35, 18, -1, true);
    }
}

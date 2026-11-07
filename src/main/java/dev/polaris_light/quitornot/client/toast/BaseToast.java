package dev.polaris_light.quitornot.client.toast;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public abstract class BaseToast implements Toast {
    protected static final Identifier texture = Identifier.fromNamespaceAndPath("quitornot", "textures/gui/toasts.png");
    protected final Component title;
    protected final Component message;
    protected final long keepTime;
    private boolean hidden;

    protected BaseToast(Component title, Component message, long keepTime) {
        this.title = title;
        this.message = message;
        this.keepTime = keepTime;
    }

    @Override
    public Visibility getWantedVisibility() {
        return hidden ? Visibility.HIDE : Visibility.SHOW;
    }

    @Override
    public void update(ToastManager manager, long fullyVisibleForMs) {
        if (fullyVisibleForMs >= keepTime) {
            hidden = true;
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, Font font, long fullyVisibleForMs) {
        drawToast(graphics, font);
    }

    protected abstract void drawToast(GuiGraphicsExtractor graphics, Font font);
}

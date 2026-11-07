package dev.polaris_light.quitornot.client.screen.confirm;

import dev.polaris_light.quitornot.client.config.ClientConfig;
import dev.polaris_light.quitornot.client.screen.confirm.style.BaseStyle;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;

public final class ConfirmScreen extends Screen {
    private final Component message;
    private final Runnable onCancel;
    private final Runnable onConfirm;
    private final long openTime;
    private final BaseStyle style = ClientConfig.confirmScreenStyle.get().baseStyleSupplier.get();
    private Button cancel;
    private Button confirm;

    private boolean confirmed = false;

    public boolean isConfirmed() {
        return confirmed;
    }

    public ConfirmScreen(Screen parentScreen, Component message, Runnable confirm) {
        super(Component.translatable("screen.quitornot.confirm.title"));
        this.openTime = System.currentTimeMillis();
        this.message = message;
        this.onCancel = () -> this.minecraft.setScreen(parentScreen);
        this.onConfirm = () -> {
            confirmed = true;
            confirm.run();
        };
    }

    @Override
    protected void init() {
        initButton();
    }

    @Override
    public void tick() {
        if (isActive()) {
            cancel.active = true;
            confirm.active = true;
        }
    }

    private void initButton() {
        confirm = style.generateConfirmButtons(this, button -> {
            confirmed = true;
            onConfirm.run();
        });

        cancel = style.generateCancelButtons(this, button -> onCancel.run());

        confirm.active = false;
        cancel.active = false;
        this.addRenderableWidget(confirm);
        this.addRenderableWidget(cancel);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        // The selected style draws its own background in extractRenderState.
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        style.render(this.minecraft, this.font, this, title, message, graphics, mouseX, mouseY, delta);
        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (ClientConfig.enableKey.get() && event.isConfirmation() && isActive()) {
            onConfirm.run();
            return true;
        }
        return super.keyPressed(event);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return ClientConfig.enableKey.get() && isActive();
    }

    @Override
    public void onClose() {
        onCancel.run();
    }

    public boolean isActive() {
        return openTime + ClientConfig.buttonWaitTime.get() < System.currentTimeMillis();
    }
}

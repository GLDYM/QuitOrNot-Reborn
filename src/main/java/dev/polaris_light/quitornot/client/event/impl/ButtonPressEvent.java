package dev.polaris_light.quitornot.client.event.impl;

import dev.polaris_light.quitornot.client.event.api.CancelableEvent;
import net.minecraft.client.gui.components.Button;

public class ButtonPressEvent extends CancelableEvent {
    private final Button button;

    public ButtonPressEvent(Button button) {
        this.button = button;
    }


    public Button getButton() {
        return button;
    }
}

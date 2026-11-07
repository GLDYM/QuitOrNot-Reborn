package dev.polaris_light.quitornot.client;

import dev.polaris_light.quitornot.client.config.ClientConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(QuitOrNot.MODID)
public class QuitOrNot {
    public static final String MODID = "quitornot_reborn";

    public QuitOrNot(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.CFG);
    }
}

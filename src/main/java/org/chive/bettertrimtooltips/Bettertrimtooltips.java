package org.chive.bettertrimtooltips;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Bettertrimtooltips.MODID)
public class Bettertrimtooltips {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "bettertrimtooltips";

    public Bettertrimtooltips() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }
}

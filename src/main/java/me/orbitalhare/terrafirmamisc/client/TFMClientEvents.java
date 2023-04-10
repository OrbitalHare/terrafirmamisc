package me.orbitalhare.terrafirmamisc.client;

import me.orbitalhare.terrafirmamisc.common.container.TFMContainerTypes;
import net.dries007.tfc.client.screen.KnappingScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class TFMClientEvents {
    
    public static void init()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
        bus.addListener(TFMClientEvents::clientSetup);
    }
    
    public static void clientSetup(FMLClientSetupEvent event)
    {
    
        event.enqueueWork(() -> {
            MenuScreens.register(TFMContainerTypes.STRAW.get(), KnappingScreen::new);
        });
        
    }
    
}

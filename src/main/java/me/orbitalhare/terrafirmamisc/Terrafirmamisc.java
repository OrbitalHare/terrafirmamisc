package me.orbitalhare.terrafirmamisc;

import me.orbitalhare.terrafirmamisc.client.TFMClientEvents;
import me.orbitalhare.terrafirmamisc.common.container.TFMContainerTypes;
import me.orbitalhare.terrafirmamisc.common.misc.TFMInteractionManager;
import me.orbitalhare.terrafirmamisc.common.recipes.TFMRecipeSerializers;
import me.orbitalhare.terrafirmamisc.common.recipes.TFMRecipeTypes;
import me.orbitalhare.terrafirmamisc.config.TFMConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import me.orbitalhare.terrafirmamisc.common.blocks.TFMBlocks;
import me.orbitalhare.terrafirmamisc.common.item.TFMItems;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

@Mod(Terrafirmamisc.MOD_ID)
public class Terrafirmamisc {
    
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "terrafirmamisc";
    
    private @Nullable Throwable syncLoadError;
    
    public Terrafirmamisc()
    {
    
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    
        bus.addListener(this::setup);
        
        TFMItems.ITEMS.register(bus);
        TFMBlocks.BLOCKS.register(bus);
        
        TFMRecipeTypes.RECIPE_TYPES.register(bus);
        TFMRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
        TFMContainerTypes.CONTAINERS.register(bus);
        
        TFMConfig.register();
        
        
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            TFMClientEvents.init();
        }
        
    }
    
    private void setup(final FMLCommonSetupEvent event) {

        // Vanilla registries are not thread safe
        event.enqueueWork(() -> {
            TFMInteractionManager.init();
        });
        

    }
}
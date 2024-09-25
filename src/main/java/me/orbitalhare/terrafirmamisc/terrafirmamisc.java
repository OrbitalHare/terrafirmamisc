package me.orbitalhare.terrafirmamisc;

import com.mojang.logging.LogUtils;
import me.orbitalhare.terrafirmamisc.client.TFMClientEvents;
import me.orbitalhare.terrafirmamisc.common.TFMCreativeTabs;
import me.orbitalhare.terrafirmamisc.common.block.TFMBlocks;
import me.orbitalhare.terrafirmamisc.common.block.TFMFluids;
import me.orbitalhare.terrafirmamisc.common.item.TFMItems;
import me.orbitalhare.terrafirmamisc.common.worldgen.TFMFeatures;
import me.orbitalhare.terrafirmamisc.config.TFMConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(terrafirmamisc.MODID)
public class terrafirmamisc {
    public static final String MODID = "terrafirmamisc";
    public static final Logger LOGGER = LogUtils.getLogger();

    public terrafirmamisc() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TFMItems.ITEMS.register(eventBus);
        TFMBlocks.BLOCKS.register(eventBus);
        TFMFluids.FLUIDS.register(eventBus);
        TFMCreativeTabs.CREATIVE_TABS.register(eventBus);
        TFMFeatures.FEATURES.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::onClientSetup);


        TFMConfig.init();
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            TFMClientEvents.init();
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // Vanilla registries are not thread safe
        event.enqueueWork(() -> {

        });
    }

    public void onClientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {


        });
    }
}

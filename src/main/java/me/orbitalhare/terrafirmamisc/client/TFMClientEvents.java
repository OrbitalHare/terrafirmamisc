package me.orbitalhare.terrafirmamisc.client;

import me.orbitalhare.terrafirmamisc.common.TFMHelpers;
import me.orbitalhare.terrafirmamisc.common.block.TFMRocks;
import me.orbitalhare.terrafirmamisc.common.util.TFMOreDeposit;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static me.orbitalhare.terrafirmamisc.terrafirmamisc.MODID;


public class TFMClientEvents {

    public static void init()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        //bus.addListener(TFMClientEvents::onModelRegister);
        bus.addListener(TFMClientEvents::registerModels);
    }
    public static void arrayOfResourceLocationsToNetwork(FriendlyByteBuf buffer, ResourceLocation[] textures)
    {
        buffer.writeVarInt(textures.length);
        for (ResourceLocation res : textures)
        {
            buffer.writeUtf(res.toString());
        }
    }
    public static ResourceLocation identifier(String id)
    {
        return TFMClientEvents.res(MODID, id);
    }
    public static ResourceLocation res(String string)
    {
        return new ResourceLocation(string);
    }

    public static ResourceLocation res(String namespace, String path)
    {
        return new ResourceLocation(namespace, path);
    }

    public static void registerModels(ModelEvent.RegisterAdditional event)
    {
        for ( TFMOreDeposit deposit : TFMOreDeposit.values())
        {
            event.register(TFMHelpers.identifier("item/pan/" + deposit.toString().toLowerCase() + "/result"));

            for ( Rock rock : Rock.values()){
                event.register(TFMHelpers.identifier("item/pan/" + deposit.toString().toLowerCase() + "/" + rock.toString().toLowerCase() + "_full"));
                event.register(TFMHelpers.identifier("item/pan/" + deposit.toString().toLowerCase() + "/" + rock.toString().toLowerCase() + "_half"));

            }

            for (TFMRocks rocks : TFMRocks.values())
            {
                event.register(TFMHelpers.identifier("item/pan/" + deposit.toString().toLowerCase() + "/" + rocks.toString().toLowerCase() + "_full"));
                event.register(TFMHelpers.identifier("item/pan/" + deposit.toString().toLowerCase() + "/" + rocks.toString().toLowerCase() + "_half"));

            }

        }
        for (OreDeposit depositOre : OreDeposit.values())
        {
            for (TFMRocks rocks : TFMRocks.values())
            {
                event.register(TFMHelpers.identifier("item/pan/" + depositOre.toString().toLowerCase() + "/" + rocks.toString().toLowerCase() + "_full"));
                event.register(TFMHelpers.identifier("item/pan/" + depositOre.toString().toLowerCase() + "/" + rocks.toString().toLowerCase() + "_half"));

            }
        }
    }
}

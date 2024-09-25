package me.orbitalhare.terrafirmamisc.config;

import net.dries007.tfc.util.Alloy;
import net.minecraftforge.common.ForgeConfigSpec.*;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.dries007.tfc.util.Helpers;

import java.util.function.Function;

import static me.orbitalhare.terrafirmamisc.terrafirmamisc.MODID;

public class TFMConfig {

    public static final TFMConfig SERVER = register(ModConfig.Type.SERVER, TFMConfig::new);
    public static void init() {}

    private static <C> C register(ModConfig.Type type, Function<ForgeConfigSpec.Builder, C> factory)
    {
        Pair<C, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(factory);
        if (!Helpers.BOOTSTRAP_ENVIRONMENT) ModLoadingContext.get().registerConfig(type, specPair.getRight());
        return specPair.getLeft();
    }

    public static IntValue moldNailCapacity;
    public static IntValue moldRingCapacity;
    TFMConfig(Builder innerBuilder)
    {
        Function<String, Builder> builder = name -> innerBuilder.translation(MODID + ".config.server." + name);

        innerBuilder.comment("This is the config for TerraFirmaMisc");
        innerBuilder.push("molds");

        moldNailCapacity = innerBuilder.comment("Tank capacity of a Nail mold (in mB).").defineInRange("moldNailCapacity", 60, 0, Alloy.MAX_ALLOY );
        moldRingCapacity = innerBuilder.comment("Tank capacity of a Ring mold (in mB).").defineInRange("moldRingCapacity", 20, 0, Alloy.MAX_ALLOY );

        innerBuilder.pop();
    }
}

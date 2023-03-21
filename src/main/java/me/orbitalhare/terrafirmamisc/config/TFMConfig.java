package me.orbitalhare.terrafirmamisc.config;

import net.dries007.tfc.util.Alloy;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class TFMConfig {
    
    private static ForgeConfigSpec GENERAL_SPEC;
    
    public static void register() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        TFMConfig.registerServerConfig(builder);
        GENERAL_SPEC = builder.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, TFMConfig.GENERAL_SPEC, "terrafirmamisc.toml");
    }
    
    public static ForgeConfigSpec.IntValue moldNailCapacity;
    
    private static void registerServerConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("This is the config for TerraFirmaMisc");
        builder.push("molds");
    
        moldNailCapacity = builder
            .comment("Tank capacity of a Nail mold (in mB).")
            .defineInRange("moldNailCapacity", 60, 0, Alloy.MAX_ALLOY);
        
        builder.pop();
    }
}

package me.orbitalhare.terrafirmamisc.common;

import net.minecraft.resources.ResourceLocation;

import static me.orbitalhare.terrafirmamisc.Terrafirmamisc.MOD_ID;

public class TFMHelpers {
    
    public static ResourceLocation identifier(String id)
    {
        return new ResourceLocation(MOD_ID, id);
    }
    
}

package me.orbitalhare.terrafirmamisc;

import me.orbitalhare.terrafirmamisc.common.TFMHelpers;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings("unused")
public class TFMTags {
    
    public static class Blocks {
    
    }
    
    public static class Items {
        public static final TagKey<Item> STRAW_KNAPPING = create("straw_knapping");
    
        private static TagKey<Item> create(String id)
        {
            return TagKey.create(Registry.ITEM_REGISTRY, TFMHelpers.identifier(id));
        }
    
    }
}

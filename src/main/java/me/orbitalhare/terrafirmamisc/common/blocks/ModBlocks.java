package me.orbitalhare.terrafirmamisc.common.blocks;

import me.orbitalhare.terrafirmamisc.Terrafirmamisc;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;


@SuppressWarnings("unused")
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, Terrafirmamisc.MOD_ID);
    
    
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return BLOCKS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
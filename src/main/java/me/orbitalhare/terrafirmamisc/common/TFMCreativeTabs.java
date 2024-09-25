package me.orbitalhare.terrafirmamisc.common;

import me.orbitalhare.terrafirmamisc.terrafirmamisc;
import me.orbitalhare.terrafirmamisc.common.block.TFMBlocks;
import me.orbitalhare.terrafirmamisc.common.block.TFMRocks;
import me.orbitalhare.terrafirmamisc.common.item.TFMItems;
import me.orbitalhare.terrafirmamisc.common.util.MetalItemsUtil;
import me.orbitalhare.terrafirmamisc.common.util.TFMMetal;
import me.orbitalhare.terrafirmamisc.common.util.TFMOre;
import me.orbitalhare.terrafirmamisc.common.util.TFMOreDeposit;
import net.dries007.tfc.common.TFCCreativeTabs;
import net.dries007.tfc.common.blocks.DecorationBlockRegistryObject;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.SelfTests;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TFMCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, terrafirmamisc.MODID);

    public static final RegistryObject<CreativeModeTab> TERRAFIRMAMISC = register("terrafirmamisc", () -> new ItemStack(TFMItems.FIRED_NAIL_MOLD.get()), TFMCreativeTabs::fillTFMTab);

    public static void fillTFMTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {
        accept(out, TFMItems.UNFIRED_NAIL_MOLD);
        accept(out, TFMItems.FIRED_NAIL_MOLD);
        accept(out, TFMItems.UNFIRED_RING_MOLD);
        accept(out, TFMItems.FIRED_RING_MOLD);
        accept(out, TFMItems.BAUXITE_POWDER);
        accept(out, TFMItems.NATIVE_PLATINUM_POWDER);

        for (TFMRocks tfmRocks : TFMRocks.values()) {
            for (Rock.BlockType type : new Rock.BlockType[]{
                    Rock.BlockType.HARDENED,
                    Rock.BlockType.RAW,
                    Rock.BlockType.PRESSURE_PLATE,
                    Rock.BlockType.BUTTON,
                    Rock.BlockType.SPIKE,
                    Rock.BlockType.COBBLE,
                    Rock.BlockType.MOSSY_COBBLE,
                    Rock.BlockType.BRICKS,
                    Rock.BlockType.CRACKED_BRICKS,
                    Rock.BlockType.MOSSY_BRICKS,
                    Rock.BlockType.SMOOTH,
                    Rock.BlockType.CHISELED,
                    Rock.BlockType.AQUEDUCT,
                    Rock.BlockType.GRAVEL,
                    Rock.BlockType.LOOSE,
                    Rock.BlockType.MOSSY_LOOSE,
            }) {
                accept(out, TFMBlocks.ROCK_BLOCKS, tfmRocks, type);
                if (type.hasVariants()) {
                    accept(out, TFMBlocks.ROCK_DECORATIONS.get(tfmRocks).get(type));
                }
            }

            accept(out, TFMItems.BAUXITE_BRICK);
        }

        for (TFMMetal metals : TFMMetal.values()) {
            accept(out, TFMItems.METAL_FLUID_BUCKETS, metals);

            for (TFMMetal.ItemType type : TFMMetal.ItemType.values()) {
                accept(out, TFMItems.TFM_METAL_ITEMS, metals, type);
            }

            for (Metal.BlockType type : Metal.BlockType.values()) {
                accept(out, TFMBlocks.METALS, metals, type);
            }
        }

        for (MetalItemsUtil.ItemType type : MetalItemsUtil.ItemType.values()) {
            for (MetalItemsUtil.TFCMetals metals : MetalItemsUtil.TFCMetals.values()) {
                accept(out, TFMItems.METAL_ITEMS, metals, type);
            }
            for (MetalItemsUtil.FLMetals metals : MetalItemsUtil.FLMetals.values()) {
                accept(out, TFMItems.FL_METAL_ITEMS, metals, type);
            }
            for (TFMMetal metals : TFMMetal.values()) {
                accept(out, TFMItems.TFM_EXTRA_METAL_ITEMS, metals, type);
            }
        }

        for (Ore ore : Ore.values()) {
            if (ore.isGraded()) {
                TFMBlocks.CUSTOM_GRADED_ORES.values().forEach(map -> map.get(ore).values().forEach(reg -> accept(out, reg)));
            } else {
                TFMBlocks.CUSTOM_ORES.values().forEach(map -> accept(out, map, ore));
            }
        }

        accept(out, TFMBlocks.SMALL_NATIVE_PLATINUM);
        for (TFMOre ore : TFMOre.values()) {
            for (Ore.Grade grade : Ore.Grade.values()) {
                accept(out, TFMItems.CUSTOM_ORES, grade, ore);
            }
        }
        for (TFMOre ore : TFMOre.values()) {
            for (Ore.Grade grade : Ore.Grade.values()) {
                for (TFMRocks rock : TFMRocks.values()) {
                    {
                        accept(out, TFMBlocks.TFM_CUSTOM_ORES_GRADED, rock, grade, ore);
                        accept(out, TFMBlocks.FL_CUSTOM_ORES, rock, grade);
                    }
                    for (Rock cRock : Rock.values())
                    {

                        accept(out, TFMBlocks.TFC_CUSTOM_ORES_GRADED, cRock, grade, ore);
                    }
                }
            }
        }

        for (TFMOreDeposit ore : TFMOreDeposit.values())
        {
            for (TFMRocks rock : TFMRocks.values())
            {
                accept(out, TFMBlocks.TFM_CUSTOM_DEPOSITS, rock, ore);
            }
            for (Rock cRock : Rock.values())
            {
                accept(out, TFMBlocks.TFC_CUSTOM_DEPOSITS, cRock, ore);
            }
        }

        for (OreDeposit ore : OreDeposit.values())
        {
            for (TFMRocks rock : TFMRocks.values())
            {
                accept(out, TFMBlocks.CUSTOM_DEPOSITS, rock, ore);
            }
        }
    }

    public static void onBuildCreativeTab(BuildCreativeModeTabContentsEvent out)
    {
        if (out.getTab() == TFCCreativeTabs.METAL.tab().get())
        {
            for (TFMMetal metals : TFMMetal.values())
            {
                for (TFMMetal.ItemType type : TFMMetal.ItemType.values()) {
                    accept(out, TFMItems.TFM_METAL_ITEMS, metals, type);
                }

                for (Metal.BlockType type : Metal.BlockType.values())
                {
                    accept(out, TFMBlocks.METALS, metals, type);
                }
            }
            for (MetalItemsUtil.ItemType type : MetalItemsUtil.ItemType.values())
            {
                for (MetalItemsUtil.TFCMetals metals : MetalItemsUtil.TFCMetals.values())
                {
                    accept(out, TFMItems.METAL_ITEMS, metals, type);
                }
                for (MetalItemsUtil.FLMetals metals : MetalItemsUtil.FLMetals.values())
                {
                    accept(out, TFMItems.FL_METAL_ITEMS, metals, type);
                }
                for (TFMMetal metals : TFMMetal.values())
                {
                    accept(out, TFMItems.TFM_EXTRA_METAL_ITEMS, metals, type);
                }
            }
        }
        if (out.getTab() == TFCCreativeTabs.ORES.tab().get())
        {
            for (Ore ore : Ore.values())
            {
                if (ore.isGraded())
                {
                    TFMBlocks.CUSTOM_GRADED_ORES.values().forEach(map -> map.get(ore).values().forEach(reg -> accept(out, reg)));

                }
                else
                {
                    TFMBlocks.CUSTOM_ORES.values().forEach(map -> accept(out, map, ore));
                }
            }
            accept(out, TFMBlocks.SMALL_NATIVE_PLATINUM);
            for (TFMOre ore : TFMOre.values()) {
                for (Ore.Grade grade : Ore.Grade.values()) {
                    accept(out, TFMItems.CUSTOM_ORES, grade, ore);
                }
            }
            for (TFMOre ore : TFMOre.values()) {
                for (Ore.Grade grade : Ore.Grade.values()) {
                    for (TFMRocks rock : TFMRocks.values()) {
                        {
                            accept(out, TFMBlocks.TFM_CUSTOM_ORES_GRADED, rock, grade, ore);
                            accept(out, TFMBlocks.FL_CUSTOM_ORES, rock, grade);
                        }
                        for (Rock cRock : Rock.values())
                        {

                            accept(out, TFMBlocks.TFC_CUSTOM_ORES_GRADED, cRock, grade, ore);
                        }
                    }
                }
            }
        }

        else if (out.getTab() == TFCCreativeTabs.ROCKS.tab().get())
        {
            for (TFMRocks tfmRocks : TFMRocks.values())
            {
                for (Rock.BlockType type : new  Rock.BlockType[]{
                        Rock.BlockType.HARDENED,
                        Rock.BlockType.RAW,
                        Rock.BlockType.PRESSURE_PLATE,
                        Rock.BlockType.BUTTON,
                        Rock.BlockType.SPIKE,
                        Rock.BlockType.COBBLE,
                        Rock.BlockType.MOSSY_COBBLE,
                        Rock.BlockType.BRICKS,
                        Rock.BlockType.CRACKED_BRICKS,
                        Rock.BlockType.MOSSY_BRICKS,
                        Rock.BlockType.SMOOTH,
                        Rock.BlockType.CHISELED,
                        Rock.BlockType.AQUEDUCT,
                        Rock.BlockType.GRAVEL,
                        Rock.BlockType.LOOSE,
                        Rock.BlockType.MOSSY_LOOSE,
                }) {
                    accept(out, TFMBlocks.ROCK_BLOCKS, tfmRocks, type);
                    if (type.hasVariants()){
                        accept(out, TFMBlocks.ROCK_DECORATIONS.get(tfmRocks).get(type));
                    }
                }
                accept(out, TFMItems.BAUXITE_BRICK);

            }
        }
        else if (out.getTab() == TFCCreativeTabs.MISC.tab().get())
        {
            accept(out, TFMItems.UNFIRED_NAIL_MOLD);
            accept(out, TFMItems.FIRED_NAIL_MOLD);
            accept(out, TFMItems.UNFIRED_RING_MOLD);
            accept(out, TFMItems.FIRED_RING_MOLD);
            accept(out, TFMItems.BAUXITE_POWDER);
            accept(out, TFMItems.NATIVE_PLATINUM_POWDER);
        }
    }

    private static RegistryObject<CreativeModeTab> register(String name, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator displayItems)
    {
        return CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
                .icon(icon)
                .title(Component.translatable("terrafirmamisc.creative_tab." + name))
                .displayItems(displayItems)
                .build());
    }

    private static <T extends ItemLike, R extends Supplier<T>, K1, K2> void accept(CreativeModeTab.Output out, Map<K1, Map<K2, R>> map, K1 key1, K2 key2)
    {
        if (map.containsKey(key1) && map.get(key1).containsKey(key2))
        {
            out.accept(map.get(key1).get(key2).get());
        }
    }

    private static <T extends ItemLike, R extends Supplier<T>, K1, K2, K3> void accept(CreativeModeTab.Output out, Map<K1, Map<K2, Map<K3, R>>> map, K1 key1, K2 key2, K3 key3)
    {
        if (map.containsKey(key1) && map.get(key1).containsKey(key2))
        {
            out.accept(map.get(key1).get(key2).get(key3).get());
        }
    }

    private static <T extends ItemLike, R extends Supplier<T>, K> void accept(CreativeModeTab.Output out, Map<K, R> map, K key)
    {
        if (map.containsKey(key))
        {
            out.accept(map.get(key).get());
        }
    }
    private static <T extends ItemLike, R extends Supplier<T>> void accept(CreativeModeTab.Output out, R reg)
    {
        if (reg.get().asItem() == Items.AIR)
        {
            terrafirmamisc.LOGGER.error("BlockItem with no Item added to creative tab: " + reg);
            SelfTests.reportExternalError();
            return;
        }
        out.accept(reg.get());
    }

    private static void accept(CreativeModeTab.Output out, DecorationBlockRegistryObject decoration)
    {
        out.accept(decoration.stair().get());
        out.accept(decoration.slab().get());
        out.accept(decoration.wall().get());
    }


    private static <T> void consumeOurs(IForgeRegistry<T> registry, Consumer<T> consumer)
    {
        for (T value : registry)
        {
            if (Objects.requireNonNull(registry.getKey(value)).getNamespace().equals(terrafirmamisc.MODID))
            {
                consumer.accept(value);
            }
        }
    }
}
package me.orbitalhare.terrafirmamisc.common.block;

import me.orbitalhare.terrafirmamisc.terrafirmamisc;
import me.orbitalhare.terrafirmamisc.common.item.TFMItems;
import me.orbitalhare.terrafirmamisc.common.util.TFMMetal;
import me.orbitalhare.terrafirmamisc.common.util.TFMOre;
import me.orbitalhare.terrafirmamisc.common.util.TFMOreDeposit;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.DecorationBlockRegistryObject;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.GroundcoverBlock;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.RockAnvilBlock;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class TFMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, terrafirmamisc.MODID);
    public static final Map<TFMMetal, Map<Metal.BlockType, RegistryObject<Block>>> METALS = Helpers.mapOfKeys(TFMMetal.class, metal ->
            Helpers.mapOfKeys(Metal.BlockType.class, type -> type.has(Metal.Default.BISMUTH), type ->
                    register(type.createName(metal), type.create(metal), type.createBlockItem(new Item.Properties()))
            )
    );
    public static final Map<TFMMetal, RegistryObject<LiquidBlock>> METAL_FLUIDS = Helpers.mapOfKeys(TFMMetal.class, metal ->
            registerNoItem("fluid/metal/" + metal.name(), () -> new LiquidBlock(TFMFluids.METALS.get(metal).source(), Properties.copy(Blocks.LAVA).noLootTable()))
    );


    public static final RegistryObject<Block> SMALL_NATIVE_PLATINUM = register("ore/small_native_platinum", () -> GroundcoverBlock.looseOre(Properties.of().strength(0.05F, 0.0F).sound(SoundType.NETHER_ORE).noCollission()));

    //
    //
    // TFC Rock Types, TFM Ores
    public static final Map<Rock, Map<Ore.Grade, Map<TFMOre, RegistryObject<Block>>>> TFC_CUSTOM_ORES_GRADED = Helpers.mapOfKeys(Rock.class, rock ->
            Helpers.mapOfKeys(Ore.Grade.class, grade ->
                    Helpers.mapOfKeys(TFMOre.class, ore ->
                    register(("ore/" + grade.name() + "_" + ore.name() + "/" + rock.name()), () -> new Block(Properties.of().sound(SoundType.STONE).strength(3, 10).requiresCorrectToolForDrops()))
                    )
            )
    );

    public static final Map<Rock, Map<TFMOre, RegistryObject<Block>>> TFC_CUSTOM_ORES = Helpers.mapOfKeys(Rock.class, rock ->
            Helpers.mapOfKeys(TFMOre.class, ore -> !ore.isGraded(), ore ->
                    register(("ore/" + ore.name() + "/" + rock.name()), () -> new Block(Properties.of().sound(SoundType.STONE).strength(3, 10).requiresCorrectToolForDrops())
                    )
            )
    );

    public static final Map<Rock, Map<TFMOreDeposit, RegistryObject<Block>>> TFC_CUSTOM_DEPOSITS = Helpers.mapOfKeys(Rock.class, rock ->
            Helpers.mapOfKeys(TFMOreDeposit.class, ore ->
                    register("deposit/" + ore.name() + "/" + rock.name(), () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.GRAVEL).strength(rock.category().hardness(2.0f)))) // Same hardness as gravel
            )
    );

    //
    //
    // TFM Rock Types, TFM Ores
    public static final Map<TFMRocks, Map<Ore.Grade, Map<TFMOre, RegistryObject<Block>>>> TFM_CUSTOM_ORES_GRADED = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(Ore.Grade.class, grade ->
                    Helpers.mapOfKeys(TFMOre.class, ore ->
            register(("ore/" + grade.name() + "_" + ore.name() + "/" + rock.name()), () -> new Block(Properties.of().sound(SoundType.STONE).strength(3, 10).requiresCorrectToolForDrops()))
                    )
            )
    );

    public static final Map<TFMRocks, Map<TFMOre, RegistryObject<Block>>> TFM_CUSTOM_ORES = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(TFMOre.class, ore -> !ore.isGraded(), ore ->
                    register(("ore/" + ore.name() + "/" + rock.name()), () -> new Block(Properties.of().sound(SoundType.STONE).strength(3, 10).requiresCorrectToolForDrops())
                    )
            )
    );

    public static final Map<TFMRocks, Map<TFMOreDeposit, RegistryObject<Block>>> TFM_CUSTOM_DEPOSITS = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(TFMOreDeposit.class, ore ->
                    register("deposit/" + ore.name() + "/" + rock.name(), () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.GRAVEL).strength(rock.category().hardness(2.0f)))) // Same hardness as gravel
            )
    );

    //
    //
    // TFM Rock Types, TFC Ores
    public static final Map<TFMRocks, Map<Ore, RegistryObject<Block>>> CUSTOM_ORES = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(Ore.class, ore -> !ore.isGraded(), ore ->
                    register(("ore/" + ore.name() + "/" + rock.name()), () -> ore.create(rock))
            )
    );
    public static final Map<TFMRocks, Map<Ore, Map<Ore.Grade, RegistryObject<Block>>>> CUSTOM_GRADED_ORES = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(Ore.class, Ore::isGraded, ore ->
                    Helpers.mapOfKeys(Ore.Grade.class, grade ->
                            register(("ore/" + grade.name() + "_" + ore.name() + "/" + rock.name()), () -> ore.create(rock))
                    )
            )
    );
    public static final Map<TFMRocks, Map<OreDeposit, RegistryObject<Block>>> CUSTOM_DEPOSITS = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(OreDeposit.class, ore ->
                    register("deposit/" + ore.name() + "/" + rock.name(), () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.GRAVEL).strength(rock.category().hardness(2.0f)))) // Same hardness as gravel
            )
    );

    //
    //
    // TFM Rock Types, Firmalife Ores
    public static final Map<TFMRocks, Map<Ore.Grade, RegistryObject<Block>>> FL_CUSTOM_ORES = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(Ore.Grade.class, grade ->
                    register(("ore/" + grade.name() + "_chromite" + "/" + rock.name()), () -> new Block(Properties.of().sound(SoundType.STONE).strength(3, 10).requiresCorrectToolForDrops())
                    )
            )
    );


    public static final Map<TFMRocks, Map<Rock.BlockType, RegistryObject<Block>>> ROCK_BLOCKS = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(Rock.BlockType.class, type ->
                    register(("rock/" + type.name() + "/" + rock.name()), () -> type.create(rock))
            )
    );

    public static final Map<TFMRocks, Map<Rock.BlockType, DecorationBlockRegistryObject>> ROCK_DECORATIONS = Helpers.mapOfKeys(TFMRocks.class, rock ->
            Helpers.mapOfKeys(Rock.BlockType.class, Rock.BlockType::hasVariants, type -> new DecorationBlockRegistryObject(
                    register(("rock/" + type.name() + "/" + rock.name()) + "_slab", () -> type.createSlab(rock)),
                    register(("rock/" + type.name() + "/" + rock.name()) + "_stairs", () -> type.createStairs(rock)),
                    register(("rock/" + type.name() + "/" + rock.name()) + "_wall", () -> type.createWall(rock))
            ))
    );

    public static final Map<TFMRocks, RegistryObject<Block>> ROCK_ANVILS = Helpers.mapOfKeys(TFMRocks.class, rock -> rock.category() == RockCategory.IGNEOUS_EXTRUSIVE || rock.category() == RockCategory.IGNEOUS_INTRUSIVE, rock ->
            register("rock/anvil/" + rock.name(), () -> new RockAnvilBlock(ExtendedProperties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2, 10).requiresCorrectToolForDrops().blockEntity(TFCBlockEntities.ANVIL), TFMBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW)))
    );


    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return RegistrationHelpers.registerBlock(BLOCKS, TFMItems.ITEMS, name, blockSupplier, blockItemFactory);
    }


}

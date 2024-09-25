package me.orbitalhare.terrafirmamisc.common.item;

import me.orbitalhare.terrafirmamisc.terrafirmamisc;
import me.orbitalhare.terrafirmamisc.common.block.TFMFluids;
import me.orbitalhare.terrafirmamisc.common.util.MetalItemsUtil;
import me.orbitalhare.terrafirmamisc.common.util.TFMMetal;
import me.orbitalhare.terrafirmamisc.common.util.TFMOre;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.*;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static me.orbitalhare.terrafirmamisc.config.TFMConfig.moldNailCapacity;
import static me.orbitalhare.terrafirmamisc.config.TFMConfig.moldRingCapacity;

@SuppressWarnings("unused")
public class TFMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, terrafirmamisc.MODID);

    // Metal Items
    public static final Map<MetalItemsUtil.TFCMetals, Map<MetalItemsUtil.ItemType, RegistryObject<Item>>> METAL_ITEMS = Helpers.mapOfKeys(MetalItemsUtil.TFCMetals.class, metal ->
            Helpers.mapOfKeys(MetalItemsUtil.ItemType.class, type ->
                    register("metal/" + type.name() + "/" + metal.name(), () -> new Item(metal_properties()))
            )
    );

    // Firmalife Metal Items
    public static final Map<MetalItemsUtil.FLMetals, Map<MetalItemsUtil.ItemType, RegistryObject<Item>>> FL_METAL_ITEMS = Helpers.mapOfKeys(MetalItemsUtil.FLMetals.class, metal ->
            Helpers.mapOfKeys(MetalItemsUtil.ItemType.class, type ->
                    register("metal/" + type.name() + "/" + metal.name(), () -> new Item(metal_properties()))
            )
    );

    public static final Map<TFMMetal, Map<MetalItemsUtil.ItemType, RegistryObject<Item>>> TFM_EXTRA_METAL_ITEMS = Helpers.mapOfKeys(TFMMetal.class, metal ->
            Helpers.mapOfKeys(MetalItemsUtil.ItemType.class, type ->
                    register("metal/" + type.name() + "/" + metal.name(), () -> new Item(metal_properties()))
            )
    );

    public static final Map<TFMMetal, Map<TFMMetal.ItemType, RegistryObject<Item>>> TFM_METAL_ITEMS = Helpers.mapOfKeys(TFMMetal.class, metal ->
            Helpers.mapOfKeys(TFMMetal.ItemType.class, type ->
                    register("metal/" + type.name() + "/" + metal.name(), () -> type.create(metal))
            )
    );




    // Molds
    public static final RegistryObject<Item>
            UNFIRED_NAIL_MOLD = ITEMS.register("ceramic/unfired_nail_mold", () -> new Item(misc_properties())),
            FIRED_NAIL_MOLD = ITEMS.register("ceramic/nail_mold", () -> new MoldItem(moldNailCapacity, TFCTags.Fluids.USABLE_IN_INGOT_MOLD, misc_properties())),
            UNFIRED_RING_MOLD = ITEMS.register("ceramic/unfired_ring_mold", () -> new Item(misc_properties())),
            FIRED_RING_MOLD = ITEMS.register("ceramic/ring_mold", () -> new MoldItem(moldRingCapacity, TFCTags.Fluids.USABLE_IN_INGOT_MOLD, misc_properties()));

    public static final Map<TFMMetal, RegistryObject<BucketItem>> METAL_FLUID_BUCKETS = Helpers.mapOfKeys(TFMMetal.class, metal ->
            register("bucket/metal/" + metal.name(), () -> new BucketItem(TFMFluids.METALS.get(metal).source(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)))
    );

    public static final Map<Ore.Grade, Map<TFMOre, RegistryObject<Item>>> CUSTOM_ORES = Helpers.mapOfKeys(Ore.Grade.class, grade ->
            Helpers.mapOfKeys(TFMOre.class, cOre ->
            register("ore/" + grade.name() + "_" + cOre)));

    public static final RegistryObject<Item> NATIVE_PLATINUM_POWDER = ITEMS.register("powder/native_platinum", () -> new Item(misc_properties()));


    public static final RegistryObject<Item> BAUXITE_POWDER = ITEMS.register("powder/bauxite", () -> new Item(misc_properties()));
    public static final RegistryObject<Item> BAUXITE_BRICK = ITEMS.register("brick/bauxite", () -> new Item(misc_properties()));

    // Fabrics
    // public static final RegistryObject<Item> STRAW_STRING = ITEMS.register("straw_string", () -> new Item(misc_properties()));
    // public static final RegistryObject<Item> LINEN_YARN = ITEMS.register("linen_yarn", () -> new Item(misc_properties()));
    // public static final RegistryObject<Item> COTTON_YARN = ITEMS.register("cotton_yarn", () -> new Item(misc_properties()));
    // public static final RegistryObject<Item> LINEN_CLOTH = ITEMS.register("linen_cloth", () -> new Item(misc_properties()));
    // public static final RegistryObject<Item> COTTON_CLOTH = ITEMS.register("cotton_cloth", () -> new Item(misc_properties()));



    public static Item.Properties metal_properties() { return new Item.Properties(); }

    public static Item.Properties misc_properties() { return new Item.Properties(); }

    private static RegistryObject<Item> register(String name)
    {
        return register(name, () -> new Item(new Item.Properties()));
    }
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}

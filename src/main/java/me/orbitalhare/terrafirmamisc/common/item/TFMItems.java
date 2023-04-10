package me.orbitalhare.terrafirmamisc.common.item;

import me.orbitalhare.terrafirmamisc.Terrafirmamisc;

import me.orbitalhare.terrafirmamisc.util.MetalItems;
import net.dries007.tfc.common.TFCItemGroup;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.MoldItem;
import net.dries007.tfc.util.Helpers;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static me.orbitalhare.terrafirmamisc.config.TFMConfig.*;

@SuppressWarnings("unused")
public class TFMItems {
    
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Terrafirmamisc.MOD_ID);

    //Metal Items
    public static final Map<MetalItems.TFCMetals, Map<MetalItems.ItemType, RegistryObject<Item>>> METAL_ITEMS = Helpers.mapOfKeys(MetalItems.TFCMetals.class, metal ->
        Helpers.mapOfKeys(MetalItems.ItemType.class, type ->
            register("metal/" + type.name() + "/" + metal.name(), () -> new Item(metal_properties()))
        )
    );
    
    //Firmalife Metal Items
    public static final Map<MetalItems.FLMetals, Map<MetalItems.ItemType, RegistryObject<Item>>> FL_METAL_ITEMS = Helpers.mapOfKeys(MetalItems.FLMetals.class, metal ->
            Helpers.mapOfKeys(MetalItems.ItemType.class, type ->
                    register("metal/" + type.name() + "/" + metal.name(), () -> new Item(metal_properties()))
                )
            );

    
    // Molds
    public static final RegistryObject<Item>
        UNFIRED_NAIL_MOLD = ITEMS.register("ceramic/unfired_nail_mold", () -> new Item(ceramic_properties())),
        FIRED_NAIL_MOLD = ITEMS.register("ceramic/nail_mold", () -> new MoldItem(() -> moldNailCapacity.get(), TFCTags.Fluids.USABLE_IN_INGOT_MOLD, ceramic_properties()))
        ;
    
    
    public static Item.Properties metal_properties() { return new Item.Properties().tab(TFCItemGroup.METAL); }
    
    public static Item.Properties ceramic_properties() { return new Item.Properties().tab(TFCItemGroup.MISC); }
    
    
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}

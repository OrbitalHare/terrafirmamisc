package me.orbitalhare.terrafirmamisc.common.util;

import me.orbitalhare.terrafirmamisc.common.TFMHelpers;
import me.orbitalhare.terrafirmamisc.common.block.TFMBlocks;
import net.dries007.tfc.common.TFCArmorMaterials;
import net.dries007.tfc.common.TFCTiers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistryMetal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.util.NonNullFunction;

import java.util.Locale;
import java.util.function.Supplier;

public enum TFMMetal implements RegistryMetal {
    ALUMINIUM(0xFFDADAF1, MapColor.COLOR_LIGHT_GRAY),
    TITANIUM(0xFFC5E1E2, MapColor.COLOR_LIGHT_BLUE),
    PLATINUM(0x9FFF80, MapColor.COLOR_LIGHT_GRAY);
    private final String serializedName;
    private final int color;
    private final MapColor mapColor;
    private final ResourceLocation sheet;

    TFMMetal(int color, MapColor mapColor)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.color = color;
        this.mapColor = mapColor;
        this.sheet = TFMHelpers.identifier("block/metal/full/" + serializedName);
    }
    @Override
    public Tier toolTier() {
        return TFCTiers.STEEL;
    }

    @Override
    public ArmorMaterial armorTier() {
        return TFCArmorMaterials.RED_STEEL;
    }

    @Override
    public Metal.Tier metalTier() {
        return Metal.Tier.TIER_VI;
    }

    @Override
    public Supplier<Block> getFullBlock() {
        return TFMBlocks.METALS.get(this).get(Metal.BlockType.BLOCK);
    }

    @Override
    public MapColor mapColor() {
        return mapColor;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    public int getColor() {
        return color;
    }

    public ResourceLocation getSheet() {
        return sheet;
    }
    public enum ItemType
    {
        // Generic
        INGOT(metal -> new Item(new Item.Properties())),
        DOUBLE_INGOT(metal -> new Item(new Item.Properties())),
        SHEET(metal -> new Item(new Item.Properties())),
        DOUBLE_SHEET(metal -> new Item(new Item.Properties())),
        ROD(metal -> new Item(new Item.Properties()));

        private final NonNullFunction<TFMMetal, Item> itemFactory;

        ItemType(NonNullFunction<TFMMetal, Item> itemFactory)
        {
            this.itemFactory = itemFactory;
        }

        public Item create(TFMMetal metal)
        {
            return itemFactory.apply(metal);
        }
    }
}

package me.orbitalhare.terrafirmamisc.common.block;
import java.util.Objects;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.*;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.util.registry.RegistryRock;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;

import static net.dries007.tfc.common.blocks.rock.Ore.*;

public enum TFMRocks implements RegistryRock {
    BAUXITE("BAUXITE", RockDisplayCategory.SEDIMENTARY, MapColor.TERRACOTTA_ORANGE, SandBlockType.RED);

    public static final TFMRocks[] VALUES = values();

    private final String serializedName;
    private final RockDisplayCategory category;
    private final MapColor color;
    private final SandBlockType sandType;

    TFMRocks(String serializedName, RockDisplayCategory category, MapColor color, SandBlockType sandType) {

        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.category = category;
        this.color = color;
        this.sandType = sandType;
    }

    public SandBlockType getSandType() {
        return sandType;
    }


    @Override
    public RockDisplayCategory displayCategory() {
        return category;
    }



    @Override
    public Supplier<? extends Block> getBlock(Rock.BlockType type) {
        return TFMBlocks.ROCK_BLOCKS.get(this).get(type);
    }

    @Override
    public Supplier<? extends Block> getAnvil() {
        return TFMBlocks.ROCK_ANVILS.get(this);
    }

    @Override
    public Supplier<? extends SlabBlock> getSlab(Rock.BlockType type) {
        return TFMBlocks.ROCK_DECORATIONS.get(this).get(type).slab();
    }

    @Override
    public Supplier<? extends StairBlock> getStair(Rock.BlockType type) {
        return TFMBlocks.ROCK_DECORATIONS.get(this).get(type).stair();
    }

    @Override
    public Supplier<? extends WallBlock> getWall(Rock.BlockType type) {
        return TFMBlocks.ROCK_DECORATIONS.get(this).get(type).wall();
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

}

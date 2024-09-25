package me.orbitalhare.terrafirmamisc.common.util;

import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.util.registry.RegistryRock;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.util.registry.RegistryRock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public enum TFMOre {

    NATIVE_PLATINUM(true);

    private final boolean graded;

    private TFMOre(boolean graded) {
        this.graded = graded;
    }

    public boolean isGraded() {
        return this.graded;
    }

    public static enum Grade {
        POOR,
        NORMAL,
        RICH;

        private static final TFMOre.Grade[] VALUES = values();

        private Grade() {
        }

        public static TFMOre.Grade valueOf(int i) {
            return i >= 0 && i < VALUES.length ? VALUES[i] : NORMAL;
        }
    }

}


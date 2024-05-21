package com.idark.valoria.registries.levelgen.tree;

import com.idark.valoria.registries.levelgen.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.grower.*;
import net.minecraft.world.level.levelgen.feature.*;
import org.jetbrains.annotations.*;

public class EldritchTree extends AbstractMegaTreeGrower{

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers){
        return LevelGen.ELDRITCH_TREE;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource pRandom){
        return LevelGen.FANCY_ELDRITCH_TREE;
    }
}
package com.idark.valoria.registries.block.types;

import com.idark.valoria.registries.BlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.phys.shapes.Shapes;

public class VioletSproutPlantBlock extends GrowingPlantBodyBlock {

    boolean pGlow;

    public VioletSproutPlantBlock(Properties pProperties, Boolean pGlow) {
        super(pProperties, Direction.UP, Shapes.block(), true);
        this.pGlow = pGlow;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return pGlow ? (GrowingPlantHeadBlock) BlockRegistry.GLOW_VIOLET_SPROUT.get() : (GrowingPlantHeadBlock) BlockRegistry.VIOLET_SPROUT.get();
    }
}

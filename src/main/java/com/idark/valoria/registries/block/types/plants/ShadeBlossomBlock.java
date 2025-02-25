package com.idark.valoria.registries.block.types.plants;

import com.idark.valoria.client.particle.ParticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShadeBlossomBlock extends Block{
    public ShadeBlossomBlock(Properties pProperties){
        super(pProperties);
    }

    private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public BlockState updateShape(BlockState p_154713_, Direction p_154714_, BlockState p_154715_, LevelAccessor p_154716_, BlockPos p_154717_, BlockPos p_154718_){
        return p_154714_ == Direction.DOWN && !this.canSurvive(p_154713_, p_154716_, p_154717_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_154713_, p_154714_, p_154715_, p_154716_, p_154717_, p_154718_);
    }

    public boolean canSurvive(BlockState p_154709_, LevelReader p_154710_, BlockPos p_154711_){
        return Block.canSupportCenter(p_154710_, p_154711_.below(), Direction.UP) && !p_154710_.isWaterAt(p_154711_);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_222503_, Level p_222504_, BlockPos p_222505_, RandomSource p_222506_){
        int i = p_222505_.getX();
        int j = p_222505_.getY();
        int k = p_222505_.getZ();
        double d0 = (double)i + p_222506_.nextDouble();
        double d1 = (double)j + 0.7D;
        double d2 = (double)k + p_222506_.nextDouble();

        p_222504_.addParticle(ParticleRegistry.FIREFLY.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    public VoxelShape getShape(BlockState p_154699_, BlockGetter p_154700_, BlockPos p_154701_, CollisionContext p_154702_){
        return SHAPE;
    }
}

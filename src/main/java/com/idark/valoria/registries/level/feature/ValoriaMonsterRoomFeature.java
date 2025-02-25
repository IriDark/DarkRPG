package com.idark.valoria.registries.level.feature;

import com.idark.valoria.registries.BlockRegistry;
import com.idark.valoria.registries.EntityTypeRegistry;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.slf4j.Logger;

import java.util.function.Predicate;

public class ValoriaMonsterRoomFeature extends Feature<NoneFeatureConfiguration>{
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityType<?>[] MOBS = new EntityType[]{EntityType.SKELETON, EntityTypeRegistry.SHADEWOOD_SPIDER.get(), EntityTypeRegistry.FLESH_SENTINEL.get()};
    private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();

    public ValoriaMonsterRoomFeature(Codec<NoneFeatureConfiguration> pCodec){
        super(pCodec);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     *
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext){
        Predicate<BlockState> predicate = Feature.isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE);
        BlockPos blockpos = pContext.origin();
        RandomSource randomsource = pContext.random();
        WorldGenLevel worldgenlevel = pContext.level();
        int i = 3;
        int j = randomsource.nextInt(2) + 2;
        int k = -j - 1;
        int l = j + 1;
        int i1 = -1;
        int j1 = 4;
        int k1 = randomsource.nextInt(2) + 2;
        int l1 = -k1 - 1;
        int i2 = k1 + 1;
        int j2 = 0;

        for(int k2 = k; k2 <= l; ++k2){
            for(int l2 = -1; l2 <= 4; ++l2){
                for(int i3 = l1; i3 <= i2; ++i3){
                    BlockPos blockpos1 = blockpos.offset(k2, l2, i3);
                    boolean flag = worldgenlevel.getBlockState(blockpos1).isSolid();
                    if(l2 == -1 && !flag){
                        return false;
                    }

                    if(l2 == 4 && !flag){
                        return false;
                    }

                    if((k2 == k || k2 == l || i3 == l1 || i3 == i2) && l2 == 0 && worldgenlevel.isEmptyBlock(blockpos1) && worldgenlevel.isEmptyBlock(blockpos1.above())){
                        ++j2;
                    }
                }
            }
        }

        if(j2 >= 1 && j2 <= 5){
            for(int k3 = k; k3 <= l; ++k3){
                for(int i4 = 3; i4 >= -1; --i4){
                    for(int k4 = l1; k4 <= i2; ++k4){
                        BlockPos blockpos3 = blockpos.offset(k3, i4, k4);
                        BlockState blockstate = worldgenlevel.getBlockState(blockpos3);
                        if(k3 != k && i4 != -1 && k4 != l1 && k3 != l && i4 != 4 && k4 != i2){
                            if(!blockstate.is(Blocks.CHEST) && !blockstate.is(Blocks.SPAWNER)){
                                this.safeSetBlock(worldgenlevel, blockpos3, AIR, predicate);
                            }
                        }else if(blockpos3.getY() >= worldgenlevel.getMinBuildHeight() && !worldgenlevel.getBlockState(blockpos3.below()).isSolid()){
                            worldgenlevel.setBlock(blockpos3, AIR, 2);
                        }else if(blockstate.isSolid() && !blockstate.is(Blocks.CHEST)){
                            if(i4 == -1 && randomsource.nextInt(4) != 0){
                                this.safeSetBlock(worldgenlevel, blockpos3, BlockRegistry.voidCrackedBrick.get().defaultBlockState(), predicate);
                            }else{
                                this.safeSetBlock(worldgenlevel, blockpos3, BlockRegistry.voidBrick.get().defaultBlockState(), predicate);
                            }
                        }
                    }
                }
            }

            for(int l3 = 0; l3 < 2; ++l3){
                for(int j4 = 0; j4 < 3; ++j4){
                    int l4 = blockpos.getX() + randomsource.nextInt(j * 2 + 1) - j;
                    int i5 = blockpos.getY();
                    int j5 = blockpos.getZ() + randomsource.nextInt(k1 * 2 + 1) - k1;
                    BlockPos blockpos2 = new BlockPos(l4, i5, j5);
                    if(worldgenlevel.isEmptyBlock(blockpos2)){
                        int j3 = 0;

                        for(Direction direction : Direction.Plane.HORIZONTAL){
                            if(worldgenlevel.getBlockState(blockpos2.relative(direction)).isSolid()){
                                ++j3;
                            }
                        }

                        if(j3 == 1){
                            this.safeSetBlock(worldgenlevel, blockpos2, StructurePiece.reorient(worldgenlevel, blockpos2, Blocks.CHEST.defaultBlockState()), predicate);
                            RandomizableContainerBlockEntity.setLootTable(worldgenlevel, randomsource, blockpos2, BuiltInLootTables.SIMPLE_DUNGEON);
                            break;
                        }
                    }
                }
            }

            this.safeSetBlock(worldgenlevel, blockpos, Blocks.SPAWNER.defaultBlockState(), predicate);
            BlockEntity blockentity = worldgenlevel.getBlockEntity(blockpos);
            if(blockentity instanceof SpawnerBlockEntity){
                SpawnerBlockEntity spawnerblockentity = (SpawnerBlockEntity)blockentity;
                spawnerblockentity.setEntityId(this.randomEntityId(randomsource), randomsource);
            }else{
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", blockpos.getX(), blockpos.getY(), blockpos.getZ());
            }

            return true;
        }else{
            return false;
        }
    }

    private EntityType<?> randomEntityId(RandomSource pRandom){
        return net.minecraftforge.common.DungeonHooks.getRandomDungeonMob(pRandom);
    }
}

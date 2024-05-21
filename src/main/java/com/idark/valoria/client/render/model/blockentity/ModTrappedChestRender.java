package com.idark.valoria.client.render.model.blockentity;

import com.idark.valoria.*;
import com.idark.valoria.registries.block.entity.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.*;

public class ModTrappedChestRender extends ChestRenderer<ModTrappedChestBlockEntity>{

    public static final ResourceLocation CHEST_SHEET = new ResourceLocation("textures/atlas/chest.png");

    public ModTrappedChestRender(BlockEntityRendererProvider.Context pContext){
        super(pContext);
    }

    private static Material chestMaterial(String pChestName){
        return new Material(CHEST_SHEET, new ResourceLocation(Valoria.ID, "entity/chest/" + pChestName));
    }

    @Override
    public @NotNull Material getMaterial(@NotNull ModTrappedChestBlockEntity tile, @NotNull ChestType type){
        Block block = tile.getBlockState().getBlock();
        if(type.name().equals("SINGLE")){
            return chestMaterial(ForgeRegistries.BLOCKS.getKey(block).getPath());
        }else{
            return chestMaterial(ForgeRegistries.BLOCKS.getKey(block).getPath() + "_" + type.name().toLowerCase());
        }
    }
}

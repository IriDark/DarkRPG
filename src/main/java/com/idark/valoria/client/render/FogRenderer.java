package com.idark.valoria.client.render;

import com.idark.valoria.block.ModBlocks;
import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

public class FogRenderer {

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onFogRender(ViewportEvent.RenderFog e) {
            BlockState blockState = e.getCamera().getBlockAtCamera();
            if (!Minecraft.getInstance().options.getCameraType().isFirstPerson() && e.getCamera().getBlockAtCamera().is(ModBlocks.QUICKSAND.get())) {
                e.setCanceled(true);
                e.setNearPlaneDistance(0.0F);
                e.setFarPlaneDistance(1.5F);
            }

            if (Minecraft.getInstance().options.getCameraType().isFirstPerson() && e.getCamera().getBlockAtCamera().is(ModBlocks.QUICKSAND.get())) {
                e.setCanceled(true);
                e.setNearPlaneDistance(0.0F);
                e.setFarPlaneDistance(1.5F);
            }

            if (!blockState.liquid() && Minecraft.getInstance().player.level().dimension().toString().toLowerCase(Locale.ROOT).equals("resourcekey[minecraft:dimension / valoria:the_valoria]")) {
                e.setCanceled(true);
                e.setNearPlaneDistance(0.1F);
                e.setFarPlaneDistance(42.5F);
                e.setFogShape(FogShape.CYLINDER);
            }
        }

        @SubscribeEvent
        public static void onFogColor(ViewportEvent.ComputeFogColor e) {
            BlockState blockState = e.getCamera().getBlockAtCamera();
            if (!Minecraft.getInstance().options.getCameraType().isFirstPerson() && e.getCamera().getBlockAtCamera().is(ModBlocks.QUICKSAND.get())) {
                e.setRed(0.57f);
                e.setGreen(0.48f);
                e.setBlue(0.34f);
            }

            if (Minecraft.getInstance().options.getCameraType().isFirstPerson() && e.getCamera().getBlockAtCamera().is(ModBlocks.QUICKSAND.get())) {
                e.setRed(0.57f);
                e.setGreen(0.48f);
                e.setBlue(0.34f);
            }

            if (!blockState.liquid() && Minecraft.getInstance().player.level().dimension().toString().toLowerCase(Locale.ROOT).equals("resourcekey[minecraft:dimension / valoria:the_valoria]")) {
                e.setRed(0.091f);
                e.setGreen(0.069f);
                e.setBlue(0.132f);
            }
        }
    }
}
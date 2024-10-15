package com.idark.valoria.core.shaders;

import com.idark.valoria.Valoria;
import com.idark.valoria.client.render.tile.ValoriaPortalRenderer;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;

import static mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes.*;

@OnlyIn(Dist.CLIENT)
public class ShaderRegistry {
    public static ShaderInstance VALORIA_PORTAL;
    public static ShaderInstance getValoriaPortal() {
        return VALORIA_PORTAL;
    }
    public static final RenderStateShard.ShaderStateShard VALORIA_PORTAL_SHADER = new RenderStateShard.ShaderStateShard(ShaderRegistry::getValoriaPortal);
    public static final RenderType VALORIA_PORTAL_RENDER_TYPE = RenderType.create(Valoria.ID + ":valoria_portal", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(VALORIA_PORTAL_SHADER).setWriteMaskState(COLOR_WRITE).setTransparencyState(NORMAL_TRANSPARENCY).setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(ValoriaPortalRenderer.BACKGROUND_LOC, false, false).add(ValoriaPortalRenderer.LAYER_LOC, false, false).build()).createCompositeState(false));
    public static RenderType valoriaPortal() {
        return VALORIA_PORTAL_RENDER_TYPE;
    }

    public static void registerRenderTypes(FMLClientSetupEvent event) {
        addTranslucentRenderType(ShaderRegistry.VALORIA_PORTAL_RENDER_TYPE);
    }

    public static void shaderRegistry(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), new ResourceLocation(Valoria.ID, "valoria_portal"), DefaultVertexFormat.POSITION), shader -> ShaderRegistry.VALORIA_PORTAL = shader);
    }
}

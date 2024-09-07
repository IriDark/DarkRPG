package com.idark.valoria.client.render.entity;

import com.idark.valoria.registries.entity.projectile.*;
import com.idark.valoria.registries.item.interfaces.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.*;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class AbstractValoriaArrowRenderer<T extends AbstractValoriaArrow> extends ArrowRenderer<T>{

    public AbstractValoriaArrowRenderer(EntityRendererProvider.Context context){
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(T arrow){
        if(arrow instanceof IProjectileTexture texture){
            return texture.getTexture();
        }

        return null;
    }
}
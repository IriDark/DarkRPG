package com.idark.darkrpg.client.render.curio.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nonnull;

public class HandsModel extends BipedModel<LivingEntity> {

  public ModelRenderer hands;
  public HandsModel() {
    super(1.0F);
    this.texWidth = 32;
    this.texHeight = 32;
    this.rightArm  = new ModelRenderer(this, 0, 0);
    this.rightArm .setPos(-5.0F, 2.0F, 0.0F);
	this.rightArm .addBox(-3.0F, 4.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.3F);
    this.leftArm  = new ModelRenderer(this, 0, 0);
    this.leftArm .mirror = true;
    this.leftArm .setPos(5.0F, 2.0F, 0.0F);
	this.leftArm .addBox(-1.0F, 4.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.3F);
   }

  @Override
  public void renderToBuffer(@Nonnull MatrixStack matrixStack, @Nonnull IVertexBuilder vertexBuilder,
    int light, int overlay, float red, float green, float blue, float alpha) {
    this.rightArm.render(matrixStack, vertexBuilder, light, overlay);
	this.leftArm.render(matrixStack, vertexBuilder, light, overlay);
  }
}
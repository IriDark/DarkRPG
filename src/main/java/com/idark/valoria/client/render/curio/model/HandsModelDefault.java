package com.idark.valoria.client.render.curio.model;

import com.google.common.collect.ImmutableList;
import com.idark.valoria.Valoria;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class HandsModelDefault extends HumanoidModel<LivingEntity> {
    public static ModelLayerLocation HANDS_MODEL = new ModelLayerLocation(new ResourceLocation(Valoria.ID, "hands"), "main");

    public ModelPart root, right_glove, left_glove;

    public HandsModelDefault(ModelPart root) {
        super(root);
        this.root = root;
        this.right_glove = root.getChild("right_glove");
        this.left_glove = root.getChild("left_glove");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Empty ones to prevent crashing
        PartDefinition head = root.addOrReplaceChild("head", new CubeListBuilder(), PartPose.ZERO);
        PartDefinition hat = root.addOrReplaceChild("hat", new CubeListBuilder(), PartPose.ZERO);
        PartDefinition body = root.addOrReplaceChild("body", new CubeListBuilder(), PartPose.ZERO);
        PartDefinition right_arm = root.addOrReplaceChild("right_arm", new CubeListBuilder(), PartPose.ZERO);
        PartDefinition left_arm = root.addOrReplaceChild("left_arm", new CubeListBuilder(), PartPose.ZERO);
        PartDefinition right_leg = root.addOrReplaceChild("right_leg", new CubeListBuilder(), PartPose.ZERO);
        PartDefinition left_leg = root.addOrReplaceChild("left_leg", new CubeListBuilder(), PartPose.ZERO);

        PartDefinition right_glove = root.addOrReplaceChild("right_glove", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F))
                .texOffs(0, 24).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        PartDefinition left_glove = root.addOrReplaceChild("left_glove", CubeListBuilder.create().texOffs(12, 12).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity instanceof LivingEntity) {
            right_glove.copyFrom(this.leftArm);
            left_glove.copyFrom(this.rightArm);
        }
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.right_glove, this.left_glove);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
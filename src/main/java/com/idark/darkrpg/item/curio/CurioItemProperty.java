package com.idark.darkrpg.item.curio;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.idark.darkrpg.DarkRPG;
import com.idark.darkrpg.client.render.curio.HandsRenderer;
import com.idark.darkrpg.client.render.curio.model.BeltModel;
import com.idark.darkrpg.client.render.curio.model.HandsModel;
import com.idark.darkrpg.client.render.curio.model.NecklaceModel;
import com.idark.darkrpg.entity.model.GoblinModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CurioItemProperty extends Item implements ICurioItem, ICurioTexture {

    private static final ResourceLocation EMPTY = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/empty.png");
    private static final ResourceLocation IRON_AMBER = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_amber.png");
    private static final ResourceLocation IRON_DIAMOND = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_diamond.png");
    private static final ResourceLocation IRON_EMERALD = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_emerald.png");
    private static final ResourceLocation IRON_RUBY = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_ruby.png");
    private static final ResourceLocation IRON_SAPPHIRE = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_sapphire.png");
    private static final ResourceLocation IRON_ARMOR = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_armor.png");
    private static final ResourceLocation IRON_HEALTH = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_health.png");
    private static final ResourceLocation IRON_WEALTH = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/iron_necklace_wealth.png");
    private static final ResourceLocation GOLDEN_AMBER = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_amber.png");
    private static final ResourceLocation GOLDEN_DIAMOND = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_diamond.png");
    private static final ResourceLocation GOLDEN_EMERALD = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_emerald.png");
    private static final ResourceLocation GOLDEN_RUBY = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_ruby.png");
    private static final ResourceLocation GOLDEN_SAPPHIRE = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_sapphire.png");
    private static final ResourceLocation GOLDEN_ARMOR = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_armor.png");
    private static final ResourceLocation GOLDEN_HEALTH = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_health.png");
    private static final ResourceLocation GOLDEN_WEALTH = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/golden_necklace_wealth.png");
    private static final ResourceLocation NETHERITE_AMBER = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_amber.png");
    private static final ResourceLocation NETHERITE_DIAMOND = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_diamond.png");
    private static final ResourceLocation NETHERITE_EMERALD = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_emerald.png");
    private static final ResourceLocation NETHERITE_RUBY = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_ruby.png");
    private static final ResourceLocation NETHERITE_SAPPHIRE = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_sapphire.png");
    private static final ResourceLocation NETHERITE_ARMOR = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_armor.png");
    private static final ResourceLocation NETHERITE_HEALTH = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_health.png");
    private static final ResourceLocation NETHERITE_WEALTH = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/necklace/netherite_necklace_wealth.png");

    private static final ResourceLocation GLOVES_LEATHER = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/leather_gloves.png");
    private static final ResourceLocation GLOVES_IRON = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/iron_gloves.png");
    private static final ResourceLocation GLOVES_GOLD = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/golden_gloves.png");
    private static final ResourceLocation GLOVES_DIAMOND = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/diamond_gloves.png");
    private static final ResourceLocation GLOVES_NETHERITE = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/netherite_gloves.png");

    private static final ResourceLocation GLOVES_LEATHER_SLIM = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/leather_gloves_slim.png");
    private static final ResourceLocation GLOVES_IRON_SLIM = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/iron_gloves_slim.png");
    private static final ResourceLocation GLOVES_GOLD_SLIM = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/golden_gloves_slim.png");
    private static final ResourceLocation GLOVES_DIAMOND_SLIM = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/diamond_gloves_slim.png");
    private static final ResourceLocation GLOVES_NETHERITE_SLIM = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/gloves/netherite_gloves_slim.png");

    private static final ResourceLocation BELT_TEXTURE = new ResourceLocation(DarkRPG.MOD_ID, "textures/entity/leather_belt.png");

    public AccessoryType type;
    public AccessoryGem gem;
    public AccessoryMaterial material;

    public CurioItemProperty(AccessoryType type, AccessoryGem gem, AccessoryMaterial material, Item.Properties properties) {
        super(properties);
        this.type = type;
        this.gem = gem;
        this.material = material;
    }

    public AccessoryType getAccessoryType() {
        return this.type;
    }

    public AccessoryGem getAccessoryGem() {
        return this.gem;
    }

    public AccessoryMaterial getAccessoryMaterial() {
        return this.material;
    }

    @Nonnull
    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, 1.0f, 1.0f);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slot, ItemStack stack) {
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        // Reciving Gem Type & gives atts
        switch (gem) {
            case NONE:
                atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 0.5, AttributeModifier.Operation.ADDITION));
                break;
            case AMBER:
                atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 1, AttributeModifier.Operation.ADDITION));
                break;
            case DIAMOND:
                atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 4, AttributeModifier.Operation.ADDITION));
                break;
            case EMERALD:
                atts.put(Attributes.LUCK, new AttributeModifier(uuid, "bonus", 1, AttributeModifier.Operation.ADDITION));
                break;
            case RUBY:
                atts.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "bonus", 1, AttributeModifier.Operation.ADDITION));
                break;
            case SAPPHIRE:
                atts.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "bonus", 0.1, AttributeModifier.Operation.ADDITION));
                break;

            case HEALTH:
                if (material == AccessoryMaterial.IRON) {
                    atts.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "bonus", 1, AttributeModifier.Operation.ADDITION));
                    break;
                } else {
                    atts.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "bonus", 2.5, AttributeModifier.Operation.ADDITION));
                    break;
                }
            case ARMOR:
                if (material == AccessoryMaterial.IRON) {
                    atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 3, AttributeModifier.Operation.ADDITION));
                    break;
                } else {
                    atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 6, AttributeModifier.Operation.ADDITION));
                    atts.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "bonus", 0.5, AttributeModifier.Operation.ADDITION));
                    break;
                }

            case TOUGH:
                atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 1.5, AttributeModifier.Operation.ADDITION));
                atts.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "bonus", 1, AttributeModifier.Operation.ADDITION));
                break;
            case TANK:
                atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 5, AttributeModifier.Operation.ADDITION));
                atts.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "bonus", 1.5, AttributeModifier.Operation.ADDITION));
                break;
            case WEALTH:
                if (material == AccessoryMaterial.IRON) {
                    atts.put(Attributes.LUCK, new AttributeModifier(uuid, "bonus", 1.5, AttributeModifier.Operation.ADDITION));
                    break;
                } else {
                    atts.put(Attributes.LUCK, new AttributeModifier(uuid, "bonus", 3, AttributeModifier.Operation.ADDITION));
                    break;
                }

            case BELT:
                atts.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus", 0.5, AttributeModifier.Operation.ADDITION));
                CuriosApi.getCuriosHelper().addSlotModifier(atts, "charm", uuid, 2.0, AttributeModifier.Operation.ADDITION);
                break;
        }

        return atts;
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        Player player = (Player) livingEntity;
        if (gem == AccessoryGem.AMBER) {
            if (!player.level().isClientSide()) {
                boolean hasPlayerEffect = !Objects.equals(player.getEffect(MobEffects.DIG_SPEED), null);
                if (!hasPlayerEffect) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200));
                }
            }
        }

        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }

    @Override
    public ResourceLocation getTexture(ItemStack stack, LivingEntity entity) {
        switch (type) {
            case NECKLACE:
                return switch (material) {
                    case IRON -> switch (gem) {
                        case NONE, BELT, TANK, TOUGH -> null;
                        case AMBER -> IRON_AMBER;
                        case DIAMOND -> IRON_DIAMOND;
                        case EMERALD -> IRON_EMERALD;
                        case RUBY -> IRON_RUBY;
                        case SAPPHIRE -> IRON_SAPPHIRE;
                        case ARMOR -> IRON_ARMOR;
                        case HEALTH -> IRON_HEALTH;
                        case WEALTH -> IRON_WEALTH;
                    };

                    case GOLD -> switch (gem) {
                        case NONE, BELT, TANK, TOUGH -> null;
                        case AMBER -> GOLDEN_AMBER;
                        case DIAMOND -> GOLDEN_DIAMOND;
                        case EMERALD -> GOLDEN_EMERALD;
                        case RUBY -> GOLDEN_RUBY;
                        case SAPPHIRE -> GOLDEN_SAPPHIRE;
                        case ARMOR -> GOLDEN_ARMOR;
                        case HEALTH -> GOLDEN_HEALTH;
                        case WEALTH -> GOLDEN_WEALTH;
                    };

                    case NETHERITE -> switch (gem) {
                        case NONE, BELT, TANK, TOUGH -> null;
                        case AMBER -> NETHERITE_AMBER;
                        case DIAMOND -> NETHERITE_DIAMOND;
                        case EMERALD -> NETHERITE_EMERALD;
                        case RUBY -> NETHERITE_RUBY;
                        case SAPPHIRE -> NETHERITE_SAPPHIRE;
                        case ARMOR -> NETHERITE_ARMOR;
                        case HEALTH -> NETHERITE_HEALTH;
                        case WEALTH -> NETHERITE_WEALTH;
                    };

                    default -> null;
                };

            case GLOVES:
                if (!HandsRenderer.isDefault2) {
                    return switch (material) {
                        case LEATHER -> GLOVES_LEATHER_SLIM;
                        case IRON -> GLOVES_IRON_SLIM;
                        case GOLD -> GLOVES_GOLD_SLIM;
                        case DIAMOND -> GLOVES_DIAMOND_SLIM;
                        case NETHERITE -> GLOVES_NETHERITE_SLIM;
                    };
                } else {
                    return switch (material) {
                        case LEATHER -> GLOVES_LEATHER;
                        case IRON -> GLOVES_IRON;
                        case GOLD -> GLOVES_GOLD;
                        case DIAMOND -> GLOVES_DIAMOND;
                        case NETHERITE -> GLOVES_NETHERITE;
                    };
                }
            case BELT:
                return BELT_TEXTURE;
        }

        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltip, flags);
        if (gem == AccessoryGem.AMBER) {
            tooltip.add(Component.translatable("tooltip.darkrpg.amber").withStyle(ChatFormatting.GRAY));
        } else if (material == AccessoryMaterial.GOLD) {
            tooltip.add(Component.translatable("tooltip.darkrpg.golden").withStyle(ChatFormatting.GRAY));
        } else if (type == AccessoryType.BELT) {
            tooltip.add(Component.translatable("tooltip.darkrpg.belt").withStyle(ChatFormatting.GRAY));
        }

        tooltip.add(Component.translatable("tooltip.darkrpg.rmb_equip").withStyle(ChatFormatting.GREEN));
    }
}
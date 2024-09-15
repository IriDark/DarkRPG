package com.idark.valoria.registries.item.types.ranged;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.idark.valoria.core.enums.ModItemTier;
import com.idark.valoria.registries.AttributeRegistry;
import com.idark.valoria.registries.entity.projectile.SpectralBladeEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class SpectralBladeItem extends SwordItem {
    private final Multimap<Attribute, AttributeModifier> atr;

    public SpectralBladeItem(int damage, float speed, Item.Properties builderIn) {
        super(ModItemTier.NONE, damage, speed, builderIn);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 3, AttributeModifier.Operation.ADDITION));
        builder.put(AttributeRegistry.PROJECTILE_DAMAGE.get(), new AttributeModifier(UUID.fromString("5334b818-69d4-417e-b4b8-1869d4917e29"), "Tool modifier", 6.0F, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -1.9F, AttributeModifier.Operation.ADDITION));
        this.atr = builder.build();
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.atr : super.getDefaultAttributeModifiers(equipmentSlot);
    }


    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player playerEntity) {
            int i = this.getUseDuration(stack) - timeLeft;
            if (i >= 6 && playerEntity.getXRot() > -55 && playerEntity.getXRot() < 65) {
                if (!level.isClientSide) {
                    stack.hurtAndBreak(10, playerEntity, (player) -> player.broadcastBreakEvent(entityLiving.getUsedItemHand()));
                    SpectralBladeEntity spectral = new SpectralBladeEntity(level, playerEntity, stack);
                    spectral.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), (float) playerEntity.getZ() * 5, 2.5F + (float) 0 * 0.5F, 3.2F);
                    if (playerEntity.getAbilities().instabuild) {
                        spectral.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(spectral);
                    level.playSound(playerEntity, spectral, SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.0F);
                    playerEntity.getCooldowns().addCooldown(this, 75);
                }

                playerEntity.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!playerIn.isShiftKeyDown()) {
            playerIn.startUsingItem(handIn);
            return InteractionResultHolder.consume(itemstack);
        }

        return InteractionResultHolder.pass(itemstack);
    }

    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltip, flags);
        tooltip.add(Component.translatable("tooltip.valoria.kunai").withStyle(ChatFormatting.GRAY));
    }
}

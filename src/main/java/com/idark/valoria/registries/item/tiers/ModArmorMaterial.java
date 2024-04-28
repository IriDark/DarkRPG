package com.idark.valoria.registries.item.tiers;

import com.idark.valoria.Valoria;
import com.idark.valoria.registries.ItemsRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {

    // Helmet, Chestplate, Leggings, Boots
    COBALT("cobalt", 46, new int[]{2, 8, 6, 1}, 18,
            SoundEvents.ARMOR_EQUIP_IRON, 1.2f, 0.05f, () -> Ingredient.of(ItemsRegistry.COBALT_INGOT.get())),
    SAMURAI("samurai", 55, new int[]{5, 9, 8, 4}, 16,
            SoundEvents.ARMOR_EQUIP_IRON, 2.5f, 0.0f, () -> Ingredient.of(ItemsRegistry.ANCIENT_INGOT.get())),
    NATURE("nature", 66, new int[]{6, 10, 9, 5}, 17,
            SoundEvents.ARMOR_EQUIP_IRON, 3f, 0.0f, () -> Ingredient.of(ItemsRegistry.NATURE_INGOT.get())),
    DEPTH("depth", 72, new int[]{6, 11, 9, 6}, 15,
            SoundEvents.ARMOR_EQUIP_IRON, 3.6f, 0.0f, () -> Ingredient.of(ItemsRegistry.AQUARIUS_INGOT.get())),
    INFERNAL("infernal", 76, new int[]{7, 12, 9, 7}, 14,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4.5f, 0.1f, () -> Ingredient.of(ItemsRegistry.INFERNAL_INGOT.get())),
    AWAKENED_VOID("awakened_void", 82, new int[]{8, 10, 9, 8}, 10,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 8f, 0.2f, () -> Ingredient.of(ItemsRegistry.VOID_INGOT.get())),
    PHANTASM("phantasm", 96, new int[]{15, 30, 20, 10}, 30,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 30f, 0.6f, () -> Ingredient.of(ItemsRegistry.ILLUSION_STONE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 16, 13};

    ModArmorMaterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Valoria.ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
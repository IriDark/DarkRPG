package com.idark.darkrpg.item;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTier implements Tier {

	//1 Harvest 2 Uses 3 Efficiency 4 Damage 5 Enchant
    //OTHER
    COBALT(1, 160, 5f, 0f, 18,
	    () -> Ingredient.of(ModItems.COBALT_INGOT.get())),
    //ELEMENTAL
    NATURE(3, 2400, 10f, 0f, 17,
	    () -> Ingredient.of(ModItems.NATURE_INGOT.get())),
	AQUARIUS(4, 2600, 11f, 0f, 15,
	    () -> Ingredient.of(ModItems.AQUARIUS_INGOT.get())),
    INFERNAL(5, 2800, 12f, 0f, 16,
	    () -> Ingredient.of(ModItems.INFERNAL_INGOT.get()));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    ModItemTier(int harvestLevel, int maxUses, float efficiency,
	        float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
	        this.harvestLevel = harvestLevel;
	        this.maxUses = maxUses;
	        this.efficiency = efficiency;
	        this.attackDamage = attackDamage;
	        this.enchantability = enchantability;
	        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
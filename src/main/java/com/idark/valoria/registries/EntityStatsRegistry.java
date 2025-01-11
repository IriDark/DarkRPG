package com.idark.valoria.registries;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.*;

public class EntityStatsRegistry{
    public static AttributeSupplier HAUNTED_MERCHANT = register(40, 6).add(Attributes.FOLLOW_RANGE, 12).build();
    public static AttributeSupplier MANNEQUIN = register().add(Attributes.MAX_HEALTH, 1).add(Attributes.KNOCKBACK_RESISTANCE, 1).build();

    //monsters - overworld
    public static AttributeSupplier GOBLIN = register(25, 5, 0.17).build();
    public static AttributeSupplier TROLL = register(30, 12).build();
    public static AttributeSupplier DRAUGR = register(30, 15).add(Attributes.ARMOR, 5).add(Attributes.ARMOR_TOUGHNESS, 2).add(Attributes.FOLLOW_RANGE, 20).build();
    public static AttributeSupplier SWAMP_WANDERER = register(35, 11).add(Attributes.KNOCKBACK_RESISTANCE, new Random().nextDouble() * 0.05F).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, new Random().nextDouble() * 0.25D + 0.5D).build();
    public static AttributeSupplier SCOURGE = register(50, 5, 0.125).add(Attributes.KNOCKBACK_RESISTANCE, new Random().nextDouble() * 0.05F).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, new Random().nextDouble() * 0.25D + 0.5D).build();
    public static AttributeSupplier NECROMANCER = register(250, 5).build();
    public static AttributeSupplier ENT = register(40, 9).build();

    //minions - overworld
    public static AttributeSupplier UNDEAD = registerFlying(12, 8.25, 0.85).add(Attributes.FOLLOW_RANGE, 8).build();

    //monsters - nether
    public static AttributeSupplier DEVIL = register(40, 4).add(Attributes.FOLLOW_RANGE, 12).build();

    //monsters - valoria
    public static AttributeSupplier SHADEWOOD_SPIDER = register(40, 15, 0.35).add(Attributes.FOLLOW_RANGE, 20).build();
    public static AttributeSupplier CORRUPTED_TROLL = register(65, 16).build();

    //minions - valoria
    public static AttributeSupplier FLESH_SENTINEL = registerFlying(20, 12.5, 0.85).add(Attributes.FOLLOW_RANGE, 8).build();

    public static AttributeSupplier.Builder register() {
        return Mob.createMobAttributes();
    }

    public static AttributeSupplier.Builder register(double health, double damage) {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, health).add(Attributes.ATTACK_DAMAGE, damage).add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    public static AttributeSupplier.Builder register(double health, double damage, double speed) {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, health).add(Attributes.ATTACK_DAMAGE, damage).add(Attributes.MOVEMENT_SPEED, speed);
    }

    public static AttributeSupplier.Builder registerFlying(double health, double damage, double speed) {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, health).add(Attributes.ATTACK_DAMAGE, damage).add(Attributes.FLYING_SPEED, speed);
    }
}

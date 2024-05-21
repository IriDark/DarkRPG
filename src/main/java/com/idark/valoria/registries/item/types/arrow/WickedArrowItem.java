package com.idark.valoria.registries.item.types.arrow;

import com.idark.valoria.registries.entity.projectile.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

public class WickedArrowItem extends ArrowItem{
    public WickedArrowItem(Item.Properties pProperties){
        super(pProperties);
    }

    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter){
        return new WickedArrow(pLevel, pShooter, pStack);
    }
}
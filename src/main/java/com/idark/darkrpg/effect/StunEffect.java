package com.idark.darkrpg.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class StunEffect extends Effect {
	
    public StunEffect() {
        super(EffectType.HARMFUL, 0x6CCE31);
		addAttributeModifier(Attributes.MOVEMENT_SPEED, "1107DE5E-7AE8-2030-840A-21B21F160890", (double)-1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
   		addAttributeModifier(Attributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", (double)-1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
       	addAttributeModifier(Attributes.ATTACK_SPEED, "55FCED67-E92A-486E-9800-B47F202C4386", (double)-1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        super.applyEffectTick(entityLivingBaseIn, amplifier);
        entityLivingBaseIn.flyingSpeed = 0;
        entityLivingBaseIn.yRot = entityLivingBaseIn.yRotO;
        entityLivingBaseIn.xRot = entityLivingBaseIn.xRotO;
        entityLivingBaseIn.yHeadRot = entityLivingBaseIn.yHeadRotO;
    }
	
	@Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
    return true;
    }
}
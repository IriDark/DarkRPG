package com.idark.valoria.registries.entity.living.minions;

import com.idark.valoria.core.interfaces.*;
import com.idark.valoria.registries.entity.living.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.targeting.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.scores.*;

import javax.annotation.*;
import java.awt.*;
import java.util.*;

public abstract class AbstractMultiAttackMinion extends MultiAttackMob implements TraceableEntity, Allied{
    @Nullable
    public LivingEntity owner;
    @Nullable
    public BlockPos boundOrigin;
    public boolean hasLimitedLife;
    public int limitedLifeTicks;
    public static final Map<EntityType<? extends AbstractMinionEntity>, Color> minionColors = new HashMap<>();

    protected AbstractMultiAttackMinion(EntityType<? extends MultiAttackMob> pEntityType, Level pLevel){
        super(pEntityType, pLevel);
    }

    public void tick(){
        super.tick();
        this.setNoGravity(true);
        if(this.hasLimitedLife && --this.limitedLifeTicks <= 0){
            if(this.level() instanceof ServerLevel serv){
                spawnDisappearParticles(serv);
                this.remove(RemovalReason.KILLED);
            }
        }

        if(this.shouldRenderAtSqrDistance(4)){
            spawnParticlesTrail();
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount){
        if(pSource.getEntity() instanceof Allied  && !(this.owner instanceof Player)) return false;
        return super.hurt(pSource, pAmount);
    }

    @Override
    public boolean isAlliedTo(Entity pEntity){
        return super.isAlliedTo(pEntity) || (pEntity instanceof Allied && !(this.owner instanceof Player));
    }

    public boolean canAttack(LivingEntity pTarget){
        return !this.isOwnedBy(pTarget) && super.canAttack(pTarget) && !isAlliedTo(pTarget);
    }

    /**
     * Server sided
     */
    public void spawnDisappearParticles(ServerLevel serverLevel){
    }

    public void spawnParticlesTrail(){
    }

    public static Color getColor(EntityType<? extends AbstractMinionEntity> entityType){
        return minionColors.getOrDefault(entityType, Color.WHITE);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound){
        super.readAdditionalSaveData(pCompound);
        if(pCompound.contains("BoundX")){
            this.boundOrigin = new BlockPos(pCompound.getInt("BoundX"), pCompound.getInt("BoundY"), pCompound.getInt("BoundZ"));
        }

        if(pCompound.contains("LifeTicks")){
            this.setLimitedLife(pCompound.getInt("LifeTicks"));
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound){
        super.addAdditionalSaveData(pCompound);
        if(this.boundOrigin != null){
            pCompound.putInt("BoundX", this.boundOrigin.getX());
            pCompound.putInt("BoundY", this.boundOrigin.getY());
            pCompound.putInt("BoundZ", this.boundOrigin.getZ());
        }

        if(this.hasLimitedLife){
            pCompound.putInt("LifeTicks", this.limitedLifeTicks);
        }
    }

    @Nullable
    public LivingEntity getOwner(){
        return this.owner;
    }

    public void setOwner(LivingEntity pOwner){
        this.owner = pOwner;
    }

    public Team getTeam(){
        LivingEntity livingentity = this.getOwner();
        if(livingentity != null){
            return livingentity.getTeam();
        }

        return super.getTeam();
    }

    public boolean isOwnedBy(LivingEntity pEntity){
        return pEntity == this.getOwner();
    }

    @Nullable
    public BlockPos getBoundOrigin(){
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos pBoundOrigin){
        this.boundOrigin = pBoundOrigin;
    }

    public void setLimitedLife(int pLimitedLifeTicks){
        this.hasLimitedLife = true;
        this.limitedLifeTicks = pLimitedLifeTicks;
    }

    class CopyOwnerTargetGoal extends TargetGoal{
        private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

        public CopyOwnerTargetGoal(PathfinderMob pMob){
            super(pMob, false);
        }

        private LivingEntity getOwnerTarget(){
            LivingEntity lastHurt = AbstractMultiAttackMinion.this.owner.getLastHurtByMob();
            if(lastHurt != null){
                return lastHurt;
            }

            return AbstractMultiAttackMinion.this.owner.getLastHurtMob();
        }

        public boolean canUse(){
            return AbstractMultiAttackMinion.this.owner != null && getOwnerTarget() != null && this.canAttack(getOwnerTarget(), this.copyOwnerTargeting);
        }

        public void start(){
            AbstractMultiAttackMinion.this.setTarget(getOwnerTarget());
            super.start();
        }
    }
}
package com.idark.valoria.registries.entity.decoration;

import com.idark.valoria.registries.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.gameevent.*;
import org.jetbrains.annotations.*;

import javax.annotation.Nullable;

public class CustomChestBoatEntity extends CustomBoatEntity implements HasCustomInventoryScreen, ContainerEntity{

    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
    @Nullable
    private ResourceLocation lootTable;
    private long lootTableSeed;

    public CustomChestBoatEntity(EntityType<? extends CustomBoatEntity> type, Level level){
        super(type, level);
    }

    public CustomChestBoatEntity(Level level, double x, double y, double z){
        this(EntityTypeRegistry.CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected float getSinglePassengerXOffset(){
        return 0.15F;
    }

    @Override
    protected int getMaxPassengers(){
        return 1;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag){
        super.addAdditionalSaveData(tag);
        this.addChestVehicleSaveData(tag);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag){
        super.readAdditionalSaveData(tag);
        this.readChestVehicleSaveData(tag);
    }

    @Override
    public void destroy(DamageSource damageSource){
        super.destroy(damageSource);
        this.chestVehicleDestroyed(damageSource, this.level(), this);
    }

    @Override
    public void remove(Entity.RemovalReason reason){
        if(!this.level().isClientSide() && reason.shouldDestroy()){
            Containers.dropContents(this.level(), this, this);
        }

        super.remove(reason);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand){
        if(this.canAddPassenger(player) && !player.isSecondaryUseActive()){
            return super.interact(player, hand);
        }else{
            InteractionResult interactionresult = this.interactWithContainerVehicle(player);
            if(interactionresult.consumesAction()){
                this.gameEvent(GameEvent.CONTAINER_OPEN, player);
                PiglinAi.angerNearbyPiglins(player, true);
            }

            return interactionresult;
        }
    }

    @Override
    public void openCustomInventoryScreen(Player player){
        player.openMenu(this);
        if(!player.level().isClientSide()){
            this.gameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinAi.angerNearbyPiglins(player, true);
        }

    }

    @Override
    public @NotNull Item getDropItem(){
        return switch(this.getCustomBoatEntityType()){
            case SHADEWOOD -> ItemsRegistry.SHADEWOOD_CHEST_BOAT_ITEM.get();
            case ELDRITCH -> ItemsRegistry.ELDRITCH_CHEST_BOAT_ITEM.get();
        };
    }

    @Override
    public void clearContent(){
        this.clearChestVehicleContent();
    }

    @Override
    public int getContainerSize(){
        return 27;
    }

    @Override
    public ItemStack getItem(int index){
        return this.getChestVehicleItem(index);
    }

    @Override
    public ItemStack removeItem(int index, int amount){
        return this.removeChestVehicleItem(index, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index){
        return this.removeChestVehicleItemNoUpdate(index);
    }

    @Override
    public void setItem(int index, ItemStack stack){
        this.setChestVehicleItem(index, stack);
    }

    @Override
    public SlotAccess getSlot(int index){
        return this.getChestVehicleSlot(index);
    }

    @Override
    public void setChanged(){
    }

    @Override
    public boolean stillValid(Player player){
        return this.isChestVehicleStillValid(player);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player){
        if(this.lootTable != null && player.isSpectator()){
            return null;
        }else{
            this.unpackLootTable(inventory.player);
            return ChestMenu.threeRows(id, inventory, this);
        }
    }

    public void unpackLootTable(@Nullable Player player){
        this.unpackChestVehicleLootTable(player);
    }

    @Nullable
    @Override
    public ResourceLocation getLootTable(){
        return this.lootTable;
    }

    @Override
    public void setLootTable(@Nullable ResourceLocation location){
        this.lootTable = location;
    }

    @Override
    public long getLootTableSeed(){
        return this.lootTableSeed;
    }

    @Override
    public void setLootTableSeed(long seed){
        this.lootTableSeed = seed;
    }

    @Override
    public NonNullList<ItemStack> getItemStacks(){
        return this.itemStacks;
    }

    @Override
    public void clearItemStacks(){
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }
}
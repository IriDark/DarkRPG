package com.idark.valoria;

import com.idark.valoria.core.capability.*;
import com.idark.valoria.core.network.*;
import com.idark.valoria.core.network.packets.*;
import com.idark.valoria.registries.*;
import com.idark.valoria.util.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.*;
import top.theillusivec4.curios.api.*;

import java.util.stream.*;

@SuppressWarnings("removal")
public class Events{

    @SubscribeEvent
    public void attachEntityCaps(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player)
            event.addCapability(new ResourceLocation(Valoria.ID, "pages"), new UnloackbleCap());
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e){
        if(ValoriaUtils.isIDE){
            ItemStack itemStack = e.getItemStack();
            Stream<ResourceLocation> itemTagStream = itemStack.getTags().map(TagKey::location);
            if(Minecraft.getInstance().options.advancedItemTooltips){
                if(Screen.hasControlDown()){
                    if(!itemStack.getTags().toList().isEmpty()){
                        e.getToolTip().add(Component.empty());
                        e.getToolTip().add(Component.literal("ItemTags: " + itemTagStream.toList()).withStyle(ChatFormatting.DARK_GRAY));
                    }

                    if(itemStack.getItem() instanceof BlockItem blockItem){
                        BlockState blockState = blockItem.getBlock().defaultBlockState();
                        Stream<ResourceLocation> blockTagStream = blockState.getTags().map(TagKey::location);
                        if(!blockState.getTags().map(TagKey::location).toList().isEmpty()){
                            if(itemStack.getTags().toList().isEmpty())
                                e.getToolTip().add(Component.empty());

                            e.getToolTip().add(Component.literal("BlockTags: " + blockTagStream.toList()).withStyle(ChatFormatting.DARK_GRAY));
                        }
                    }
                }else if(!itemStack.getTags().toList().isEmpty() || itemStack.getItem() instanceof BlockItem blockItem && !blockItem.getBlock().defaultBlockState().getTags().toList().isEmpty()){
                    e.getToolTip().add(Component.empty());
                    e.getToolTip().add(Component.literal("Press [Control] to get tags info").withStyle(ChatFormatting.GRAY));
                }
            }
        }
    }

    @SubscribeEvent
    public void disableBlock(ShieldBlockEvent event){
        if(event.getDamageSource().getDirectEntity() instanceof Player player){
            LivingEntity mob = event.getEntity();
            ItemStack weapon = player.getMainHandItem();
            if(!weapon.isEmpty() && weapon.is(TagsRegistry.CAN_DISABLE_SHIELD) && mob instanceof Player attacked){
                attacked.disableShield(true);
            }
        }
    }

    @SubscribeEvent
    public void critDamage(CriticalHitEvent event){
        if(CuriosApi.getCuriosHelper().findEquippedCurio(ItemsRegistry.RUNE_OF_ACCURACY.get(), event.getEntity()).isPresent()){
            if(RandomUtil.percentChance(0.1f)){
                event.getTarget().hurt(event.getEntity().level().damageSources().playerAttack(event.getEntity()), (float)(event.getEntity().getAttributeValue(Attributes.ATTACK_DAMAGE) * 1.5f));
            }
        }
    }

    @SubscribeEvent
    public void onClone(PlayerEvent.Clone event){
        Capability<IUnlockable> PAGE = IUnlockable.INSTANCE;

        event.getOriginal().reviveCaps();
        event.getEntity().getCapability(PAGE).ifPresent((k) -> event.getOriginal().getCapability(PAGE).ifPresent((o) ->
        ((INBTSerializable<CompoundTag>)k).deserializeNBT(((INBTSerializable<CompoundTag>)o).serializeNBT())));
        if(!event.getEntity().level().isClientSide){
            PacketHandler.sendTo((ServerPlayer)event.getEntity(), new UnlockableUpdatePacket(event.getEntity()));
        }
    }

    @SubscribeEvent
    public void registerCustomAI(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof LivingEntity && !event.getLevel().isClientSide){
            if(event.getEntity() instanceof Player){
                PacketHandler.sendTo((ServerPlayer)event.getEntity(), new UnlockableUpdatePacket((Player)event.getEntity()));
            }
        }
    }
}
package com.idark.darkrpg.util;
import com.idark.darkrpg.DarkRPG;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {
    private final static String MODID = DarkRPG.MOD_ID;

    public static TagKey<Item> item(final ResourceLocation name) {
        return TagKey.create(Registries.ITEM, name);
    }

    public static TagKey<PaintingVariant> painting(final ResourceLocation name) {
        return TagKey.create(Registries.PAINTING_VARIANT, name);
    }

    public static final TagKey<Item> POTIONS = item(new ResourceLocation(MODID, "potions"));
    public static final TagKey<Item> ALCOHOL = item(new ResourceLocation(MODID, "alcohol"));
    public static final TagKey<Item> RUM = item(new ResourceLocation(MODID, "rum"));
    public static final TagKey<Item> GEODES = item(new ResourceLocation(MODID, "geodes"));
    public static final TagKey<PaintingVariant> MODDED = painting(new ResourceLocation(MODID, "painting"));
}
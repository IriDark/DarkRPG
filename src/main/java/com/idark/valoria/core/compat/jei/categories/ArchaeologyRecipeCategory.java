package com.idark.valoria.core.compat.jei.categories;

import com.idark.valoria.core.compat.jei.*;
import com.idark.valoria.registries.*;
import com.idark.valoria.registries.item.recipe.*;
import mezz.jei.api.gui.builder.*;
import mezz.jei.api.gui.widgets.*;
import mezz.jei.api.helpers.*;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;

import java.util.*;

public class ArchaeologyRecipeCategory extends AbstractRecipeCategory<ArchaeologyRecipe>{
    public static final int width = 82;
    public static final int height = 34;

    public ArchaeologyRecipeCategory(IGuiHelper helper) {
        super(ModRecipeTypes.ARCHAEOLOGY, Component.translatable("jei.valoria.archaeology_table"), helper.createDrawableItemLike(BlockRegistry.ARCHAEOLOGY_TABLE.get()), width, height);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ArchaeologyRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        ItemStack resultStack = recipe.getResultItem(RegistryAccess.EMPTY);

        builder.addInputSlot(1, 9).setStandardSlotBackground().addItemStacks(Arrays.asList(recipeIngredients.get(0).getItems()));
        builder.addOutputSlot(61, 9).setOutputSlotBackground().addItemStack(resultStack);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, ArchaeologyRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(26, 9);
    }

    @Override
    public ResourceLocation getRegistryName(ArchaeologyRecipe recipe) {
        return recipe.getId();
    }
}
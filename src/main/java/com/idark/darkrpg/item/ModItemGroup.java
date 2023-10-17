package com.idark.darkrpg.item;

import com.idark.darkrpg.DarkRPG;
import com.idark.darkrpg.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class ModItemGroup extends ItemGroup {

    public ModItemGroup(String label){
	super(label);
        this.setBackgroundSuffix("darkrpg_item_search.png");
        this.hideTitle();
    }

	@Override
	public ResourceLocation getTabsImage() {
		return new ResourceLocation(DarkRPG.MOD_ID, "textures/gui/tabs_darkrpg.png");
	}
	
    public static final ItemGroup DARKRPG_GROUP = new ModItemGroup("DarkRPGModTab") {
		@Override
		public ItemStack makeIcon() {
		return new ItemStack(ModItems.NATURE_PICKAXE.get());
		}
	};
	
    public static final ItemGroup DARKRPG_BLOCKS_GROUP = new ModItemGroup("DarkRPGBlocksModTab") {
		@Override
		public ItemStack makeIcon() {
		return new ItemStack(ModItems.VOID_STONE.get());
		}
	};
}
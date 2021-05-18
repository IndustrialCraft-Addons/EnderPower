package com.nofrfa.enderpower.tools;

import com.nofrfa.enderpower.misc.ModUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Pickaxe extends ItemPickaxe {
    public Pickaxe(ToolMaterial material, String name, CreativeTabs tab) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ModUtils.enchanterHelper(worldIn, playerIn, handIn, Enchantment.getEnchantmentByID(35));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}

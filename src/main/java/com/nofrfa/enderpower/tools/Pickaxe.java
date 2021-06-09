package com.nofrfa.enderpower.tools;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.nofrfa.enderpower.misc.ModUtils;
import ic2.core.IC2;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class Pickaxe extends ItemPickaxe {
    public Pickaxe(ToolMaterial material, String name, CreativeTabs tab) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(ModUtils.helperHasMode(stack, "digMode")) {
            if(GuiScreen.isShiftKeyDown()){
                switch(stack.getTagCompound().getInteger("digMode")) {
                    case 2:
                        tooltip.add(String.format("%s - %s", "SafeTile", ChatFormatting.GREEN + "Active"));
                    case 1:
                        tooltip.add("Dig Size: 3x3");
                        break;
                }
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if(!world.isRemote) {
            if(IC2.keyboard.isSneakKeyDown(player)) {
                ModUtils.helperChangeMode(player, hand, "digMode", 3);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return ModUtils.helperHasMode(stack, "digMode");
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if(ModUtils.helperHasMode(stack, "digMode"))
            ModUtils.helperBlockDestroyer(stack, world, pos, entityLiving, "pickaxe", 1, "digMode");
        return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
    }
}

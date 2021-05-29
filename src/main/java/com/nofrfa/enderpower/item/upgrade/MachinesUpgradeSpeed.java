package com.nofrfa.enderpower.item.upgrade;

import com.nofrfa.enderpower.misc.Configs;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class MachinesUpgradeSpeed extends Item {
    public MachinesUpgradeSpeed(String name, int setMaxStack, CreativeTabs setTab) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(setTab);
        this.setMaxStackSize(setMaxStack);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(GuiScreen.isShiftKeyDown()){
            int boostSpeed = stack.getCount() * Configs.GeneralSettings.Upgrades.FastQ.upgrades_fastq_boostSpeed;
            int boostEnergy = stack.getCount() * Configs.GeneralSettings.Upgrades.FastQ.upgrades_fastq_increaseEnergyConsume;

            tooltip.add(I18n.format("upgrade.use_gas_converter"));
            tooltip.add(String.format("%s%d %s", I18n.format("upgrade.speed2"), boostEnergy, I18n.format("more.eu_t")));
            tooltip.add(String.format("%s%d %s", I18n.format("upgrade.speed"), boostSpeed, I18n.format("more.sec")));
        } else {
            tooltip.add(I18n.format("deterrent.shift"));
        }
    }
}
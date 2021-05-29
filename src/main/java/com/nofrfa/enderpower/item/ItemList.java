package com.nofrfa.enderpower.item;

import com.nofrfa.enderpower.misc.Configs;
import com.nofrfa.enderpower.misc.ModUtils;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.misc.tabs.TabsList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemList extends Item {
    public ItemList(String name, int setMaxStack) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TabsList.EXtabs);
        this.setMaxStackSize(setMaxStack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_energy))) {
            if(GuiScreen.isShiftKeyDown()){
                tooltip.add(I18n.format("upgrade.use_generator"));
                tooltip.add(String.format("%s%s", I18n.format("upgrade.energy"), ModUtils.getString(Configs.GeneralSettings.Upgrades.Energy.energy_upgrade_boost)));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_capacity))) {
            if(GuiScreen.isShiftKeyDown()){
                tooltip.add(I18n.format("upgrade.use_generator"));
                tooltip.add(String.format("%s%s", I18n.format("upgrade.capacity"), ModUtils.getString(Configs.GeneralSettings.Upgrades.Capacity.capacity_upgrade_boost)));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_gift_energy))) {
            if(GuiScreen.isShiftKeyDown()){
                tooltip.add(I18n.format("upgrade.use_generator"));
                tooltip.add(String.format("%s%s", I18n.format("upgrade.gift_energy"), Configs.GeneralSettings.Upgrades.GiftEnergy.giftEnergy_upgrade_boost));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }
    }
}
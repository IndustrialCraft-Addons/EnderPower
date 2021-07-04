package com.nofrfa.enderpower.item;

import com.nofrfa.enderpower.misc.EPConfig;
import com.nofrfa.enderpower.misc.ModUtils;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.misc.tabs.TabsList;
import ic2.core.init.Localization;
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

    public ItemList(String name, int setMaxStack, int maxDamage, boolean canRepair) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxDamage(maxDamage);
        this.setCreativeTab(TabsList.EXtabs);
        this.setMaxStackSize(setMaxStack);
        if(!canRepair)
            this.setNoRepair();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_energy))) {
            if(GuiScreen.isShiftKeyDown()){
                tooltip.add(I18n.format("upgrade.use_generator"));
                tooltip.add(String.format("%s%s", I18n.format("upgrade.energy"), ModUtils.getStringFromNumber(EPConfig.GeneralSettings.Upgrades.Energy.energy_upgrade_boost)));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_capacity))) {
            if(GuiScreen.isShiftKeyDown()){
                tooltip.add(I18n.format("upgrade.use_generator"));
                tooltip.add(String.format("%s%s", I18n.format("upgrade.capacity"), ModUtils.getStringFromNumber(EPConfig.GeneralSettings.Upgrades.Capacity.capacity_upgrade_boost)));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_gift_energy))) {
            if(GuiScreen.isShiftKeyDown()){
                tooltip.add(I18n.format("upgrade.use_generator"));
                tooltip.add(String.format("%s%s", I18n.format("upgrade.gift_energy"), EPConfig.GeneralSettings.Upgrades.GiftEnergy.giftEnergy_upgrade_boost));
                tooltip.add(String.format("%s%smb/t", I18n.format("upgrade.gift_energy_debuff"), EPConfig.GeneralSettings.Upgrades.GiftEnergy.giftEnergy_upgrade_addConsumeGas));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(ModUtils.getEqualsItems(stack,
                ItemsRegistry.COMPONENT_1, ItemsRegistry.COMPONENT_2, ItemsRegistry.COMPONENT_3, ItemsRegistry.COMPONENT_4,
                ItemsRegistry.COMPONENT_5, ItemsRegistry.COMPONENT_6, ItemsRegistry.COMPONENT_7)
        ) {
            if(!stack.isItemDamaged())
                tooltip.add(String.format("%s %s / %s", Localization.translate("ic2.reactoritem.durability"), stack.getMaxDamage() - stack.getItemDamage(), stack.getMaxDamage()));
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent))) {
            if(GuiScreen.isShiftKeyDown()){
                tooltip.add(I18n.format("deterrent.information.line1"));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent_filled))) {
            if(GuiScreen.isShiftKeyDown()){
                if(!stack.hasTagCompound()) {
                    tooltip.add(String.format("%s %s", I18n.format("deterrent_filled.information.line1"), I18n.format("deterrent_filled.null_info")));
                } else {
                    String get_nbt = stack.getTagCompound().getString("inside");
                    tooltip.add(String.format("%s %s", I18n.format("deterrent_filled.information.line1"), get_nbt));

                    if(get_nbt.equals("enderfish"))
                        tooltip.add(I18n.format("deterrent.error"));
                }
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_speed))) {
            if(GuiScreen.isShiftKeyDown()){
                int boostSpeed = stack.getCount() * EPConfig.GeneralSettings.Upgrades.FastQ.upgrades_fastq_boostSpeed;
                int boostEnergy = stack.getCount() * EPConfig.GeneralSettings.Upgrades.FastQ.upgrades_fastq_increaseEnergyConsume;

                tooltip.add(I18n.format("upgrade.use_gas_converter"));
                tooltip.add(String.format("%s%d %s", I18n.format("upgrade.speed2"), boostEnergy, I18n.format("more.eu_t")));
                tooltip.add(String.format("%s%d %s", I18n.format("upgrade.speed"), boostSpeed, I18n.format("more.sec")));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_Volecy))) {
            if(GuiScreen.isShiftKeyDown()){
                int boostEnergy = stack.getCount() * EPConfig.GeneralSettings.Upgrades.Volecy.upgrades_volecy_increaseEnergyConsume;
                int boostMilibackets = stack.getCount() * EPConfig.GeneralSettings.Upgrades.Volecy.upgrades_volecy_mbBoost;

                tooltip.add(I18n.format("upgrade.use_gas_converter"));
                tooltip.add(String.format("%s%d %s", I18n.format("upgrade.volecy"), boostEnergy, I18n.format("more.eu_t")));
                tooltip.add(String.format("%s%d %s", I18n.format("upgrade.volecy2"), boostMilibackets, I18n.format("more.mb_out")));
                tooltip.add(I18n.format("upgrade.alert"));
            } else {
                tooltip.add(I18n.format("deterrent.shift"));
            }
        }
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        if(itemStack.isItemEqual(new ItemStack(ItemsRegistry.GENERATOR_sp_0)))
            return 39600;

        if(itemStack.isItemEqual(new ItemStack(ItemsRegistry.GENERATOR_sp_1)))
            return 26000;

        return -1;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return !ModUtils.getEqualsItems(
                stack,
                ItemsRegistry.COMPONENT_1, ItemsRegistry.COMPONENT_2, ItemsRegistry.COMPONENT_3, ItemsRegistry.COMPONENT_4,
                ItemsRegistry.COMPONENT_5, ItemsRegistry.COMPONENT_6, ItemsRegistry.COMPONENT_7, ItemsRegistry.ITEM_erbi_amulet
        );
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if(ModUtils.getEqualsItems(stack,
                ItemsRegistry.COMPONENT_1, ItemsRegistry.COMPONENT_2, ItemsRegistry.COMPONENT_3, ItemsRegistry.COMPONENT_4, ItemsRegistry.COMPONENT_5, ItemsRegistry.COMPONENT_6, ItemsRegistry.COMPONENT_7)
        )
            return true;

        return stack.isItemDamaged();
    }
}
package com.nofrfa.enderpower.world.ore;

import com.nofrfa.enderpower.misc.tabs.TabsList;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class NeifritOre extends BlockOre {
    public NeifritOre() {
        this.setCreativeTab(TabsList.EXtabs);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 4);
        this.setRegistryName("neifrit_ore");
        this.setResistance(17.0F);
        this.setUnlocalizedName("enderpower.neifrit_ore");
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return 1;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if(GuiScreen.isShiftKeyDown()){
            tooltip.add(I18n.format("tile.enderpower.neifrit_ore.description"));
            tooltip.add(I18n.format("tile.enderpower.neifrit_ore.description2"));
        } else {
            tooltip.add(I18n.format("deterrent.shift"));
        }
    }
}

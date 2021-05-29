package com.nofrfa.enderpower.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockList extends Block {
    public BlockList(String name, Material material, int opacity, float hardness,float resistanse, String hravLevel, int level, CreativeTabs tabSet) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tabSet);
        this.setLightOpacity(opacity);
        this.setHardness(hardness);
        this.setResistance(resistanse);
        this.setHarvestLevel(hravLevel, level);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.blocks.BlockList;
import com.nofrfa.enderpower.fluids.GasBlockErbi;
import com.nofrfa.enderpower.misc.tabs.TabsList;
import com.nofrfa.enderpower.world.ore.NeifritOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlocksRegister {
    public static Block
            BLOCK_UltimateMachineCasing = new BlockList("ultimate_machine_casing", Material.IRON, 255, 10.0F, 100.0F, "pickaxe", 3, TabsList.EXtabs),
            GAS_ERBI = new GasBlockErbi(FluidsRegister.GAS_ERBI);
    public static BlockOre NeifritOre = new NeifritOre();


    public static void register() {
        setRegister(BLOCK_UltimateMachineCasing);
        setRegister(GAS_ERBI);
        setRegister(NeifritOre);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        setRender(BLOCK_UltimateMachineCasing);
        setRender(GAS_ERBI);
        setRender(NeifritOre);
    }

    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
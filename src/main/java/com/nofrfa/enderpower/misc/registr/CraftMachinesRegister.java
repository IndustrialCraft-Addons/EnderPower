package com.nofrfa.enderpower.misc.registr;

import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CraftMachinesRegister {
    private static final ItemStack
            carbon_plate = IC2Items.getItem("crafting", "carbon_plate"),
            spadiy = new ItemStack(ItemsRegistry.INGOT_spadiy),
            iron_dust = IC2Items.getItem("dust", "iron"),
            spadiy_plate = new ItemStack(ItemsRegistry.PLATE_spadiy),
            spadiy_plate_compress = new ItemStack(ItemsRegistry.PLATE_spadiy_compres),
            neifrit = new ItemStack(ItemsRegistry.INGOT_nefrit),
            neifrit_plate = new ItemStack(ItemsRegistry.PLATE_neifrit),
            neifrit_plate_compres = new ItemStack(ItemsRegistry.PLATE_neifrit_compres),
            neifrit_casing = new ItemStack(ItemsRegistry.CASING_neifrit, 2),
            neifritpowder = new ItemStack(ItemsRegistry.DUST_neifrit),
            sp_0 = new ItemStack(ItemsRegistry.GENERATOR_sp_0),
            sp_1 = new ItemStack(ItemsRegistry.GENERATOR_sp_1);

    public static void addMachineRecipe() {
        IRecipeInputFactory input = Recipes.inputFactory;
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("minHeat", 3000);

        iron_dust.setCount(3);

        addCompressorRecipe(input.forStack(carbon_plate, 9), new ItemStack(ItemsRegistry.ITEM_Compressed_Carbon_Plate));
        addCompressorRecipe(input.forStack(neifrit_plate, 9), neifrit_plate_compres);
        addCompressorRecipe(input.forStack(spadiy_plate, 9), spadiy_plate_compress);
        addCompressorRecipe(input.forStack(neifritpowder, 6), neifrit);
        addCompressorRecipe(input.forStack(sp_0, 2), sp_1);

        addRollingRecipe(input.forStack(spadiy), spadiy_plate);
        addRollingRecipe(input.forStack(neifrit), neifrit_plate);
        addRollingRecipe(input.forStack(neifrit_plate), neifrit_casing);

        addMaceratorRecipe(input.forStack(neifrit), new ItemStack(ItemsRegistry.DUST_neifrit, 6));
        addMaceratorRecipe(input.forStack(new ItemStack(BlocksRegister.NeifritOre)), new ItemStack(ItemsRegistry.DUST_neifrit, 9));
    }

    private static void addCompressorRecipe(IRecipeInput input, ItemStack output) {
        Recipes.compressor.addRecipe(input, null, false, output);
    }

    private static void addRollingRecipe(IRecipeInput input, ItemStack output) {
        Recipes.metalformerRolling.addRecipe(input, null, false, output);
    }

    private static void addMaceratorRecipe(IRecipeInput input, ItemStack output) {
        Recipes.macerator.addRecipe(input, null, false, output);
    }
}

package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.EnderPower;
import com.nofrfa.enderpower.misc.Configs;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftRegister {
    private static final ItemStack
            ultmachine = new ItemStack(BlocksRegister.BLOCK_UltimateMachineCasing),
            ult_circuit = new ItemStack(ItemsRegistry.ITEM_Ultimate_Circuit),
            adv_circuit = IC2Items.getItem("crafting", "advanced_circuit"),
            iodine = IC2Items.getItem("misc_resource", "iodine"),
            compressed_carbon_plate = new ItemStack(ItemsRegistry.ITEM_Compressed_Carbon_Plate),
            adv_casing_machine = IC2Items.getItem("resource", "advanced_machine"),
            power_convertion = new ItemStack(ItemsRegistry.MODULE_power_convertion),
            neifrit_coil = new ItemStack(ItemsRegistry.ITEM_neifrit_coil),
            neifrit = new ItemStack(ItemsRegistry.INGOT_nefrit),
            spadiy = new ItemStack(ItemsRegistry.INGOT_spadiy),
            upgrade = IC2Items.getItem("upgrade", "transformer"),
            lapotron_crystal = IC2Items.getItem("lapotron_crystal"),
            neifritpowder = new ItemStack(ItemsRegistry.DUST_neifrit),
            dust_lapis = IC2Items.getItem("dust","lapis"),
            lithiumdust = IC2Items.getItem("dust","lithium"),
            energydust = IC2Items.getItem("dust","energium"),
            golddust = IC2Items.getItem("dust", "gold"),
            emptybich = new ItemStack(ItemsRegistry.ITEM_empshel),
            ukrglass = IC2Items.getItem("glass","reinforced"),
            heatingrod = new ItemStack(ItemsRegistry.ITEM_hetrod),
            ukrkamen = IC2Items.getItem("resource","reinforced_stone"),
            inhibiton_core = new ItemStack(ItemsRegistry.ITEM_The_inhibition),
            spadyplate = new ItemStack(ItemsRegistry.PLATE_spadiy),
            spadiydust = new ItemStack(ItemsRegistry.DUST_spadiy),
            inhibition_core = new ItemStack(ItemsRegistry.ITEM_The_inhibition),
            neifrit_compres = new ItemStack(ItemsRegistry.PLATE_neifrit_compres),
            neifrit_plate = new ItemStack(ItemsRegistry.PLATE_neifrit),
            end_crystal = new ItemStack(Items.END_CRYSTAL),
            projectile = new ItemStack(ItemsRegistry.ITEM_deterrent),
            gold_ingot = new ItemStack(Items.GOLD_INGOT),
            gas_converter = EnderPower.machines.getItemStack(AllMachinesTE.gas_converter),
            destructor = EnderPower.machines.getItemStack(AllMachinesTE.destructor),
            irid_pate = IC2Items.getItem("crafting", "iridium"),
            erbi_ingot = new ItemStack(ItemsRegistry.INGOT_erbi),
            sp_3 = new ItemStack(ItemsRegistry.GENERATOR_sp_3),
            amulet_erbi = new ItemStack(ItemsRegistry.ITEM_erbi_amulet),

            //Теплообменники
            HEATSINK_1 = new ItemStack(ItemsRegistry.COMPONENT_1),
            HEATSINK_2 = new ItemStack(ItemsRegistry.COMPONENT_2),
            HEATSINK_3 = new ItemStack(ItemsRegistry.COMPONENT_3),
            HEATSINK_4 = new ItemStack(ItemsRegistry.COMPONENT_4),
            HEATSINK_5 = new ItemStack(ItemsRegistry.COMPONENT_5),
            HEATSINK_6 = new ItemStack(ItemsRegistry.COMPONENT_6),
            HEATSINK_7 = new ItemStack(ItemsRegistry.COMPONENT_7),

            HEATSINK_gold = gold_ingot,
            HEATSINK_coolant = IC2Items.getItem("fluid_cell", "ic2coolant"),
            HEATSINK_neifrit = neifrit,
            HEATSINK_neifrit_plate = new ItemStack(ItemsRegistry.PLATE_neifrit),
            HEATSINK_neifrit_plate_compress = neifrit_compres,
            HEATSINK_spadiy = spadiy,
            HEATSINK_spadiy_plate_compress = new ItemStack(ItemsRegistry.PLATE_spadiy_compres),
            HEATSINK_ult_cirtcuit = ult_circuit,
            HEATSINK_neifrit_casing = new ItemStack(ItemsRegistry.CASING_neifrit),

            HEATSINK_reactor_exchanger = IC2Items.getItem("reactor_heat_exchanger"),
            HEATSINK_reactor_heat_vent = IC2Items.getItem("reactor_heat_vent"),
            HEATSINK_component_exchanger = IC2Items.getItem("component_heat_exchanger"),
            HEATSINK_overclocked_heat_vent = IC2Items.getItem("overclocked_heat_vent"),
            HEATSINK_iridium_plate = IC2Items.getItem("crafting", "iridium"),
            HEATSINK_advanced_heat_exchanger = IC2Items.getItem("advanced_heat_exchanger"),
            HEATSINK_advanced_hear_vent = IC2Items.getItem("advanced_heat_vent"),
            //Улучшения
            FastQ = new ItemStack(ItemsRegistry.UPGRADE_speed),
            Iridium_Reflector = IC2Items.getItem("iridium_reflector"),
            diamond = new ItemStack(Items.DIAMOND),
            upgrade_neifrit = neifrit,
            lithium_dust = IC2Items.getItem("dust","lithium"),
            upgrade_overlocker = IC2Items.getItem("upgrade","overclocker"),
            Vy_upgrade = new ItemStack(ItemsRegistry.UPGRADE_Volecy),
            Energy_storage = IC2Items.getItem("upgrade","energy_storage"),
            steel_dust = new ItemStack(ItemsRegistry.DUST_steel),
            diamond_dust = IC2Items.getItem("dust", "diamond"),
            electrical_motor = IC2Items.getItem("crafting","electric_motor"),
            obsidian_dust = IC2Items.getItem("dust", "obsidian"),
            obsidian = new ItemStack(Blocks.OBSIDIAN),
            steel_ingot = IC2Items.getItem("ingot", "steel");

    public static void addCraftingRecipes() {
        assert false;
        addShapedRecipes((new ItemStack(ItemsRegistry.DUST_spadiy, 4)),
                "AAA",
                "BBB",
                "CCC",
                'A', steel_dust, 'B', obsidian_dust, 'C', diamond_dust);

        addShapedRecipes((new ItemStack(ItemsRegistry.DUST_spadiy, 1)),
                "A  ",
                "B  ",
                "C  ",
                'A', "ingotSteel", 'B', "obsidian", 'C', "gemDiamond");

        addShapelessRecipe((new ItemStack(ItemsRegistry.DUST_neifrit, 4)), dust_lapis, lithiumdust, energydust, golddust);

        addShapedRecipes((new ItemStack(ItemsRegistry.ITEM_Ultimate_Circuit, 1)),
                "ABA",
                "CDC",
                "ABA",
                'A', spadiydust, 'B', adv_circuit, 'C', dust_lapis, 'D', IC2Items.getItem("misc_resource", "iridium_shard"));

        addShapedRecipes((new ItemStack(ItemsRegistry.ITEM_Ultimate_Circuit, 1)),
                "ACA",
                "BDB",
                "ACA",
                'A', spadiydust, 'B', adv_circuit, 'C', dust_lapis, 'D', IC2Items.getItem("misc_resource", "iridium_shard"));

        addShapedRecipes((new ItemStack(BlocksRegister.BLOCK_UltimateMachineCasing, 1)),
                "BDB",
                "BCB",
                "BAB",
                'A', adv_casing_machine, 'B', compressed_carbon_plate, 'C', ult_circuit, 'D', neifrit_plate);

        addShapedRecipes(is(ItemsRegistry.TOOL_spadiy_sword),
                " A ",
                " A ",
                " B ",
                'A', spadiy, 'B', "stickWood");

        addShapedRecipes(is(ItemsRegistry.TOOL_spadiy_sword),
                " A ",
                " A ",
                " B ",
                'A', spadiy, 'B', "stickWood");

        addShapedRecipes(is(ItemsRegistry.TOOL_spadiy_pickaxe),
                "AAA",
                " B ",
                " B ",
                'A', spadiy, 'B', "stickWood");

        addShapedRecipes(is(ItemsRegistry.TOOL_spadiy_axe),
                " AA",
                " BA",
                " B ",
                'A', spadiy, 'B', "stickWood");

        addShapedRecipes(is(ItemsRegistry.TOOL_spadiy_axe),
                "AA ",
                "AB ",
                " B ",
                'A', spadiy, 'B', "stickWood");

        addShapedRecipes(is(ItemsRegistry.TOOL_spadiy_spade),
                " A ",
                " B ",
                " B ",
                'A', spadiy, 'B', "stickWood");

        //rofl
        if(Configs.GeneralSettings.root_access)
            addShapelessRecipe((new ItemStack(ItemsRegistry.UPGRADE_creative_energy, 4)), is(Blocks.BEDROCK),is(Blocks.BEDROCK),is(Blocks.BEDROCK),is(Blocks.BEDROCK),is(Blocks.BEDROCK),is(Blocks.BEDROCK));
    }

    public static void addFurnaceRecipes() {
        addFurnaceRecipe(is(BlocksRegister.NeifritOre), is(ItemsRegistry.INGOT_nefrit), 2.0F);
    }

    private static void addShapedRecipes(ItemStack output, Object...input){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(output, input);
    }

    private static void addShapelessRecipe(ItemStack output, Object...input) {
        Recipes.advRecipes.addShapelessRecipe(output, input);
    }

    private static void addFurnaceRecipe(ItemStack stack, ItemStack output, Float exp) {
        GameRegistry.addSmelting(stack, output, exp);
    }

    private static void addFurnaceRecipe(Item item, ItemStack output, Float exp) {
        GameRegistry.addSmelting(item, output, exp);
    }

    private static void addFurnaceRecipe(Block block, ItemStack output, Float exp) {
        GameRegistry.addSmelting(block, output, exp);
    }

    private static ItemStack is(Item item) {
        return new ItemStack(item);
    }

    private static ItemStack is(Block block) {
        return new ItemStack(block);
    }

    private static ItemStack is(Item item, int count) {
        return new ItemStack(item, count);
    }

    private static ItemStack is(Block block, int count) {
        return new ItemStack(block, count);
    }
}
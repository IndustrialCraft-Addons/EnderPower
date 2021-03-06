package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.EnderPower;
import com.nofrfa.enderpower.item.ItemList;
import com.nofrfa.enderpower.misc.EPConfig;
import com.nofrfa.enderpower.misc.ModUtils;
import com.nofrfa.enderpower.misc.tabs.TabsList;
import com.nofrfa.enderpower.tools.Axe;
import com.nofrfa.enderpower.tools.Pickaxe;
import com.nofrfa.enderpower.tools.Shovel;
import com.nofrfa.enderpower.tools.Sword;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(EnderPower.MODID)
@Mod.EventBusSubscriber
public class ItemsRegistry {
    @GameRegistry.ObjectHolder("deterrent")
    public static final Item ITEM_deterrent = null;

    @GameRegistry.ObjectHolder("deterrent_filled")
    public static final Item ITEM_deterrent_filled = null;

    @GameRegistry.ObjectHolder("spadiy")
    public static final Item INGOT_spadiy = null;

    @GameRegistry.ObjectHolder("neifrit")
    public static final Item INGOT_nefrit = null;

    @GameRegistry.ObjectHolder("erbi")
    public static final Item INGOT_erbi = null;

    @GameRegistry.ObjectHolder("spadiy_dust")
    public static final Item DUST_spadiy = null;

    @GameRegistry.ObjectHolder("steel_dust")
    public static final Item DUST_steel = null;

    @GameRegistry.ObjectHolder("neifrit_dust")
    public static final Item DUST_neifrit = null;

    @GameRegistry.ObjectHolder("neifrit_plate")
    public static final Item PLATE_neifrit = null;

    @GameRegistry.ObjectHolder("spadiy_plate")
    public static final Item PLATE_spadiy = null;

    @GameRegistry.ObjectHolder("neifrit_plate_dense")
    public static final Item PLATE_neifrit_compres = null;

    @GameRegistry.ObjectHolder("spadiy_plate_dense")
    public static final Item PLATE_spadiy_compres = null;

    @GameRegistry.ObjectHolder("compressed_carbon_plate")
    public static final Item ITEM_Compressed_Carbon_Plate = null;

    @GameRegistry.ObjectHolder("ultimate_circuit")
    public static final Item ITEM_Ultimate_Circuit = null;

    @GameRegistry.ObjectHolder("neifrit_coil")
    public static final Item ITEM_neifrit_coil = null;

    @GameRegistry.ObjectHolder("emptyshell")
    public static final Item ITEM_empshel = null;

    @GameRegistry.ObjectHolder("heatingrod")
    public static final Item ITEM_hetrod = null;

    @GameRegistry.ObjectHolder("inhibition_core")
    public static final Item ITEM_The_inhibition = null;

    @GameRegistry.ObjectHolder("amulet_erbi")
    public static final Item ITEM_erbi_amulet = null;

    @GameRegistry.ObjectHolder("spadiy_pickaxe")
    public static final Item TOOL_spadiy_pickaxe = null;

    @GameRegistry.ObjectHolder("spadiy_spade")
    public static final Item TOOL_spadiy_spade = null;

    @GameRegistry.ObjectHolder("spadiy_axe")
    public static final Item TOOL_spadiy_axe = null;

    @GameRegistry.ObjectHolder("spadiy_sword")
    public static final Item TOOL_spadiy_sword = null;

    @GameRegistry.ObjectHolder("second_product_0")
    public static final Item GENERATOR_sp_0 = null;

    @GameRegistry.ObjectHolder("second_product_1")
    public static final Item GENERATOR_sp_1 = null;

    @GameRegistry.ObjectHolder("second_product_2")
    public static final Item GENERATOR_sp_2 = null;

    @GameRegistry.ObjectHolder("second_product_3")
    public static final Item GENERATOR_sp_3 = null;

    @GameRegistry.ObjectHolder("heatsink_lvl.1")
    public static final Item  COMPONENT_1 = null;

    @GameRegistry.ObjectHolder("heatsink_lvl.2")
    public static final Item  COMPONENT_2 = null;

    @GameRegistry.ObjectHolder("heatsink_lvl.3")
    public static final Item  COMPONENT_3 = null;

    @GameRegistry.ObjectHolder("heatsink_lvl.4")
    public static final Item  COMPONENT_4= null;

    @GameRegistry.ObjectHolder("heatsink_lvl.5")
    public static final Item  COMPONENT_5= null;

    @GameRegistry.ObjectHolder("heatsink_lvl.6")
    public static final Item  COMPONENT_6= null;

    @GameRegistry.ObjectHolder("heatsink_lvl.7")
    public static final Item  COMPONENT_7= null;

    @GameRegistry.ObjectHolder("upgrade_volecy")
    public static final Item UPGRADE_Volecy  = null;

    @GameRegistry.ObjectHolder("upgrade_speed")
    public static final Item UPGRADE_speed  = null;

    @GameRegistry.ObjectHolder("upgrade_energy")
    public static final Item UPGRADE_energy  = null;

    @GameRegistry.ObjectHolder("upgrade_capacity")
    public static final Item UPGRADE_capacity  = null;

    @GameRegistry.ObjectHolder("upgrade_gift_energy")
    public static final Item UPGRADE_gift_energy  = null;

    @GameRegistry.ObjectHolder("upgrade_creative_energy")
    public static final Item UPGRADE_creative_energy  = null;

    @SubscribeEvent
    public static void onRegistryItem(RegistryEvent.Register<Item> e) {
        //Item
        e.getRegistry().register(new ItemList("deterrent", 1));
        e.getRegistry().register(new ItemList("deterrent_filled", 1));
        e.getRegistry().register(new ItemList("compressed_carbon_plate", 64));
        e.getRegistry().register(new ItemList("ultimate_circuit", 64));
        e.getRegistry().register(new ItemList("neifrit_coil", 64));
        e.getRegistry().register(new ItemList("emptyshell", 64));
        e.getRegistry().register(new ItemList("heatingrod", 64));
        e.getRegistry().register(new ItemList("inhibition_core", 64));
        e.getRegistry().register(new ItemList("amulet_erbi", 1, EPConfig.GeneralSettings.Item.amuletErbi_durability, false));

        //Ingot
        e.getRegistry().register(new ItemList("spadiy", 64));
        e.getRegistry().register(new ItemList("neifrit", 64));
        e.getRegistry().register(new ItemList("erbi", 64));

        //Plate
        e.getRegistry().register(new ItemList("spadiy_plate", 64));
        e.getRegistry().register(new ItemList("neifrit_plate", 64));
        e.getRegistry().register(new ItemList("neifrit_plate_dense", 64));
        e.getRegistry().register(new ItemList("spadiy_plate_dense", 64));

        //Dust
        e.getRegistry().register(new ItemList("spadiy_dust", 64));
        e.getRegistry().register(new ItemList("neifrit_dust", 64));
        e.getRegistry().register(new ItemList("steel_dust", 64));

        //Tools
        e.getRegistry().register(new Pickaxe(ModUtils.ToolMaterial_univ, "spadiy_pickaxe", TabsList.EXtabs));
        e.getRegistry().register(new Shovel(ModUtils.ToolMaterial_univ, "spadiy_spade", TabsList.EXtabs));
        e.getRegistry().register(new Axe(ModUtils.ToolMaterial_univ, "spadiy_axe", TabsList.EXtabs, 8.0F, 40.0F));
        e.getRegistry().register(new Sword(ModUtils.ToolMaterial_sword, "spadiy_sword", TabsList.EXtabs));

        //Upgrade
        e.getRegistry().register(new ItemList("upgrade_speed", 16));
        e.getRegistry().register(new ItemList("upgrade_volecy", 16));
        if(EPConfig.GeneralSettings.Upgrades.Energy.energy_upgrade_bool)
            e.getRegistry().register(new ItemList("upgrade_energy", 1));

        if(EPConfig.GeneralSettings.Upgrades.Capacity.capacity_upgrade_bool)
            e.getRegistry().register(new ItemList("upgrade_capacity", 1));

        if(EPConfig.GeneralSettings.Upgrades.GiftEnergy.giftEnergy_upgrade_bool)
            e.getRegistry().register(new ItemList("upgrade_gift_energy", 1));

        e.getRegistry().register(new ItemList("upgrade_creative_energy", 1));

        //Component
        e.getRegistry().register(new ItemList("heatsink_lvl.1", 1, EPConfig.GeneralSettings.Item.heatSink1_durability, false));
        e.getRegistry().register(new ItemList("heatsink_lvl.2", 1, EPConfig.GeneralSettings.Item.heatSink2_durability, false));
        e.getRegistry().register(new ItemList("heatsink_lvl.3", 1, EPConfig.GeneralSettings.Item.heatSink3_durability, false));
        e.getRegistry().register(new ItemList("heatsink_lvl.4", 1, EPConfig.GeneralSettings.Item.heatSink4_durability, false));
        e.getRegistry().register(new ItemList("heatsink_lvl.5", 1, EPConfig.GeneralSettings.Item.heatSink5_durability, false));
        e.getRegistry().register(new ItemList("heatsink_lvl.6", 1, EPConfig.GeneralSettings.Item.heatSink6_durability, false));
        e.getRegistry().register(new ItemList("heatsink_lvl.7", 1, EPConfig.GeneralSettings.Item.heatSink7_durability, false));

        //Second Products
        e.getRegistry().register(new ItemList("second_product_0", 64));
        e.getRegistry().register(new ItemList("second_product_1", 64));
        e.getRegistry().register(new ItemList("second_product_2", 64));
        e.getRegistry().register(new ItemList("second_product_3", 64));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegistryModel(ModelRegistryEvent e) {
        //Ingot
        assert false;
        registryModel(INGOT_spadiy);
        registryModel(INGOT_nefrit);
        registryModel(INGOT_erbi);

        //Plate
        registryModel(PLATE_spadiy);
        registryModel(PLATE_neifrit);
        registryModel(PLATE_neifrit_compres);
        registryModel(PLATE_spadiy_compres);

        //Dust
        registryModel(DUST_spadiy);
        registryModel(DUST_steel);
        registryModel(DUST_neifrit);

        //Item
        registryModel(ITEM_Compressed_Carbon_Plate);
        registryModel(ITEM_deterrent);
        registryModel(ITEM_deterrent_filled);
        registryModel(ITEM_Ultimate_Circuit);
        registryModel(ITEM_neifrit_coil);
        registryModel(ITEM_empshel);
        registryModel(ITEM_hetrod);
        registryModel(ITEM_The_inhibition);
        registryModel(ITEM_erbi_amulet);

        //Tools
        registryModel(TOOL_spadiy_pickaxe);
        registryModel(TOOL_spadiy_axe);
        registryModel(TOOL_spadiy_spade);
        registryModel(TOOL_spadiy_sword);

        //Upgrade
        registryModel(UPGRADE_speed);
        registryModel(UPGRADE_Volecy);
        registryModel(UPGRADE_energy);
        registryModel(UPGRADE_capacity);
        registryModel(UPGRADE_gift_energy);
        registryModel(UPGRADE_creative_energy);

        //Component
        registryModel(COMPONENT_1);
        registryModel(COMPONENT_2);
        registryModel(COMPONENT_3);
        registryModel(COMPONENT_4);
        registryModel(COMPONENT_5);
        registryModel(COMPONENT_6);
        registryModel(COMPONENT_7);

        //Second production
        registryModel(GENERATOR_sp_0);
        registryModel(GENERATOR_sp_1);
        registryModel(GENERATOR_sp_2);
        registryModel(GENERATOR_sp_3);
    }

    // ?????? ?????? ???????? - ???? ??????????????
    @SideOnly(Side.CLIENT)
    private static void registryModel(Item item) {
        final ResourceLocation regName = item.getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        ModelBakery.registerItemVariants(item, mrl);
        ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
    }
}

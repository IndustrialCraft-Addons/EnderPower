package com.nofrfa.enderpower.tile.machines.destructor;

import com.nofrfa.enderpower.misc.EPConfig;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.tile.machines.destructor.gui.ContainerDestr;
import com.nofrfa.enderpower.tile.machines.destructor.gui.GuiDestr;
import ic2.api.tile.IWrenchable;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.comp.Redstone;
import ic2.core.block.invslot.InvSlotConsumableItemStack;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.profile.NotClassic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NotClassic
public class DestructorTE extends TileEntityElectricMachine implements IHasGui, IWrenchable {
    protected Redstone redstone;
    public final InvSlotConsumableItemStack inputContainer;
    public final InvSlotOutput outputContainer;
    private int MAX_PROGRESS;
    private float progress;
    private int energyConsume;
    private int timer;
    public List<Recipes> recipes;
    public List<ItemStack> outputItems = new ArrayList<>();

    public DestructorTE() {
        super(16000, 4);
        this.redstone = this.addComponent(new Redstone(this));
        this.inputContainer = new InvSlotConsumableItemStack(this, "in", 1, Recipes.getItemsForInput());
        this.outputContainer = new InvSlotOutput(this, "out", 4);
        this.progress = 0;
        this.MAX_PROGRESS = 600;
        this.energyConsume = EPConfig.GeneralSettings.Mechanisms.Destructor.defaultEnergyConsume;
        this.recipes = Recipes.getRecipes();
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.progress = nbt.getInteger("progress");
        this.MAX_PROGRESS = nbt.getInteger("max_progress");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("progress", getProgress());
        nbt.setInteger("max_progress", this.MAX_PROGRESS);
        return nbt;
    }

    protected void updateEntityServer() {
        super.updateEntityServer();

        if(this.timer++ % 100 == 0) {
            if(this.energyConsume != EPConfig.GeneralSettings.Mechanisms.Destructor.defaultEnergyConsume)
                this.energyConsume = EPConfig.GeneralSettings.Mechanisms.Destructor.defaultEnergyConsume;
            this.timer = 0;
        }

        int recipeNumber = 0;
        this.outputItems.clear();

        if(!this.inputContainer.isEmpty()) {
            for(int i = 0; i < this.recipes.size(); i++) {
                if(this.recipes.get(i).matchesInput(this.inputContainer.get())) {
                    this.outputItems.addAll(Arrays.asList(this.recipes.get(i).getOutput()));
                    recipeNumber = i;
                    break;
                }
            }
        }

        if(canWork(recipeNumber)) {
            this.progress++;
            this.energy.useEnergy(energyConsume);
            this.setActive(true);
        } else this.setActive(false);

        if(this.inputContainer.isEmpty()) this.progress = 0;

        if(this.progress == this.MAX_PROGRESS) {
            this.progress = 0;
            completed(recipeNumber);
        }
    }

    private void completed(int recipeNumber) {
        this.inputContainer.consume(this.recipes.get(recipeNumber).getInput()[0].getCount());

        for(ItemStack outputItem : this.outputItems) {
            this.outputContainer.add(outputItem);
        }
    }

    private boolean canWork(int recipeNumber) {
        int checkID = 0;
        for(ItemStack outputItem : outputItems) {
            if(this.outputContainer.canAdd(outputItem))
                checkID++;
        }

        return !this.inputContainer.isEmpty()
                && this.energy.canUseEnergy(energyConsume)
                && this.inputContainer.get().getCount() >= this.recipes.get(recipeNumber).getInput()[0].getCount()
                && checkID == outputItems.size()
                && this.redstone.getRedstoneInput() == 0;
    }

    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerDestr(entityPlayer, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return new GuiDestr(new ContainerDestr(entityPlayer, this));
    }

    public int getProgress() {
        return Math.round((this.progress / this.MAX_PROGRESS) * 100F);
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        return this.getFacing();
    }

    @Override
    public boolean setFacing(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        if (!this.canSetFacingWrench(enumFacing, entityPlayer)) {
            return false;
        } else {
            this.setFacing(enumFacing);
            return true;
        }
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        List<ItemStack> list = new ArrayList<>();
        inputContainer.forEach(list::add);
        outputContainer.forEach(list::add);

        return list;
    }

    private static ItemStack is(Item item, int amount){
        return new ItemStack(item, amount);
    }

    private static ItemStack is(Item item){
        return new ItemStack(item, 1);
    }

    public static class Recipes {
        private static final List<Recipes> recipes = new ArrayList<>();
        public static List<Recipes> getRecipes() { // Получатель всех рецептов.
            return recipes;
        }
        private final ItemStack[] input;
        private final ItemStack[] op0;

        public Recipes(ItemStack[] input, ItemStack...output1) {
            this.input = input;
            this.op0 = output1;
        }

        public static void addRecipes(ItemStack input, ItemStack...output1) {
            ItemStack[] input0 = new ItemStack[1];
            input0[0] = input;

            Recipes recipe = new Recipes(input0, output1);
            if (recipes.contains(recipe))
                return;
            recipes.add(recipe);
        }

        private static void addRecipes(String input, ItemStack...output) {
            if(!OreDictionary.doesOreNameExist(input)) throw new RuntimeException("invalid oreDictionary name: " + input);

            ItemStack[] itemStacks = OreDictionary.getOres(input).toArray(new ItemStack[0]);

            for(ItemStack is : itemStacks)
                is.setCount(1);

            Recipes recipe = new Recipes(itemStacks, output);
            if (recipes.contains(recipe))
                return;
            recipes.add(recipe);
        }

        private static void addRecipes(String input, int count, ItemStack...output) {
            if(!OreDictionary.doesOreNameExist(input)) throw new RuntimeException("invalid oreDictionary name: " + input);

            ItemStack[] itemStacks = OreDictionary.getOres(input).toArray(new ItemStack[0]);
            Arrays.stream(itemStacks).forEachOrdered(is -> is.setCount(count));

            Recipes recipe = new Recipes(itemStacks, output);
            if (recipes.contains(recipe))
                return;
            recipes.add(recipe);
        }

        public static ItemStack[] getItemsForInput() {
            List<ItemStack> listItems = new ArrayList<>();

            for(Recipes recipe : recipes) {
                ItemStack[] itemStacks = recipe.getInput();
                listItems.addAll(Arrays.asList(itemStacks));
            }

            return listItems.toArray(new ItemStack[0]);
        }

        public ItemStack[] getInput() { // Получатель входного предмета рецепта.
            return input;
        }

        public ItemStack[] getOutput() { // Получатель выходного предмета рецепта.
            return op0;
        }

        public boolean matchesInput(ItemStack is) {
            for(ItemStack item : input) {
                if(is.getItem() == item.getItem())
                    return true;
            }

            return false;
        }

        public static void initRecipes() {
            addRecipes(is(ItemsRegistry.DUST_spadiy), is(ItemsRegistry.INGOT_spadiy));
            addRecipes(is(ItemsRegistry.INGOT_spadiy, 2), is(ItemsRegistry.DUST_spadiy));
            addRecipes("ingotSteel", is(ItemsRegistry.DUST_steel));
            addRecipes(is(ItemsRegistry.GENERATOR_sp_1, 4), is(ItemsRegistry.GENERATOR_sp_2));
            addRecipes(is(ItemsRegistry.GENERATOR_sp_2, 4), is(ItemsRegistry.GENERATOR_sp_3));
        }
    }
}

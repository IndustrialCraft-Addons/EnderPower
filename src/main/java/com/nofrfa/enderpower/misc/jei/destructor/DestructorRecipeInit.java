package com.nofrfa.enderpower.misc.jei.destructor;

import com.nofrfa.enderpower.tile.machines.destructor.DestructorTE;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DestructorRecipeInit {
    private static final List<DestructorRecipeInit> recipes = new ArrayList<>();

    public static List<DestructorRecipeInit> getRecipes() { // Получатель всех рецептов.
        return recipes;
    }

    private final List<ItemStack> input;
    private final List<ItemStack> op0;

    public DestructorRecipeInit(List<ItemStack> input, List<ItemStack> output1) {
        this.input = input;
        this.op0 = output1;
    }

    public List<ItemStack> getInput() { // Получатель входного предмета рецепта.
        return input;
    }

    public List<ItemStack> getOutput() { // Получатель выходного предмета рецепта.
        return op0;
    }

    public static void addRecipe(List<ItemStack> input, List<ItemStack> output1) {
        DestructorRecipeInit recipe = new DestructorRecipeInit(input, output1);
        if (recipes.contains(recipe))
            return;
        recipes.add(recipe);
    }

    public static DestructorRecipeInit getRecipe(ItemStack is) {
        if (is == null || is.isEmpty())
            return null;
        for (DestructorRecipeInit recipe : recipes)
            if (recipe.matchesInput(is))
                return recipe;
        return null;
    }

    public boolean matchesInput(ItemStack is) {
        for(ItemStack item : input) {
            if(is.getItem() == item.getItem())
                return true;
        }

        return false;
    }

    public static void initRecipes() {
        for(int i = 0; i < DestructorTE.Recipes.getRecipes().size(); i++) {
            List<ItemStack> list = new ArrayList<>(Arrays.asList(DestructorTE.Recipes.getRecipes().get(i).getOutput()));
            addRecipe(Arrays.asList(DestructorTE.Recipes.getRecipes().get(i).getInput()), list);
        }
    }
}

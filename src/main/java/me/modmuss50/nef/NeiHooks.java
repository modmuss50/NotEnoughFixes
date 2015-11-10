package me.modmuss50.nef;

import codechicken.nei.ItemList;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NeiHooks {

    //This is the same as the one from nei, but it has null check on the item.
    public static void findFuels() {
        System.out.println("Using custom fuel finder...");
        FurnaceRecipeHandler.afuels = new ArrayList();
        Set efuels = excludedFuels();
        Iterator i$ = ItemList.items.iterator();

        while (i$.hasNext()) {
            ItemStack item = (ItemStack) i$.next();
            if (item.getItem() == null) {
                System.out.println("Found a null item, in an itemstack!");
            } else if (!efuels.contains(item.getItem())) {
                int burnTime = TileEntityFurnace.getItemBurnTime(item);
                if (burnTime > 0) {
                    FurnaceRecipeHandler.afuels.add(new FurnaceRecipeHandler.FuelPair(item.copy(), burnTime));
                }
            }
        }
    }

    private static Set<Item> excludedFuels() {
        HashSet efuels = new HashSet();
        efuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.standing_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wall_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wooden_door));
        efuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
        return efuels;
    }

}

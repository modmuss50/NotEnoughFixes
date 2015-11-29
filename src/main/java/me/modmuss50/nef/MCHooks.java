package me.modmuss50.nef;

import net.minecraft.item.Item;

public class MCHooks {

    public static void checkNull(Item item) {
        if (item == null) {
          //  throw new NullPointerException("An itemstack was created with a null item!");
            System.out.println("An itemstack was created with a null item!");
            Thread.dumpStack();
        }
    }
}

package me.modmuss50.nef;

import codechicken.nei.ItemList;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.IUsageHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

@Mod(name = "NotEnoughFixes", modid = "nef")
public class NotEnoughFixes {

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        System.out.println("Checking for null items!");
        for(ItemStack stack : ItemList.items){
            if(stack.getItem() == null){
                System.out.println("Found a null item in a stack, removing from NEI");
                ItemList.items.remove(stack);
            }
        }

    }

}

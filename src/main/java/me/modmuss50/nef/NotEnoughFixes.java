package me.modmuss50.nef;

import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.IUsageHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

@Mod(name = "NotEnoughFixes", modid = "nef")
public class NotEnoughFixes {

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event){
        System.out.println("Displaying all usage handlers");
        for(IUsageHandler usageHandler : GuiUsageRecipe.usagehandlers){
            System.out.println("Usage: " + usageHandler.getClass().getCanonicalName());
        }

    }

}

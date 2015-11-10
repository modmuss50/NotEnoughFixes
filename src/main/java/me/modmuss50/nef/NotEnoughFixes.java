package me.modmuss50.nef;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mod(name = "NotEnoughFixes", modid = "nef", version = "1.2.1", acceptableRemoteVersions = "*")
public class NotEnoughFixes {


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        injectBrandings();
    }

    public static void injectBrandings() {
        Field f = ReflectUtilities.getField(FMLCommonHandler.instance(), "brandings");
        f.setAccessible(true);
        try {
            FMLCommonHandler.instance().computeBranding();
            List<String> brands = new ArrayList<String>((List<String>) f.get(FMLCommonHandler.instance()));
            List<String> newBrands = new ArrayList<String>();
            for (int i = 0; i < brands.size(); i++) {
                if (i == 1) {
                    newBrands.add("NotEnoughFixes v1.2.1");
                }
                newBrands.add(brands.get(i));
            }
            f.set(FMLCommonHandler.instance(), newBrands);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

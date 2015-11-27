package me.modmuss50.nef;


import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

//-Dfml.coreMods.load=me.modmuss50.nef.NotEnoughFixesCore
@IFMLLoadingPlugin.MCVersion("1.8.8")
public class NotEnoughFixesCore implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"me.modmuss50.nef.NEFClassTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}

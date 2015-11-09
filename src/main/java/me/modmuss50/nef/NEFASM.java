package me.modmuss50.nef;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

//-Dfml.coreMods.load=me.modmuss50.nef.NEFASM
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class NEFASM implements IFMLLoadingPlugin {
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

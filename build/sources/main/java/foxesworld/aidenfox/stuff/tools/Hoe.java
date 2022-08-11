package foxesworld.aidenfox.stuff.tools;

import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.item.ItemHoe;

public class Hoe extends ItemHoe {


    public Hoe(String name, ToolMaterial material) {
        super(material);
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }
}
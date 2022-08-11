package foxesworld.aidenfox.stuff.tools;

import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.item.ItemSword;


public class Sword extends ItemSword {

    public Sword(String name, ToolMaterial material) {
        super(material);
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }
}

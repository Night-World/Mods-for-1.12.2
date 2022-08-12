package foxesworld.aidenfox.stuff.items;

import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.item.Item;

public abstract class Items extends Item {

    public Items(String name) {
        this.setRegistryName(Environment.MODID, name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }
}

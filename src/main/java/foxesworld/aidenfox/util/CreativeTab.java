package foxesworld.aidenfox.util;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static foxesworld.aidenfox.cfg.Environment.ITEMS;

public class CreativeTab extends CreativeTabs {

    public static final CreativeTabs MOD_TAB = new CreativeTab();

    public CreativeTab() {

        super(Environment.MODID);

    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ITEMS.get("lapis_apple"));
    }
}
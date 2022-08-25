package foxesworld.aidenfox.stuff.items;

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.CreativeTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.util.Utils.addLore;

public abstract class Item extends net.minecraft.item.Item {

    private String itemName;
    private Boolean itemLore;

    public Item(String name, Boolean lore) {
        this.itemName = name;
        this.itemLore = lore;
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.itemName,"item", tooltip);
    }
}

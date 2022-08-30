package foxesworld.aidenfox.deprecated.items;

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.methods.CreativeTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.methods.Utils.addLore;

public class Item extends net.minecraft.item.Item {

    private String itemName;

    public Item(String name) {
        this.itemName = name;
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.put(name, this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.itemName,"item", tooltip);
    }
}

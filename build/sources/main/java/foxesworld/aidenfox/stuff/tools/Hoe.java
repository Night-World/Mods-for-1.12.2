package foxesworld.aidenfox.stuff.tools;

import foxesworld.aidenfox.util.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Hoe extends ItemHoe {

    private static String toolName;
    private static Boolean toolLore;

    public Hoe(String name, Boolean lore, ToolMaterial material) {
        super(material);
        this.toolName = name;
        this.toolLore = lore;
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.toolLore) {
            TextComponentTranslation msg = new TextComponentTranslation("item."+ this.toolName + ".lore");
            tooltip.add(msg.getUnformattedText());
        }
    }
}
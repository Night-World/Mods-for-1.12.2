package foxesworld.hardcontent.dynamic.tools.toolsType;

import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.cfg.CreativeTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.hardcontent.methods.Utils.addLore;

public class Spade extends ItemSpade {

    private static String toolName;

    public Spade(String name, ToolMaterial material) {
        super(material);
        this.toolName = name;
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.put(name, this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.toolName,"item", tooltip);
    }
}
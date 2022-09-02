package foxesworld.hardcontent.dynamic.tools.toolsType;

import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.cfg.CreativeTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.hardcontent.methods.Utils.addLore;

public class Pickaxe extends ItemPickaxe {

    private static String toolName;
    private static ToolMaterial toolMaterial;

    public Pickaxe(String name, ToolMaterial material) {
        super(material);
        this.toolName = name;
        this.toolMaterial = material;
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

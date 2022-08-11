package foxesworld.aidenfox.stuff.tools;
import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.item.ItemAxe;

public class Axe extends ItemAxe {

    public Axe(String name, ToolMaterial material) {
        super(material, 2.0f, 3.2f);
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }
}
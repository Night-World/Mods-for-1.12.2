package foxesworld.hardcontent.dynamic.tools.toolsType;

import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.cfg.CreativeTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.hardcontent.methods.Utils.addLore;

@Mod.EventBusSubscriber
public class Axe extends ItemAxe {

    private String toolName;
    private ToolMaterial toolMaterial;

    public Axe(String name, ToolMaterial material) {
        super(material, 2.0f, 3.2f);
        this.toolName = name;
        this.toolMaterial = material;
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.put(name, this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.toolName,"item", tooltip);
    }

    @SubscribeEvent
    public void onPlayerItemCrafted(ItemCraftedEvent event) {
        event.player.sendMessage(new TextComponentString("Test1"));
        //return super.on
    }
}
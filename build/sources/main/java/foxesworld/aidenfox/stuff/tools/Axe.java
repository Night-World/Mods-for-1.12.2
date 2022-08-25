package foxesworld.aidenfox.stuff.tools;

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.CreativeTab;
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

import static foxesworld.aidenfox.util.Utils.addLore;

@Mod.EventBusSubscriber
public class Axe extends ItemAxe {

    private String toolName;

    public Axe(String name, ToolMaterial material) {
        super(material, 2.0f, 3.2f);
        this.toolName = name;
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
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
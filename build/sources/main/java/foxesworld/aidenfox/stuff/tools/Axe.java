package foxesworld.aidenfox.stuff.tools;
import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.stuff.sounds.ModSounds.ASK;

public class Axe extends ItemAxe {

    private String toolName;
    private Boolean  toolLore;

    public Axe(String name, Boolean lore, ToolMaterial material) {
        super(material, 2.0f, 3.2f);
        this.toolName = name;
        this.toolLore = lore;
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.toolLore) {
            TextComponentTranslation msg = new TextComponentTranslation("item." + this.toolName + ".lore");
            tooltip.add(msg.getUnformattedText());
        }
    }

}
package foxesworld.aidenfox.stuff.tools;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.cfg.Environment.foxesSounds;

@Mod.EventBusSubscriber
public class Axe extends ItemAxe {

    private String toolName;
    private Boolean toolLore;

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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        System.out.println("You've got it " + playerIn.getName());
        worldIn.playSound((EntityPlayer) playerIn,
                playerIn.posX,
                playerIn.posY,
                playerIn.posZ,
                foxesSounds.get("event.action.fail"),
                SoundCategory.NEUTRAL,
                1.5F, 1F);
        return super.onItemRightClick(worldIn, playerIn, hand);
    }

    @SubscribeEvent
    public void onPlayerItemCrafted(ItemCraftedEvent event) {
        event.player.sendMessage(new TextComponentString("Test1"));

        //return super.on
    }
}
package foxesworld.aidenfox.stuff.items;

import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.cfg.Environment.foxesSounds;

public abstract class Items extends Item  {

    private String itemName;
    private Boolean itemLore;

    public Items(String name, Boolean lore) {
        this.itemName = name;
        this.itemLore = lore;
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.itemLore) {
            TextComponentTranslation msg = new TextComponentTranslation("item."+ this.itemName + ".lore");
            tooltip.add(msg.getUnformattedText());
        }
    }

    //@SubscribeEvent
    public static void onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player){
        worldIn.playSound((EntityPlayer)null,
                player.posX,
                player.posY,
                player.posZ,
                foxesSounds.get("event.action.success"),
                SoundCategory.NEUTRAL,
                085F,
                1.4F);

    }
}

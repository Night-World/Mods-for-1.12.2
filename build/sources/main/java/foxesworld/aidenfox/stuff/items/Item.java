package foxesworld.aidenfox.stuff.items;

import foxesworld.aidenfox.util.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.cfg.Environment.SOUNDS;

public abstract class Item extends net.minecraft.item.Item {

    private String itemName;
    private Boolean itemLore;

    public Item(String name, Boolean lore) {
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        worldIn.playSound((EntityPlayer) playerIn,
                playerIn.posX,
                playerIn.posY,
                playerIn.posZ,
                SOUNDS.get("entity.topielec.hurt"),
                SoundCategory.NEUTRAL,
                1.5F, 1F);
        return super.onItemRightClick(worldIn, playerIn, hand);

    }
}

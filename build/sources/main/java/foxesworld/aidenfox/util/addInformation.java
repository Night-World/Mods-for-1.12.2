package foxesworld.aidenfox.util;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class addInformation {


    private boolean itemLore;
    private String itemName;

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.itemLore) {
            TextComponentTranslation msg = new TextComponentTranslation("item."+this.itemName+".lore");
            tooltip.add(msg.getUnformattedText());
        }
    }

}

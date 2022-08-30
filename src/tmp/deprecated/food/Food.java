package foxesworld.aidenfox.deprecated.food;

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.CreativeTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.util.Utils.addLore;

public abstract class Food extends ItemFood {

    private String itemName;
    private boolean onEatenEffect = false;


    public Food(String name, int amount, float saturation, boolean isWolfFood, boolean alwaysEdible) {
        super(amount, saturation, isWolfFood);
        this.itemName = name;
        this.onEatenEffect = onEatenEffect;
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        if(alwaysEdible) {
            this.setAlwaysEdible();
        }
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.put(name, this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.itemName,"item", tooltip);
    }
}
// player.addPotionEffect(randomEffect(playerHunger));
            /* ItemStack stack = new ItemStack(this, 1);
            NBTTagCompound itemCompound = stack.getTagCompound();
            NBTTagList tags = new NBTTagList();
            NBTTagString eString = new NBTTagString("e.getRegistryName().toString(");
            tags.appendTag(eString);
            itemCompound.setTag("lore", tags); */

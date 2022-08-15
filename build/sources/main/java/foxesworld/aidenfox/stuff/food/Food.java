package foxesworld.aidenfox.stuff.food;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.cfg.Environment.foxesSounds;

public abstract class Food extends ItemFood {

    private String itemName;
    private Boolean itemLore;
    private boolean onEatenEffect = false;


    public Food(String name, Boolean lore, int amount, float saturation, boolean isWolfFood, boolean alwaysEdible, boolean onEatenEffect) {
        super(amount, saturation, isWolfFood);
        this.itemName = name;
        this.itemLore = lore;
        this.onEatenEffect = onEatenEffect;
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        if(alwaysEdible) {
            this.setAlwaysEdible();
        }
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(this.onEatenEffect) {
            super.onFoodEaten(stack, worldIn, player);
            int playerHunger = player.getFoodStats().getFoodLevel();
            worldIn.playSound((EntityPlayer)null,
                    player.posX,
                    player.posY,
                    player.posZ,
                    foxesSounds.get(ConfigCreator.onAppleEaten),
                    SoundCategory.NEUTRAL,
                    1.5F, 1F);
            player.addExperience(playerHunger);
        }
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.itemLore) {
            TextComponentTranslation msg = new TextComponentTranslation("item."+this.itemName+".lore");
            tooltip.add(msg.getUnformattedText());
        }
    }
}
// player.addPotionEffect(randomEffect(playerHunger));
            /* ItemStack stack = new ItemStack(this, 1);
            NBTTagCompound itemCompound = stack.getTagCompound();
            NBTTagList tags = new NBTTagList();
            NBTTagString eString = new NBTTagString("e.getRegistryName().toString(");
            tags.appendTag(eString);
            itemCompound.setTag("lore", tags); */

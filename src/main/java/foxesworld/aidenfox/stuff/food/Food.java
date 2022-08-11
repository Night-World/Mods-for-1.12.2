package foxesworld.aidenfox.stuff.food;

import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static foxesworld.aidenfox.stuff.effects.Effects.randomEffect;

public abstract class Food extends ItemFood {

    private boolean onEatenEffect = false;

    public Food(String name, int amount, float saturation, boolean isWolfFood, boolean alwaysEdible, boolean onEatenEffect) {
        super(amount, saturation, isWolfFood);
        this.onEatenEffect = onEatenEffect;
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        if(alwaysEdible == true) {
            this.setAlwaysEdible();
        }
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.add(this);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(this.onEatenEffect == true) {
            super.onFoodEaten(stack, worldIn, player);
            player.addPotionEffect(randomEffect(player.getFoodStats().getFoodLevel()));
        }
    }
}

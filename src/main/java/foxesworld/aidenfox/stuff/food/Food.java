package foxesworld.aidenfox.stuff.food;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.CreativeTab;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import static foxesworld.aidenfox.cfg.Environment.SOUNDS;

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
            Thread appleEaten = new Thread(new Runnable()  {
                public void run() {
                    int playerHunger = player.getFoodStats().getFoodLevel();
                    
                    worldIn.playSound((EntityPlayer)player,
                            player.posX,
                            player.posY,
                            player.posZ,
                            SOUNDS.get(ConfigCreator.onAppleEaten),
                            SoundCategory.NEUTRAL,
                            1.5F, 1F);
                    try {
                        Thread.sleep(5500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double circleSize = 7;
                    int points = 12;
                    for (int i = 0; i < 360; i += 360/points) {
                        double angle = (i * Math.PI / 180);
                        double x = circleSize * Math.cos(angle);
                        double z = circleSize * Math.sin(angle);

                        EntitySkeleton EntitySkeleton = new EntitySkeleton(worldIn);
                        EntitySkeleton.canPickUpLoot();
                        EntitySkeleton.setLocationAndAngles( player.posX + x, player.posY, player.posZ + z, (float) (0.0F + x), (float) (0.0F + z));
                        worldIn.spawnEntity(EntitySkeleton);
                    }
                    player.addExperience(playerHunger * 20);
                }
            });
            appleEaten.start();
            player.getCooldownTracker().setCooldown(this, 50);
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

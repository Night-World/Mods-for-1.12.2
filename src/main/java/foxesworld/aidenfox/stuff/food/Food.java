package foxesworld.aidenfox.stuff.food;

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.CreativeTab;
import net.minecraft.item.ItemFood;

public abstract class Food extends ItemFood {

    private String itemName;
    private Boolean itemLore;
    private boolean onEatenEffect = false;


    public Food(String name, Boolean lore, int amount, float saturation, boolean isWolfFood, boolean alwaysEdible) {
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
}
// player.addPotionEffect(randomEffect(playerHunger));
            /* ItemStack stack = new ItemStack(this, 1);
            NBTTagCompound itemCompound = stack.getTagCompound();
            NBTTagList tags = new NBTTagList();
            NBTTagString eString = new NBTTagString("e.getRegistryName().toString(");
            tags.appendTag(eString);
            itemCompound.setTag("lore", tags); */

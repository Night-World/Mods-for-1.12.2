package foxesworld.aidenfox.util;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static foxesworld.aidenfox.cfg.Environment.BLOCKS;

public class CreativeTab extends CreativeTabs {

    public static final CreativeTabs MOD_TAB = new CreativeTab();

    public CreativeTab() {

        super(Environment.MODID);

    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(BLOCKS.get("hardened_dirt"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getBackgroundImage() {
        return new ResourceLocation("textures/gui/tab");
    }
}
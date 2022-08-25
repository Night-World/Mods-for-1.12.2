package foxesworld.aidenfox.stuff.blocks;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.util.CreativeTab.MOD_TAB;
import static foxesworld.aidenfox.util.Utils.addLore;
import static foxesworld.aidenfox.util.Utils.debugSend;

public abstract class Blocks extends Block {

    protected String name;

    public Blocks(String name, Material material, SoundType snd, String harvestTool, Integer harvestLevel, float hardness, float resistance) {
        super(material);
        debugSend("["+name+"] " +material +" | " +snd + " | " + harvestTool + " | " + harvestLevel + " | " + hardness + " | " + resistance);
        this.name = name;
        this.setTranslationKey(name);
        this.setSoundType(snd);
        this.setHarvestLevel(harvestTool, harvestLevel);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(Environment.MODID, name);
        setCreativeTab(MOD_TAB);

        Environment.BLOCKS.add(this);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return super.isNormalCube(state, world, pos);
    }

    public String getBlockName() {
        return name;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.name, "tile", tooltip);
    }
}
package foxesworld.aidenfox.stuff.blocks;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.util.CreativeTab.MOD_TAB;

public abstract class Blocks extends Block {

    protected String name;
    protected Boolean blockLore = false;
    //public Integer blockParticle = 1;

    public Blocks(String name,
                  Boolean blockLore,
                  Material material,
                  SoundType snd,
                  String harvestTool,
                  Integer harvestLevel,
                  float hardness,
                  float resistance) {
        super(material);

                this.name = name;
                this.blockLore = blockLore;
                //this.blockParticle = particle;
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
        return false;
    }

    public String getBlockName() {
        return name;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.blockLore) {
            TextComponentTranslation msg = new TextComponentTranslation("tile."+this.name+".lore");
            tooltip.add(msg.getUnformattedText());
        }
    }
}
package foxesworld.aidenfox.stuff.blocks;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static foxesworld.aidenfox.util.CreativeTab.MOD_TAB;
import static foxesworld.aidenfox.util.Utils.addLore;
import static foxesworld.aidenfox.util.Utils.debugSend;

public abstract class Blocks extends Block {

    protected String name;
    protected boolean creatureSpawn;
    protected String itemDrop;
    protected Block thisBlock;

    public Blocks(String name, Material material, SoundType snd, String harvestTool, Integer harvestLevel, float hardness, float resistance, boolean creatureSpawn, String itemDrop) {
        super(material);
        debugSend("[" + name + "] " + material + " | " + snd + " | " + harvestTool + " | " + harvestLevel + " | " + hardness + " | " + resistance);
        this.name = name;
        this.creatureSpawn = creatureSpawn;
        this.itemDrop = itemDrop;
        this.setTranslationKey(name);
        this.setSoundType(snd);
        this.setHarvestLevel(harvestTool, harvestLevel);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(Environment.MODID, name);
        setCreativeTab(MOD_TAB);

        Environment.BLOCKS.add(this);
        thisBlock = this;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return super.isNormalCube(state, world, pos);
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        if(this.creatureSpawn) {
            return super.canCreatureSpawn(state, world, pos, type);
        } else {
            return  false;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        Item dropItem = new Item();
        if(!this.itemDrop.equals("")) {
        if (harvesters.get() != null) {
            EntityPlayer entityPlayer = harvesters.get();
            if (entityPlayer.getHeldItemMainhand() != null) {
                //dropItem = new ItemStack(getBlockFromName(this.itemDrop));
                //for(int j = 0; j < fortune; j++){
                    //Block.getBlockFromName(this.itemDrop);
                dropItem = Environment.ITEMS.get(this.itemDrop);
                //}

            }
        }
        }
        return dropItem;
    }

    public String getBlockName() {
        return name;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.name, "tile", tooltip);
    }
}
/*
 * Copyright (c) 2022  RegData by FoxesWorld
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package foxesworld.hardcontent.data;

import foxesworld.hardcontent.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

import static foxesworld.hardcontent.methods.Utils.debugSend;
import static net.minecraftforge.fml.relauncher.Side.CLIENT;

public class RegData {

    private ResourceLocation assetsLocation;

    public static void regItems() {
        for (Map.Entry entry : Environment.ITEMS.entrySet()) {
            Item thisItem = (Item) entry.getValue();
            ForgeRegistries.ITEMS.registerAll(thisItem);
            itemRenderer(thisItem);
        }
    }

    public static void regBlocks() {
        for (Map.Entry entry : Environment.BLOCKS.entrySet()) {
            Block block = (Block) entry.getValue();
            ForgeRegistries.BLOCKS.register(block);
            ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            blockRenderer(block);
        }
    }

    @SideOnly(CLIENT)
    private static void itemRenderer(Item thisItem) {
        final ResourceLocation regName = thisItem.getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        debugSend("Registering render of  " + regName);
        ModelBakery.registerItemVariants(thisItem, mrl);
        ModelLoader.setCustomModelResourceLocation(thisItem, 0, mrl);
    }
    @SideOnly(CLIENT)
    private static void blockRenderer(Block thisBlock) {
        final ResourceLocation regName = thisBlock.getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        debugSend("Registering render of  " + regName);
        ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(thisBlock), 0, mrl);
    }

    private static String getItemLocation(ResourceLocation regName){
        ResourceLocation location = new ResourceLocation(Environment.MODCFGDIR + Environment.generatedDirName + regName);
        String path = location.getPath();
        return path;
    }


}
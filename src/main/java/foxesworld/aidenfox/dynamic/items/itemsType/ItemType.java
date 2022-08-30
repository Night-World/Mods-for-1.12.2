/*
 * Copyright (c) 2022  ItemType by FoxesWorld
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

package foxesworld.aidenfox.dynamic.items.itemsType;

import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.methods.Utils.addLore;
import static foxesworld.aidenfox.methods.Utils.playFoxesSound;

public class ItemType extends net.minecraft.item.Item {

    private String itemName;
    private  String onRightClick;

    public ItemType(String name, String onRightClick) {
        this.itemName = name;
        this.onRightClick = onRightClick;
        this.setRegistryName(Environment.MODID, name);
        this.setTranslationKey(name);
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.put(name, this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.itemName,"item", tooltip);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand itemHeld) {
        if(!this.onRightClick.equals("")){
            String[] onClickData = this.onRightClick.split("->");
            onClickActions(onClickData[0], onClickData[1], world, player, itemHeld);
        }
        return new ActionResult(EnumActionResult.PASS, player.getHeldItem(itemHeld));
    }

    private void onClickActions(String actionType, String action, World world, EntityPlayer player, EnumHand itemHeld){
     switch(actionType){
         case "send":
             player.sendMessage(new TextComponentString(action));
             break;

         case "playFoxesSound":
             playFoxesSound(world, player, action);
             break;

         default:
             player.sendMessage(new TextComponentString("Unknown action '" + actionType+"'"));
     }
    }
}

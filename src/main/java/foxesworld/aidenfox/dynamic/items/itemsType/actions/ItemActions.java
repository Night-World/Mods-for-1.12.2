/*
 * Copyright (c) 2022  ItemActions by FoxesWorld
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

package foxesworld.aidenfox.dynamic.items.itemsType.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static foxesworld.aidenfox.methods.Utils.playFoxesSound;

public class ItemActions {
    private String itemReguest;
    private String itemRequestData;
    private World world;
    private EntityPlayer player;
    private EnumHand itemHeld;

    public ItemActions(String itemReguest, String itemRequestData, World world, EntityPlayer player, EnumHand itemHeld) {
        this.itemReguest = itemReguest;
        this.itemRequestData = itemRequestData;
        this.world = world;
        this.player = player;
        this.itemHeld = itemHeld;
    }

    public void performAction(){
        switch (this.itemReguest) {
            case "send":
                player.sendMessage(new TextComponentString(this.itemRequestData));
                break;

            case "playFoxesSound":
                playFoxesSound(world, player, this.itemRequestData);
                break;

            default:
                player.sendMessage(new TextComponentString("Unknown action '" + this.itemReguest + "'"));
        }
    }
}

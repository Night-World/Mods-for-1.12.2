/*
 * Copyright (c) 2022  FoodActions by FoxesWorld
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

package foxesworld.hardcontent.dynamic.item.itemActions;

import foxesworld.hardcontent.methods.PlayerMethods;
import foxesworld.hardcontent.methods.SpawnEntity;
import foxesworld.hardcontent.methods.XpParty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static foxesworld.hardcontent.methods.Utils.playFoxesSound;

public class FoodActions {

    private ItemStack item;
    private World world;
    private EntityPlayer player;
    private String foodRequest;
    private String foodRequestData;
    private SpawnEntity eSpawner;
    private PlayerMethods pMethods;

    public FoodActions(ItemStack item, World world, EntityPlayer player, String onEatenEffect, PlayerMethods pMethods) {
        String[] onEatenData = onEatenEffect.split("->");
        this.foodRequest = onEatenData[0];
        this.foodRequestData = onEatenData[1];
        this.item = item;
        this.world = world;
        this.player = player;
        this.pMethods = pMethods;
        this.eSpawner = new SpawnEntity(world, pMethods);
    }

    public void performAction(){
        switch(this.foodRequest){
            case "playFoxesSound":
                playFoxesSound(world, player, this.foodRequestData);
                break;

            case "sendMessage":
                player.sendMessage(new TextComponentString(this.foodRequestData));
                break;

            case "xpParty":
                XpParty.setParticle(EnumParticleTypes.EXPLOSION_HUGE);
                XpParty.XpParty(16.0, Integer.valueOf(this.foodRequestData),this.player, this.world, eSpawner);
                //XpParty.setSoundPlay("event.action.fail");
                break;

            default:
                player.sendMessage(new TextComponentString("Unknown action '" + this.foodRequest + "'"));

        }
    }
}

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

import foxesworld.aidenfox.methods.PlayerMethods;
import foxesworld.aidenfox.methods.SpawnEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static foxesworld.aidenfox.methods.Utils.playFoxesSound;

public class ItemActions {
    private String itemRequest;
    private String itemRequestData;
    private World world;
    private EntityPlayer player;
    private EnumHand itemHeld;
    private  PlayerMethods pMethods;
    private SpawnEntity eSpawner;

    public ItemActions(World world, EntityPlayer player, EnumHand itemHeld, String onRightClick, PlayerMethods pMethods) {
        String[] onClickData = onRightClick.split("->");
        this.itemRequest = onClickData[0];
        this.itemRequestData = onClickData[1];
        this.world = world;
        this.player = player;
        this.itemHeld = itemHeld;
        this.pMethods = pMethods;
    }

    public void performAction(){
        this.eSpawner = new SpawnEntity(world, pMethods);
        switch (this.itemRequest) {
            case "sendMessage":
                player.sendMessage(new TextComponentString(this.itemRequestData));
                break;

            case "playFoxesSound":
                playFoxesSound(world, player, this.itemRequestData);
                break;

            case "thor":
                this.eSpawner.spawnLightningBolt(false);
                this.eSpawner.setParticle(EnumParticleTypes.valueOf(this.itemRequestData));
                this.eSpawner.spawnParticleEntity();
                break;

            case "xpDerp":
                this.eSpawner.spawnExpBottle(pMethods.getPlayerLook('x'), pMethods.getPlayerLook('y'), pMethods.getPlayerLook('z'));
                this.eSpawner.setParticle(EnumParticleTypes.valueOf(this.itemRequestData));
                break;

            case "explode":
                world.createExplosion(player, pMethods.getPlayerLook('x'), pMethods.getPlayerLook('y'), pMethods.getPlayerLook('z'), Float.parseFloat(this.itemRequestData), true);
                break;

            default:
                player.sendMessage(new TextComponentString("Unknown action '" + this.itemRequest + "'"));
        }

    }
}

/*
 * Copyright (c) 2022  Player by FoxesWorld
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

package foxesworld.hardcontent.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

import static foxesworld.hardcontent.proxy.CommonProxy.playerInstance;

public class Player {

    private String playerName;
    private boolean CREATIVE;

    public Player(EntityPlayer player) {
        playerInstance = player;
        this.playerName = player.getName();
        this.CREATIVE = player.isCreative();
    }

    public Double getPlayerLookPoint(Character cordLetter) {
        Map<Character, Double> lookPos = new HashMap();
        RayTraceResult pos = playerInstance.rayTrace(100, 20);
        lookPos.put('x', (double) pos.getBlockPos().getX());
        lookPos.put('y', (double) pos.getBlockPos().getY());
        lookPos.put('z', (double) pos.getBlockPos().getZ());
        return lookPos.get(cordLetter);
    }

    public Double getpointerVector(Character cordLetter) {
        Map<Character, Double> vecPos = new HashMap();
        Vec3d vec = playerInstance.getLookVec();
        vecPos.put('x', vec.x * 1D + playerInstance.posX);
        vecPos.put('y', vec.y * 1D + playerInstance.posY);
        vecPos.put('z', vec.z * 1D + playerInstance.posZ);

        return vecPos.get(cordLetter);
    }

    public boolean isCREATIVE() {
        return CREATIVE;
    }
}

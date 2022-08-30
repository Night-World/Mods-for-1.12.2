/*
 * Copyright (c) 2022  PlayerMethods by FoxesWorld
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

package foxesworld.aidenfox.methods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;

import java.util.HashMap;
import java.util.Map;

public class PlayerMethods {

    private EntityPlayer player;

    public PlayerMethods(EntityPlayer player){
        this.player = player;
    }

    public Double getPlayerLook(Character cord) {
        Map<Character, Double> lookPos = new HashMap();
        RayTraceResult pos = this.player.rayTrace(100, 20);
        lookPos.put('x', (double) pos.getBlockPos().getX());
        lookPos.put('y', (double) pos.getBlockPos().getY());
        lookPos.put('z', (double) pos.getBlockPos().getZ());
        return lookPos.get(cord);
    }

    /*
    public Double getpointerVec3d (Character cord){
        Vec3d vec = this.player.getLookVec();
        double d0 = vec.x*1D+player.posX;
        double d1 = vec.y*1D+player.posY;
        double d2 = vec.z*1D+player.posZ;
    } */

}

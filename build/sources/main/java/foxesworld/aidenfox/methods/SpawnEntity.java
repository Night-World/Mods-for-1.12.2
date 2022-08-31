/*
 * Copyright (c) 2022  SpawnEntity by FoxesWorld
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

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import static net.minecraft.util.EnumParticleTypes.FLAME;

public class SpawnEntity {

    private static World world;
    private static PlayerMethods pMethods;
    private static EnumParticleTypes particle = FLAME;

    public SpawnEntity(World world, PlayerMethods pMethods) {
        this.world = world;
        this.pMethods = pMethods;
    }

    public static void spawnLightningBolt(boolean effectOnly){
        EntityLightningBolt lightning = new EntityLightningBolt(world, pMethods.getPlayerLook('x'), pMethods.getPlayerLook('y'), pMethods.getPlayerLook('z'), effectOnly);
        world.addWeatherEffect(lightning);
    }

    public static void spawnParticleEntity(){
        EntityAreaEffectCloud entity = new EntityAreaEffectCloud(world, pMethods.getPlayerLook('x'), pMethods.getPlayerLook('y'), pMethods.getPlayerLook('z'));
        entity.setParticle(particle);
        entity.setDuration(1);
        world.spawnEntity(entity);
    }

    public static void spawnExpBottle(double x, double y, double z){
        EntityExpBottle EntityExpBottle = new EntityExpBottle(world);
        EntityExpBottle.shoot(x,y,z, (float) (0.0F + pMethods.getPlayerLook('x')), (float) (0.0F + pMethods.getPlayerLook('z')));
        //EntityExpBottle.setLocationAndAngles(x,y,z, (float) (0.0F + pMethods.getPlayerLook('x')), (float) (0.0F + pMethods.getPlayerLook('z')));
        world.spawnEntity(EntityExpBottle);
    }

    public static void setParticle(EnumParticleTypes particle) {
        SpawnEntity.particle = particle;
    }
}

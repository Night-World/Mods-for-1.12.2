/*
 * Copyright (c) 2022  EntityAttributes by FoxesWorld
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

package foxesworld.aidenfox.deprecated.entity.parser;

import net.minecraft.entity.Entity;

public class EntityAttributes {
    private float FOLLOW_RANGE;
    private float MOVEMENT_SPEED;
    private float ATTACK_DAMAGE;
    private float ARMOR;
    private float ARMOR_TOUGHNESS;
    private boolean EntityAISwimming;
    private float EntityAIAttackMeleeNum;
    private boolean EntityAIAttackMeleeBool;
    private float EntityAIWanderDistance;
    private Class<? extends Entity> EntityAIWatchClosest;
    private float EntityAIWatchClosestDistance;
    private boolean EntityAILookIdle;
    private Class<? extends Entity>[] EntityAIHurtByTarget;
    private int MaxSpawnedInChunk;

    public float getFOLLOW_RANGE() {
        return FOLLOW_RANGE;
    }

    public float getMOVEMENT_SPEED() {
        return MOVEMENT_SPEED;
    }

    public float getATTACK_DAMAGE() {
        return ATTACK_DAMAGE;
    }

    public float getARMOR() {
        return ARMOR;
    }

    public float getARMOR_TOUGHNESS() {
        return ARMOR_TOUGHNESS;
    }

    public boolean isEntityAISwimming() {
        return EntityAISwimming;
    }

    public float getEntityAIAttackMeleeNum() {
        return EntityAIAttackMeleeNum;
    }

    public boolean isEntityAIAttackMeleeBool() {
        return EntityAIAttackMeleeBool;
    }

    public float getEntityAIWanderDistance() {
        return EntityAIWanderDistance;
    }

    public Class<? extends Entity> getEntityAIWatchClosest() {
        return EntityAIWatchClosest;
    }

    public float getEntityAIWatchClosestDistance() {
        return EntityAIWatchClosestDistance;
    }

    public boolean isEntityAILookIdle() {
        return EntityAILookIdle;
    }

    public Class<? extends Entity>[] getEntityAIHurtByTarget() {
        return EntityAIHurtByTarget;
    }

    public int getMaxSpawnedInChunk() {
        return MaxSpawnedInChunk;
    }
}

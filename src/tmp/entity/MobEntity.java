/*
 * Copyright (c) 2022  EntityMob by FoxesWorld
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

package foxesworld.aidenfox.deprecated.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MobEntity extends EntityMob {
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

    public MobEntity(World p_i1738_1_, float FOLLOW_RANGE, float MOVEMENT_SPEED, float ATTACK_DAMAGE, float ARMOR, float ARMOR_TOUGHNESS, boolean entityAISwimming, float entityAIAttackMeleeNum, boolean entityAIAttackMeleeBool, float entityAIWanderDistance, Class<? extends Entity> entityAIWatchClosest, float entityAIWatchClosestDistance, boolean entityAILookIdle, Class<? extends Entity>[] entityAIHurtByTarget, int maxSpawnedInChunk) {
        super(p_i1738_1_);
        this.FOLLOW_RANGE = FOLLOW_RANGE;
        this.MOVEMENT_SPEED = MOVEMENT_SPEED;
        this.ATTACK_DAMAGE = ATTACK_DAMAGE;
        this.ARMOR = ARMOR;
        this.ARMOR_TOUGHNESS = ARMOR_TOUGHNESS;
        EntityAISwimming = entityAISwimming;
        EntityAIAttackMeleeNum = entityAIAttackMeleeNum;
        EntityAIAttackMeleeBool = entityAIAttackMeleeBool;
        EntityAIWanderDistance = entityAIWanderDistance;
        EntityAIWatchClosest = entityAIWatchClosest;
        EntityAIWatchClosestDistance = entityAIWatchClosestDistance;
        EntityAILookIdle = entityAILookIdle;
        EntityAIHurtByTarget = entityAIHurtByTarget;
        MaxSpawnedInChunk = maxSpawnedInChunk;
    }

    //Наш доп. урон(ниже о нём)
    public static int ADD_DAMAGE = 15;


    /*Конструктор*/
    public MobEntity(World world) {
        super(world);
        setSize(0.6F, 1.98F);//Размер моба
    }

    /*Конструктор с установкой позиции*/
    public MobEntity(World world, double x, double y, double z) {
        super(world);
        setSize(0.6F, 1.98F);//Размер моба
        setPositionAndUpdate(x, y, z);
    }

    @Override
    protected void applyEntityAttributes() {
        /*Строчка ниже нужна для регистрации атрибутов(Макс. ХП, скорость, сила атаки, броня, скорость полёта и тд.)*/
        super.applyEntityAttributes();
        //Устанавливаем атрибуты
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(this.FOLLOW_RANGE);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.MOVEMENT_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(this.ARMOR);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(this.ARMOR_TOUGHNESS);
    }

    @Override
    protected void initEntityAI() {
        /*Делаем интелект мобу
         * 1 параметр - приоритет
         * 2 параметр - дочерный класс от EntityAIBase
         */
        if(this.EntityAISwimming) {
            this.tasks.addTask(0, new EntityAISwimming(this));//Плавает ли моб
        }
        this.tasks.addTask(1, new EntityAIAttackMelee(this, this.EntityAIAttackMeleeNum, this.EntityAIAttackMeleeBool));//Атака ближнего боя
        this.tasks.addTask(2, new EntityAIWander(this, this.EntityAIWanderDistance));//Моб путешествует
        this.tasks.addTask(3, new EntityAIWatchClosest(this, this.EntityAIWatchClosest, this.EntityAIWatchClosestDistance));//Смотрит на EntityPlayer(игрок)
        if(EntityAILookIdle) {
            this.tasks.addTask(4, new EntityAILookIdle(this));//Грубо говоря - ленивое поварачивание головы
        }
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityZombie.class, EntityPlayer.class}));//Охота за такими мобами: EntityZombie, EntityPlayer
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        /*Что происходит при атаке*/
        if (super.attackEntityAsMob(entityIn)) {//Проверка на атаку
            if (entityIn instanceof EntityLivingBase) {//Если это моб
                ((EntityLivingBase) entityIn).attackEntityAsMob(this);//Делаем последним ударившим нашего моба
                entityIn.attackEntityFrom(((EntityLivingBase) entityIn).getLastDamageSource(), rand.nextInt(ADD_DAMAGE));//Наносим доп. урон
            }
            return true;
        } else {
            return false;
        }
    }
    @Override
    public int getMaxSpawnedInChunk() {
        /*Сколько спавнится в чанке*/
        return this.MaxSpawnedInChunk;
    }
}
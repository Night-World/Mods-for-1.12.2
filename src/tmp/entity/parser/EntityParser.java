/*
 * Copyright (c) 2022  EntityParser by FoxesWorld
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

package foxesworld.hardcontent.deprecated.entity.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.hardcontent.deprecated.entity.MobEntity;
import foxesworld.hardcontent.methods.FileAsStream;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;

public class EntityParser {

    private String mobsFileName;
    private String MODID;
    private Gson gson;

    public EntityParser(String mobsFileName, String MODID) {
        this.mobsFileName = mobsFileName;
        this.MODID = MODID;
    }

    public void readTplFile() {
        FileAsStream structuresJsonStream = new FileAsStream(this.mobsFileName, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        readFromJson(jsonString);
    }

    private void readFromJson(String jsonIn) {
        gson = new Gson();
        TypeToken<List<EntityAttributes>> typeToken = new TypeToken<List<EntityAttributes>>() {};
        List<EntityAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
        for (EntityAttributes obj : object) {
            float followRange = obj.getFOLLOW_RANGE();
            float movingSpeed = obj.getMOVEMENT_SPEED();
            float attackDamage = obj.getATTACK_DAMAGE();
            float armor = obj.getARMOR();
            float armourToughness = obj.getARMOR_TOUGHNESS();
            float EntityAIAttackMeleeNum = obj.getEntityAIAttackMeleeNum();
            float EntityAIWanderDistance = obj.getEntityAIWanderDistance();
            boolean EntityAISwimming = obj.isEntityAISwimming();
            boolean entityAiAttackMeele = obj.isEntityAIAttackMeleeBool();
            Class<? extends Entity> EntityAIWatchClosest = obj.getEntityAIWatchClosest();
            float EntityAIWatchClosestDistance = obj.getEntityAIWatchClosestDistance();
            boolean EntityAILookIdle = obj.isEntityAILookIdle();
            Class<? extends Entity>[] EntityAIHurtByTarget = obj.getEntityAIHurtByTarget();
            int MaxSpawnedInChunk = obj.getMaxSpawnedInChunk();

            final MobEntity mobEntity = new MobEntity(new World() {
                @Override
                protected IChunkProvider createChunkProvider() {
                    return null;
                }

                @Override
                protected boolean isChunkLoaded(int i, int i1, boolean b) {
                    return false;
                }
            },
                    followRange,
                    movingSpeed,
                    attackDamage,
                    armor,
                    armourToughness,
                    EntityAIAttackMeleeNum,
                    EntityAIWanderDistance,
                    EntityAISwimming,
                    entityAiAttackMeele,
                    EntityAIWatchClosest,
                    EntityAIWatchClosestDistance,
                    EntityAILookIdle,
                    EntityAIHurtByTarget,
                    MaxSpawnedInChunk);

        }
    }
}

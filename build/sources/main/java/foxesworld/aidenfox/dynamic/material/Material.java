/*
 * Copyright (c) 2022  Material by FoxesWorld
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

package foxesworld.aidenfox.dynamic.material;

import foxesworld.aidenfox.dynamic.blocks.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static foxesworld.aidenfox.cfg.Environment.MATERIALS;
import static foxesworld.aidenfox.methods.Utils.debugSend;

public class Material {
    String materialName;
    int materialHarvestLevel;
    int materialUsages;
    float materialEfficiency;
    float materialDamage;
    int materialEnchantability;
    String fixMaterial;
    int fixMaterialAmmount;
    int fixMaterialMeta;

    public net.minecraft.item.Item.ToolMaterial MATERIAL;

    public Material(String materialName, int materialHarvestLevel, int materialUsages, float materialEfficiency, float materialDamage, int materialEnchantability, String fixMaterial, int fixMaterialAmmount, int fixMaterialMeta) {
        this.materialName = materialName;
        this.materialHarvestLevel = materialHarvestLevel;
        this.materialUsages = materialUsages;
        this.materialEfficiency = materialEfficiency;
        this.materialDamage = materialDamage;
        this.materialEnchantability = materialEnchantability;
        this.fixMaterial = fixMaterial;
        this.fixMaterialAmmount = fixMaterialAmmount;
        this.fixMaterialMeta = fixMaterialMeta;

        MATERIAL = EnumHelper.addToolMaterial(this.materialName, this.materialHarvestLevel, this.materialUsages, this.materialEfficiency, this.materialDamage, this.materialEnchantability).setRepairItem(new ItemStack(Blocks.getBlockFromName(this.fixMaterial), this.fixMaterialAmmount, this.fixMaterialMeta));
        debugSend("Putting " + this.materialName + " to registry");
        debugSend("Fixmaterial - " + this.fixMaterial +" FixMaterial ammount " + this.fixMaterialAmmount + " FixMaterial meta " + this.fixMaterialMeta);
        MATERIALS.put(this.materialName, this.MATERIAL);
    }
}

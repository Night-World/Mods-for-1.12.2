/*
 * Copyright (c) 2022  NBT by FoxesWorld
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

package foxesworld.hardcontent.methods;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBT {

    public static NBTTagCompound getTagCompound(ItemStack stack) {
        if(stack.hasTagCompound()) {
            return stack.getTagCompound();
        }
        NBTTagCompound data = new NBTTagCompound();
        stack.setTagCompound(data);
        return data;
    }

    public static NBTTagCompound getCompoundTag(NBTTagCompound tag, String key) {
        if(tag.hasKey(key)) {
            return tag.getCompoundTag(key);
        }
        NBTTagCompound data = new NBTTagCompound();
        tag.setTag(key, data);
        return data;
    }

    public static NBTTagCompound getCompoundTag(ItemStack stack, String key) {
        return getCompoundTag(getTagCompound(stack), key);
    }

}

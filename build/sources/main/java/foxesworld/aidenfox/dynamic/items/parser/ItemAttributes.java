/*
 * Copyright (c) 2022  ItemAttributes by FoxesWorld
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

package foxesworld.aidenfox.dynamic.items.parser;

public class ItemAttributes {
    String itemName;
    String itemType;


    String onItemRightClick;
    int amount;
    float saturation;
    boolean isWolfFood;
    boolean alwaysEdible;

    public String getName() {
        return itemName;
    }

    public String getOnItemRightClick() {
        return onItemRightClick;
    }

    public String getItemType(){
        return itemType;
    }

    public int getAmount() {
        return amount;
    }

    public float getSaturation() {
        return saturation;
    }

    public boolean isWolfFood() {
        return isWolfFood;
    }

    public boolean isAlwaysEdible() {
        return alwaysEdible;
    }
}

/*
 * Copyright (c) 2022  AddItem by FoxesWorld
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

package foxesworld.hardcontent.dynamic.item.addingItem;

import foxesworld.hardcontent.dynamic.item.itemsType.FoodType;
import foxesworld.hardcontent.dynamic.item.itemsType.ItemType;

import static foxesworld.hardcontent.data.JsonGenerated.item.GenerateItem.generateItem;
import static foxesworld.hardcontent.methods.Utils.debugSend;

public class AddItem {
    private ItemAttributes itemAttributes;
    private int actionCoolDown = 0;

    public AddItem(ItemAttributes itemAttributes) {
        this.itemAttributes = itemAttributes;
        String itemName = this.itemAttributes.getName();
        switch (this.itemAttributes.getItemType()) {
            case "item":
                String onRightClick = this.itemAttributes.getOnItemRightClick();
                if (!onRightClick.equals("")) {
                    actionCoolDown = this.itemAttributes.getActionCoolDown();
                }
                generateItem(itemName, itemName);
                new ItemType(itemName, onRightClick, actionCoolDown);
                break;

            case "food":
                int amount = this.itemAttributes.getAmount();
                float saturation = this.itemAttributes.getSaturation();
                boolean isWolfFood = this.itemAttributes.isWolfFood;
                boolean alwaysEdible = this.itemAttributes.isAlwaysEdible();
                String onEatenEffect = this.itemAttributes.getOnEatenEffect();
                if (!onEatenEffect.equals("")) {
                    actionCoolDown = this.itemAttributes.getActionCoolDown();
                }
                generateItem(itemName, itemName);
                new FoodType(itemName, amount, saturation, isWolfFood, alwaysEdible, onEatenEffect, actionCoolDown);
                break;
            default:
                debugSend("Unexpected value: " + this.itemAttributes.getItemType());
        }
    }
}

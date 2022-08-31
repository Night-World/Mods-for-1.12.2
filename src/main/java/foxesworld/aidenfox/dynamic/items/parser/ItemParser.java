/*
 * Copyright (c) 2022  ItemParser by FoxesWorld
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.aidenfox.dynamic.items.itemsType.FoodType;
import foxesworld.aidenfox.dynamic.items.itemsType.ItemType;
import foxesworld.aidenfox.methods.FileAsStream;

import java.util.List;

import static foxesworld.aidenfox.cfg.ConfigCreator.regItems;
import static foxesworld.aidenfox.methods.Utils.debugSend;

public class ItemParser {

    private String itemsFileName;
    private String MODID;
    private Gson gson;

    public ItemParser(String itemsFileName, String MODID) {
        this.itemsFileName = itemsFileName;
        this.MODID = MODID;
    }

    public void readTplFile() {
        FileAsStream structuresJsonStream = new FileAsStream(this.itemsFileName, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        readFromJson(jsonString);
    }

    private void readFromJson(String jsonIn) {
        if (regItems) {
            gson = new Gson();
            int actionCoolDown = 0;
            TypeToken<List<ItemAttributes>> typeToken = new TypeToken<List<ItemAttributes>>() {
            };
            List<ItemAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
            for (ItemAttributes obj : object) {
                ItemType item;
                FoodType food;
                String itemName = obj.getName();
                switch (obj.getItemType()) {
                    case "item":
                        String onRightClick = obj.getOnItemRightClick();
                        if (!onRightClick.equals("")) {
                            actionCoolDown = obj.getActionCoolDown();
                        }
                        item = new ItemType(itemName, onRightClick, actionCoolDown);
                        break;

                    case "food":
                        int amount = obj.getAmount();
                        float saturation = obj.getSaturation();
                        boolean isWolfFood = obj.isWolfFood;
                        boolean alwaysEdible = obj.isAlwaysEdible();
                        String onEatenEffect = obj.getOnEatenEffect();
                        if (!onEatenEffect.equals("")) {
                            actionCoolDown = obj.getActionCoolDown();
                        }
                        food = new FoodType(itemName, amount, saturation, isWolfFood, alwaysEdible, onEatenEffect, actionCoolDown);
                        break;
                    default:
                        debugSend("Unexpected value: " + obj.getItemType());
                }
            }
        }
    }

}

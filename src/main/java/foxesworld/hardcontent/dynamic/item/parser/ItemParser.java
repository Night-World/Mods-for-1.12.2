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

package foxesworld.hardcontent.dynamic.item.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.hardcontent.cfg.ConfigInit;
import foxesworld.hardcontent.dynamic.item.addingItem.AddItem;
import foxesworld.hardcontent.dynamic.item.addingItem.ItemAttributes;

import java.util.List;

public final class ItemParser {

    private String fileName;
    private String fileDir;
    private Gson gson;

    public ItemParser(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    public void readFromJson(String jsonIn) {
        if (ConfigInit.CONFIGgenerate.regItems) {
            gson = new Gson();
            TypeToken<List<ItemAttributes>> typeToken = new TypeToken<List<ItemAttributes>>() {
            };
            List<ItemAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
            for (ItemAttributes obj : object) {
                AddItem AddItem= new AddItem(obj);
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDir() {
        return fileDir;
    }

}

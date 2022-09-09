/*
 * Copyright (c) 2022  ConfigurationLoader by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.configuration;

import com.google.common.io.ByteStreams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import foxesworld.hardcontent.Main;
import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.gui.mainmenu.gui.GuiCustom;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class ConfigurationLoader {
    Config config;
    private String configFileName = "mainmenu.json";
    private String configFilePath = "/assets/foxesmod/data_templates/";
    private String configFile = configFilePath + configFileName;

    public ConfigurationLoader(Config config) {
        this.config = config;
    }

    public void load() throws Exception {
        JsonParser jsonParser = new JsonParser();

        File configFolder = new File(Main.INSTANCE.configFolder, Environment.MODID);
        if (!configFolder.exists()) {
            configFolder.mkdir();
        }

        File mainmenuConfig = new File(configFolder, configFileName);
        if (!mainmenuConfig.exists()) {
            InputStream input = null;

            OutputStream output = null;
            try {
                output = new FileOutputStream(mainmenuConfig);
                input = getClass().getResourceAsStream(configFile);
                ByteStreams.copy(input, output);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(output);
                IOUtils.closeQuietly(input);
            }
        }

        File[] jsonFiles = configFolder.listFiles();

        // Preload Main Menu so that other menus can rely on it
        for (File guiFile : jsonFiles) {
            if (guiFile.getName().equals(configFileName)) {
                GuiConfig guiConfig = new GuiConfig();
                String name = guiFile.getName().replace(".json", "");

                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(guiFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    JsonElement jsonElement = jsonParser.parse(reader);
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    guiConfig.load(name, jsonObject);
                } catch (Exception e) {
                    try {
                        reader.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    throw e;
                }

                try {
                    reader.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }

                this.config.addGui(guiConfig.name, new GuiCustom(guiConfig));
            }
        }

        for (File guiFile : jsonFiles) {
            if (!guiFile.getName().equals(configFileName) && guiFile.getName().endsWith(".json")) {
                GuiConfig guiConfig = new GuiConfig();
                String name = guiFile.getName().replace(".json", "");

                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(guiFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    JsonElement jsonElement = jsonParser.parse(reader);
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    guiConfig.load(name, jsonObject);
                } catch (Exception e) {
                    try {
                        reader.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    throw e;
                }

                try {
                    reader.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }

                this.config.addGui(guiConfig.name, new GuiCustom(guiConfig));
            }
        }
    }
}

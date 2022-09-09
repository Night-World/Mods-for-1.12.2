/*
 * Copyright (c) 2022  TextResourceLocation by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.lib.texts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextResourceLocation implements IText {
    String string;
    ResourceLocation resourceLocation;

    public TextResourceLocation(String resourceString) {
        resourceLocation = new ResourceLocation(resourceString);
        string = "";
    }

    @Override
    public String get() {
        if (string == null) {
            return "";
        }

        if (string.equals("")) {
            IResource resource = null;
            try {
                resource = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (resource != null) {
                BufferedReader in = null;
                in = new BufferedReader(new InputStreamReader(resource.getInputStream()));

                StringBuilder builder = new StringBuilder();

                String inputLine = null;
                try {
                    inputLine = in.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ;
                do {
                    builder.append(inputLine);

                    try {
                        inputLine = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }

                    if (inputLine != null) {
                        builder.append("\n");
                    }
                }
                while (inputLine != null);

                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                string = builder.toString();
            } else {
                string = null;

                return "";
            }
        }

        return string;
    }

}

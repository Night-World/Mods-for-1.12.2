/*
 * Copyright (c) 2022  LoadTextureURL by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.handler;

import foxesworld.hardcontent.gui.mainmenu.lib.textures.TextureURL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadTextureURL extends Thread {
    TextureURL texture;

    public LoadTextureURL(TextureURL texture) {
        this.texture = texture;

        this.setDaemon(true);
    }

    @Override
    public void run() {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(texture.getURL());
        } catch (IOException e) {

        }

        if (bi != null) {
            texture.finishLoading(bi);
        }
    }
}

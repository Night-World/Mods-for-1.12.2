/*
 * Copyright (c) 2022  TextureURL by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.lib.textures;

import foxesworld.hardcontent.gui.mainmenu.MainMenu;
import foxesworld.hardcontent.gui.mainmenu.handler.LoadTextureURL;
import foxesworld.hardcontent.gui.mainmenu.lib.StringReplacer;
import foxesworld.hardcontent.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class TextureURL implements ITexture {
    URL url;
    int textureID;
    private BufferedImage bi;

    public TextureURL(String url) {
        this.textureID = -1;
        try {
            this.url = new URL(StringReplacer.replacePlaceholders(url));
        } catch (MalformedURLException e) {
            Main.INSTANCE.logger.log(Level.ERROR, "Invalid URL: " + url);
            e.printStackTrace();
        }

        new LoadTextureURL(this).start();
    }

    @Override
    public void bind() {
        if (this.textureID != -1) {
            GlStateManager.bindTexture(this.textureID);
        } else {
            if (bi != null) {
                setTextureID(TextureUtil.uploadTextureImageAllocate(GL11.glGenTextures(), bi, false, false));
                bind();
                return;
            }
            MainMenu.bindTransparent();
        }
    }

    public void finishLoading(BufferedImage bi) {
        this.bi = bi;
    }

    public URL getURL() {
        return url;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

}

/*
 * Copyright (c) 2022  Panorama by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.configuration.elements;

import foxesworld.hardcontent.gui.mainmenu.configuration.GuiConfig;
import foxesworld.hardcontent.gui.mainmenu.lib.textures.ITexture;

public class Panorama extends Element {
    String images;

    public ITexture[] locations;

    public boolean blur;
    public boolean gradient;
    public boolean animate;
    public boolean synced;
    public int position;
    public int animationSpeed;

    public Panorama(GuiConfig parent, String images, boolean blur, boolean gradient) {
        super(parent);
        this.images = images;
        this.blur = blur;
        this.gradient = gradient;
        this.animate = true;
        this.animationSpeed = 1;
        this.synced = false;

        locations = new ITexture[6];

        for (int i = 0; i < 6; i++) {
            String rl = images.replace("%c", i + "");
            locations[i] = GuiConfig.getWantedTexture(rl);
        }
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }
}

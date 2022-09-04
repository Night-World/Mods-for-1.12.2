/*
 * Copyright (c) 2022  GuiSettngs by FoxesWorld
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

package foxesworld.hardcontent.gui;

import foxesworld.hardcontent.cfg.Environment;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiSettings extends GuiContainer {

    final private ResourceLocation texture = new ResourceLocation(Environment.MODID, "textures/gui/bg.png");

    public GuiSettings() {
        super(new EmptyContainer());
        System.out.println("doot doot " + texture.getPath());
        //drawGuiContainerBackgroundLayer(1,2,3);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        int zX = (width - xSize) / 2;
        int zY = (height - ySize) / 2;
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(zX, 0, 0, 0, xSize, mc.displayHeight);
    }
}

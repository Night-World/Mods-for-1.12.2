/*
 * Copyright (c) 2022  GuiSettings by FoxesWorld
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

package foxesworld.hardcontent.cfg.GuiSettings;

import foxesworld.hardcontent.cfg.ConfigCreator;
import foxesworld.hardcontent.cfg.Environment;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;

public class GuiSettings extends GuiContainer{

    public GuiSettings(){
        super(new EmptyContainer());
        System.out.println("I'm here!!!");
        xSize = 180;
        ySize = 200;
    }

    final private ResourceLocation texture = new ResourceLocation(Environment.MODID, "textures/gui/bg.png");

    @Override
    public void initGui(){
        buttonList.clear();
        buttonList.add(new GuiButton(1, (this.width - xSize) / 2 + 5, this.height / 2 - 80, 170, 20, I18n.format(I18n.format("customdebug.coords") + ": " + on(ConfigCreator.regItems))));
    }

    @Override
    protected void actionPerformed(GuiButton button){
        switch (button.id) {
            case 1: {
                ConfigCreator.regItems = swap(ConfigCreator.regItems);
                this.initGui();
                ConfigManager.sync(Environment.MODID, Config.Type.INSTANCE);
                break;
            }
        }
    }

    public String on(boolean check) {
        if(check)
            return I18n.format(Environment.MODID+".on");
        else
            return I18n.format(Environment.MODID+".off");
    }
    public boolean swap(boolean on) {
        if(on)
            return false;
        else
            return true;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mX, int mY) {
        int zX = (width - xSize) / 2;
        int zY = (height - ySize) / 2;
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(zX, 0, 0, 0, xSize, mc.displayHeight);
    }
}

/*
 * Copyright (c) 2022  GuiCustomConfirmOpenLink by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiCustomConfirmOpenLink extends GuiYesNo
{
    private final String openLinkWarning;
    private final String copyLinkButtonText;
    private final String linkText;
    private boolean showSecurityWarning = true;
    private static final String __OBFID = "CL_00000683";

    public GuiCustomConfirmOpenLink(GuiYesNoCallback p_i1084_1_, String p_i1084_2_, int p_i1084_3_, boolean p_i1084_4_)
    {
        super(p_i1084_1_, I18n.format(p_i1084_4_ ? "chat.link.confirmTrusted" : "chat.link.confirm", new Object[0]), p_i1084_2_, p_i1084_3_);
        this.confirmButtonText = I18n.format(p_i1084_4_ ? "chat.link.open" : "gui.yes", new Object[0]);
        this.cancelButtonText = I18n.format(p_i1084_4_ ? "gui.cancel" : "gui.no", new Object[0]);
        this.copyLinkButtonText = I18n.format("chat.copy", new Object[0]);
        this.openLinkWarning = I18n.format("chat.link.warning", new Object[0]);
        this.linkText = p_i1084_2_;
    }

    public void initGui()
    {
    	super.initGui();
    	this.buttonList.remove(0);
    	this.buttonList.remove(0);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50 - 105, this.height / 6 + 96, 100, 20, this.confirmButtonText));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height / 6 + 96, 100, 20, this.copyLinkButtonText));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50 + 105, this.height / 6 + 96, 100, 20, this.cancelButtonText));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 2)
        {
            this.copyLinkToClipboard();
        }

        this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
    }

    public void copyLinkToClipboard()
    {
        setClipboardString(this.linkText);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.showSecurityWarning)
        {
            this.drawCenteredString(this.fontRenderer, this.openLinkWarning, this.width / 2, 110, 16764108);
        }
    }

    public void disableSecurityWarning()
    {
        this.showSecurityWarning = false;
    }
}
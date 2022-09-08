/*
 * Copyright (c) 2022  ActionLoadWorld by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.lib.actions;

import java.io.File;
import java.io.FileInputStream;

import foxesworld.hardcontent.gui.mainmenu.gui.GuiCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.GuiOldSaveLoadConfirm;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.StartupQuery;

public class ActionLoadWorld implements IAction
{
	String dirName;
	String saveName;

	public ActionLoadWorld(String dirName, String saveName)
	{
		this.dirName = dirName;
		this.saveName = saveName;
	}

	@Override
	public void perform(Object source, GuiCustom menu)
	{
		File dir = new File(FMLClientHandler.instance().getSavesDir(), dirName);
		NBTTagCompound leveldat;
		try
		{
			leveldat = CompressedStreamTools.readCompressed(new FileInputStream(new File(dir, "level.dat")));
		}
		catch (Exception e)
		{
			try
			{
				leveldat = CompressedStreamTools.readCompressed(new FileInputStream(new File(dir, "level.dat_old")));
			}
			catch (Exception e1)
			{
				FMLLog.warning("There appears to be a problem loading the save %s, both level files are unreadable.", dirName);
				return;
			}
		}
		NBTTagCompound fmlData = leveldat.getCompoundTag("FML");
		if (fmlData.hasKey("ModItemData"))
		{
			FMLClientHandler.instance().showGuiScreen(new GuiOldSaveLoadConfirm(dirName, saveName, menu));
		}
		else
		{
			try
			{
				Minecraft.getMinecraft().launchIntegratedServer(dirName, saveName, (WorldSettings) null);
			}
			catch (StartupQuery.AbortedException e)
			{
				// ignore
			}
		}
	}

}

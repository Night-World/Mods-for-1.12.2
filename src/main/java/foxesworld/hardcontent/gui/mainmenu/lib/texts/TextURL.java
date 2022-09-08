/*
 * Copyright (c) 2022  TextURL by FoxesWorld
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

import java.net.MalformedURLException;
import java.net.URL;

import foxesworld.hardcontent.gui.mainmenu.lib.StringReplacer;
import foxesworld.hardcontent.gui.mainmenu.handler.LoadStringURL;

public class TextURL implements IText
{
	URL url;
	public String string;

	int refreshInterval;
	int refreshCounter;

	LoadStringURL loadThread;

	public TextURL(String url, int refreshInterval)
	{
		try
		{
			this.url = new URL(StringReplacer.replacePlaceholders(url));
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}

		this.string = "";

		(loadThread = new LoadStringURL(this)).start();

		this.refreshInterval = refreshInterval;
		this.refreshCounter = 0;
	}

	public void tick()
	{
		if (this.refreshInterval != -1 && this.refreshInterval >= 60)
		{
			this.refreshCounter++;
			if (this.refreshCounter >= this.refreshInterval && !this.loadThread.isAlive())
			{
				(loadThread = new LoadStringURL(this)).start();
				this.refreshCounter = 0;
			}
		}
	}

	@Override
	public String get()
	{
		synchronized (string)
		{
			return string;
		}
	}

	public URL getURL()
	{
		return url;
	}
}

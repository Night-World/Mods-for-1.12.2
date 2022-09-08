/*
 * Copyright (c) 2022  Slideshow by FoxesWorld
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Slideshow extends Element
{
	public ITexture[] ressources;
	public int displayDuration;

	private int counter;
	public int fadeDuration;
	private boolean fading = false;

	public Slideshow(GuiConfig parent, String[] images)
	{
		super(parent);
		ressources = new ITexture[images.length];

		this.displayDuration = 60;
		this.counter = 0;
		this.fadeDuration = 20;

		for (int i = 0; i < images.length; i++)
		{
			ressources[i] = GuiConfig.getWantedTexture(images[i]);
		}
	}

	public void shuffle()
	{
		List<ITexture> list = Arrays.asList(ressources);
		Collections.shuffle(list);
		ressources = (ITexture[]) list.toArray();
	}

	public void update()
	{
		counter++;

		fading = (counter % (displayDuration + fadeDuration)) > displayDuration;
	}

	public boolean fading()
	{
		return fading;
	}

	public float getAlphaFade(float partial)
	{
		float counterProgress = ((counter + partial) % (displayDuration + fadeDuration)) - displayDuration;

		float durationTeiler = 1F / fadeDuration;
		float alpha = durationTeiler * counterProgress;
		return alpha;
	}

	public ITexture getCurrentResource1()
	{
		int index = counter / ((displayDuration + fadeDuration)) % ressources.length;
		return ressources[index];
	}

	public ITexture getCurrentResource2()
	{
		if (fading)
		{
			int index = (counter + fadeDuration) / ((displayDuration + fadeDuration)) % ressources.length;
			return ressources[index];
		}
		return null;
	}
}

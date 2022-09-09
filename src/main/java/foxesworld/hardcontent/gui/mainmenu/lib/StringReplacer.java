/*
 * Copyright (c) 2022  StringReplacer by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.lib;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StringReplacer {
    static final String TIME_FORMAT = "HH:mm";
    static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);

    static Field mcpversionField;
    static Field mcversionField;

    public static String mcpversion;
    public static String mcversion;

    static {
        try {
            mcpversionField = Loader.class.getDeclaredField("mcpversion");
            mcpversionField.setAccessible(true);
            mcpversion = (String) mcpversionField.get(null);

            mcversionField = ForgeVersion.class.getDeclaredField("mcVersion");
            mcversion = (String) mcversionField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String replacePlaceholders(String source) {
        int tModCount = Loader.instance().getModList().size();
        int aModCount = Loader.instance().getActiveModList().size();
        Calendar calendar = Calendar.getInstance();

        String clock = timeFormat.format(calendar.getTime());

        DateFormat formatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());

        String date = formatter.format(new Date());

        return source.replace("#date#", date).replace("#time#", clock).replace("#mcversion#", mcversion).replace("#fmlversion#", Loader.instance().getFMLVersionString()).replace("#mcpversion#", mcpversion).replace("#modsloaded#", tModCount + "").replace("#modsactive#", aModCount + "").replace("#forgeversion#", ForgeVersion.getVersion()).replace("#username#", Minecraft.getMinecraft().getSession().getUsername());
    }
}

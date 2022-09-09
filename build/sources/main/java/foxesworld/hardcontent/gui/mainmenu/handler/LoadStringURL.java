/*
 * Copyright (c) 2022  LoadStringURL by FoxesWorld
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

import foxesworld.hardcontent.gui.mainmenu.lib.texts.TextURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoadStringURL extends Thread {
    TextURL text;

    public LoadStringURL(TextURL text) {
        this.text = text;

        this.setDaemon(true);
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(text.getURL().openStream()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        String inputLine = null;
        do {
            if (inputLine != null) {
                builder.append(inputLine);
            }

            String newInput = null;
            try {
                newInput = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (inputLine != null) {
                builder.append("\n");
            }

            inputLine = newInput;
        }
        while (inputLine != null);

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (text.string) {
            text.string = builder.toString();
        }
    }
}

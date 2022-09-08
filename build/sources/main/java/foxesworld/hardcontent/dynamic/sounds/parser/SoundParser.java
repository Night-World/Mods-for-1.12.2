/*
 * Copyright (c) 2022  SoundParser by FoxesWorld
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

package foxesworld.hardcontent.dynamic.sounds.parser;

import com.google.gson.Gson;
import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.methods.FileAsStream;

import java.util.HashMap;

public class SoundParser {

    private String soundFile;
    private Object soundContents;
    HashMap<String, Object> SoundsMap;
    private Gson gson;

    public SoundParser(String soundFile) {
        this.soundFile = soundFile;
    }

    public Object parse(){
        soundContents = new FileAsStream(this.soundFile, Environment.MODID).getFileContents();
        return soundContents;
    }
}

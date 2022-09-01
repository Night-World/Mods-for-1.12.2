/*
 * Copyright (c) 2022  BufferedFileReader by FoxesWorld
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

package foxesworld.aidenfox.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedFileReader {

    public static String BufferedFileReader(String pathToFile){
        System.out.println("ThisPath " + pathToFile);
        String fileCntents = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathToFile));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileCntents = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileCntents;
    }
}

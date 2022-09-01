/*
 * Copyright (c) 2022  WriteFile by FoxesWorld
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WriteFile {

    public static void appendToFile(String content, String path) {
        List<String> contentList = Arrays.asList(content.split("\n"));
        BufferedWriter bw = null;
        boolean myappend = true;
        try {
            bw = new BufferedWriter(new FileWriter(path, myappend));
            for (String line : contentList) {
                bw.write(line);
                bw.write("\n");
                //bw.newLine();
            }
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                // ignore it  or write notice
            }
        }

    }
}

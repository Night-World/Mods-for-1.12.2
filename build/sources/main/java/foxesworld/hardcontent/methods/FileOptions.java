/*
 * Copyright (c) 2022  FileOptions by FoxesWorld
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

package foxesworld.hardcontent.methods;

import foxesworld.hardcontent.cfg.Environment;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static foxesworld.hardcontent.cfg.Environment.dataTemplateDir;

public class FileOptions {

    public static String BufferedFileReader(String pathToFile) {
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

    public static void createIfnotExists(String fileDir, String filename) {
        File cfgFile = new File(fileDir + filename);
        FileAsStream templateFile = new FileAsStream(dataTemplateDir + filename, Environment.MODID);
        Object templateFileContents = templateFile.getFileContents();
        if (!cfgFile.exists()) {
            try {
                cfgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            appendToFile(String.valueOf(templateFileContents), String.valueOf(cfgFile));
        }
    }

    public static void appendToFile(String content, String path) {
        List<String> contentList = Arrays.asList(content.split("\n"));
        BufferedWriter bw = null;
        boolean myappend = true;
        try {
            bw = new BufferedWriter(new FileWriter(path, myappend));
            for (String line : contentList) {
                bw.write(line);
                bw.write("\n");
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

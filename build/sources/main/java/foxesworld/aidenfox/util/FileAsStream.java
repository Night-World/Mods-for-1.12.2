package foxesworld.aidenfox.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileAsStream {


    private InputStream fileContents;
    private String fullPath;
    private String modDir;
    private final String pathRoot = "assets/";

    public FileAsStream(String filePath, String ModDir) {
        this.modDir = ModDir;
        this.fullPath = this.pathRoot + this.modDir +"/"+  filePath;
        this.getFileAsIOStream();
    }

    private void  getFileAsIOStream() {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(this.fullPath);

        if (ioStream == null) {
            throw new IllegalArgumentException(this.fullPath + " is not found");
        }
        this.fileContents = ioStream;
    }

    public Object getFileContents() {
        Object returnText = "";
        try {
            try (InputStreamReader isr = new InputStreamReader(this.fileContents);
                 BufferedReader br = new BufferedReader(isr);) {
                String line;
                while ((line = br.readLine()) != null) {
                    returnText+=line.replace("   ", "");
                }
                this.fileContents.close();
            }
        } catch (IOException e) {
        }
        return returnText;
    }

}

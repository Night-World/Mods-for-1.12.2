package foxesworld.aidenfox.stuff.sounds;

import foxesworld.aidenfox.cfg.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileAsStream {

    private final String pathRoot = "assets/" + Environment.MODID + "/";
    private InputStream fileContents;
    private String fullPath;

    public FileAsStream(String path) {
        this.fullPath = this.pathRoot + path;
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

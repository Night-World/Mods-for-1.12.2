package foxesworld.hardcontent.methods;

import java.io.*;

public class FileAsStream {


    private InputStream fileContents;
    private String fullPath;
    private String fileName;
    private String modDir;
    private final String pathRoot = "assets/";

    public FileAsStream(String filePath, String ModDir) {
        this.modDir = ModDir;
        this.fileName = filePath;
        this.fullPath = this.pathRoot + this.modDir + "/" + filePath;
        this.getFileAsIOStream();
    }

    private void getFileAsIOStream() {
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
                    returnText += line;
                    returnText += "\n";
                }
                this.fileContents.close();
            }
        } catch (IOException e) {
        }
        return returnText;
    }

}

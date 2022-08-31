package foxesworld.aidenfox.methods;

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
        if (this.fullPath.contains("json")) {
            if (!new File(this.fullPath).exists()) {
                try {
                    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(this.modDir + File.separator + this.fileName), "utf-8"))) {
                        writer.write("something");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //  Read or copy - WIP!!!
        }
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
                    returnText += line.replace("   ", "");
                }
                this.fileContents.close();
            }
        } catch (IOException e) {
        }
        return returnText;
    }

}

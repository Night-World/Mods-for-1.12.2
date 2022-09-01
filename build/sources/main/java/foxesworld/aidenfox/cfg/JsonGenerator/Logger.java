package foxesworld.aidenfox.cfg.JsonGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
    private PrintWriter writer;

    public Logger() {
        try {
            this.writer = new PrintWriter(JsonGenerator.bindDir + "/log.txt", "UTF-8");
        } catch (FileNotFoundException var2) {
            var2.printStackTrace();
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

    }

    public void log(String text) {
        this.writer.println(text);
        System.out.println(text);
    }

    public void end() {
        this.writer.close();
    }


    /*TO REWRITE USING SWITCH CASE */
    public void logItem(String name) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String file = JsonGenerator.bindDir + "/output/item/" + name + ".json";
        this.log("[" + sdf.format(cal.getTime()) + "] Created Item: " + file);
    }

    public void logBlock(String name) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String file = JsonGenerator.bindDir + "/output/block/" + name + ".json";
        String time = sdf.format(cal.getTime());
        this.log("[" + time + "] Created Block: " + file);
    }

    public void logBlockstate(String name) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String file = JsonGenerator.bindDir + "/output/blockstate/" + name + ".json";
        this.log("[" + sdf.format(cal.getTime()) + "] Created Blockstate: " + file);
    }

    public void logRecipe(String name) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String file = JsonGenerator.bindDir + "/output/recipes/" + name + ".json";
        this.log("[" + sdf.format(cal.getTime()) + "] Created Recipe: " + file);
    }
}

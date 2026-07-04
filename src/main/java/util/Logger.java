package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static File logFile;

    public static void log(String message) {
        System.out.println(message);
        if (logFile != null) {
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(message + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setLogFile(File logFile) {
        Logger.logFile = logFile;
    }
}

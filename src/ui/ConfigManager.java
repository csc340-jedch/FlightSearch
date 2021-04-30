package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConfigManager {
    private final static String CFG = "settings.cfg";

    public static String readSetting(String _key) {
        File file = new File(CFG);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split("=");
            if (arr[0].equals(_key) && arr.length == 2) {
                scanner.close();
                return arr[1];
            }
        }
        return null;
    }

    public static boolean configExists() {
        File file = new File(CFG);
        return file.exists();
    }

    public static void writeConfig(String _username, String _password) {
        File file = new File(CFG);
        PrintWriter writer;

        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        writer.println("username=" + _username);

        writer.println("password=" + _password);

        writer.close();
    }
}

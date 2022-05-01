package ru.job4j.concurrent;

import java.io.*;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        String output = "";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
                output += (char) data;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContentWithoutUnicode() {
        String output = "";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}

package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }
/*
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
    }*/

    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}

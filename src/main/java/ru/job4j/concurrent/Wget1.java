package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget1 implements Runnable {
    private final String url;
    private final int speed;
    private final String file;

    public Wget1(String url, int speed, String file) {
        this.url = url;
        this.speed = speed;
        this.file = file;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long byteWrite = 0;
            long start = System.currentTimeMillis();
            /*bytesRead - Это кол-во прочтенных за раз байт*/
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                byteWrite += bytesRead;
                if (byteWrite > speed) {
                    long delta = System.currentTimeMillis() - start;
                    if (delta < 1000) {
                        Thread.sleep(delta);
                    }
                    start = System.currentTimeMillis();
                    byteWrite = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void validation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Количество аргументов не равно трем");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validation(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];
        Thread wget = new Thread(new Wget1(url, speed, file));
        wget.start();
        wget.join();
    }
}

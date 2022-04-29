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
                        Thread.sleep(1000);
                    }
                    start = System.currentTimeMillis();
                    byteWrite = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];*/
        String url = "https://proof.ovh.net/files/10Mb.dat";
        String file = "C:/projects/job4j_threads/data/10Mb.dat";
        int speed = 1024 * 1024; /*ограничивать скорость до 1 мегабайта в секунду*/
        Thread wget = new Thread(new Wget1(url, speed, file));
        wget.start();
        wget.join();
    }
}

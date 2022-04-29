package ru.job4j.concurrent;

import java.util.ArrayList;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] arr = new String[]{"\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (String s : arr) {
                    Thread.sleep(500);
                    System.out.print("\r load: " + s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }


}

package org.urfu.practice2.Task5;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task5 implements Task {
    private final String FILE_URL;
    private final String FILE_NAME;
    private final AtomicBoolean isStopped = new AtomicBoolean(false);

    public Task5(String fileUrl, String fileName) {
        FILE_URL = fileUrl;
        FILE_NAME = fileName;
    }

    @Override
    public void start() {
        System.out.println("Загрузка началась");
        Thread downloadThread = new Thread(this::download);
        downloadThread.start();
    }

    @Override
    public void stop() {
        isStopped.set(true);
    }

    private void download() {
        File file = new File(FILE_NAME);
        int totalBytesRead = 0;

        try (BufferedInputStream in = new BufferedInputStream(new URI(FILE_URL).toURL().openStream());
             FileOutputStream out = new FileOutputStream(FILE_NAME)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                if (isStopped.get()) {
                    System.out.println("Загрузка остановлена");
                    break;
                }
                out.write(dataBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (file.exists() && !file.delete()) {
                System.out.println("Не удалось удалить файл");
            }
        }

        System.out.println(totalBytesRead + " байт скачано");
    }
}

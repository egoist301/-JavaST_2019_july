package by.training.task06;

import java.util.concurrent.TimeUnit;

public class ThreadsApp {
    public static void main(String[] args) {
        CommonResource commonResource = new CommonResource();
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Поток " + i);
            t.start();
        }
    }
}

class CommonResource {
    int x = 0;
}

class CountThread implements Runnable {
    CommonResource resource;

    public CountThread(final CommonResource resourceNew) {
        resource = resourceNew;
    }

    @Override
    public void run() {
        synchronized (resource) {
            resource.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s  %d  \n", Thread.currentThread().getName(), resource.x);
                resource.x++;
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException eNew) {
                    eNew.printStackTrace();
                }
            }
        }
    }
}

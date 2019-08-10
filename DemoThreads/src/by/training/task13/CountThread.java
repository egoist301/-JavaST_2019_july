package by.training.task13;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CountThread implements Runnable {
    CommonResource resource;
    Semaphore semaphore;
    String name;

    public CountThread(final CommonResource resourceNew, final Semaphore semaphoreNew, final String nameNew) {
        resource = resourceNew;
        semaphore = semaphoreNew;
        name = nameNew;
    }

    @Override
    public void run() {
        try{
            System.out.println(name + " ожидает разрешение");
            semaphore.acquire();
            resource.x = 1;
            for (int i = 1; i < 5; ++i){
                System.out.println(name + ": "+ resource.x);
                resource.x++;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException eNew){
            eNew.printStackTrace();
        }
        System.out.println(name + " освобождает разрешение");
        semaphore.release();
    }
}

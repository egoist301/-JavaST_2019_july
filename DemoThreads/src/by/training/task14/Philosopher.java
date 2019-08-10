package by.training.task14;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {
    Semaphore semaphore;
    int num;
    int id;

    Philosopher(Semaphore semaphoreNew, int idNew) {
        semaphore = semaphoreNew;
        id = idNew;
    }

    @Override
    public void run() {
        try {
            while (num < 3) {
                semaphore.acquire();
                System.out.println("Философ " + id + " садится за стол");
                TimeUnit.MILLISECONDS.sleep(500);
                num++;
                System.out.println("Философ " + id + " выходит из-за стола");
                semaphore.release();
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException eNew) {
            eNew.printStackTrace();
        }
    }
}

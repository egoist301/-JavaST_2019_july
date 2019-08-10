package by.training.task13;

import java.util.concurrent.Semaphore;

public class Runner {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        CommonResource resource = new CommonResource();
        for (int i = 1; i < 4; ++i) {
            new Thread(new CountThread(resource, semaphore, "CountThread " + i)).start();
        }
    }
}

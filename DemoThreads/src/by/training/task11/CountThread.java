package by.training.task11;

import java.util.concurrent.locks.ReentrantLock;

public class CountThread implements Runnable {
    CommonResource commonResource;
    ReentrantLock reentrantLock;

    public CountThread(final CommonResource commonResourceNew, final ReentrantLock reentrantLockNew) {
        commonResource = commonResourceNew;
        reentrantLock = reentrantLockNew;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        try {
            commonResource.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d\n", Thread.currentThread().getName(), commonResource.x);
                commonResource.x++;
                Thread.sleep(100);
            }
        } catch (InterruptedException eNew){
            System.err.println(eNew);
        } finally {
            reentrantLock.unlock();
        }
    }
}

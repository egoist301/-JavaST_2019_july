package by.training.task03;

import java.util.concurrent.TimeUnit;

public class PriorityRunner {
    public static void main(String[] args) {
        PriorThread min = new PriorThread("min");
        PriorThread max = new PriorThread("max");
        PriorThread norm = new PriorThread("norm");
        min.setPriority(Thread.MIN_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        min.setDaemon(true);
        min.start();
        max.start();
        norm.start();

        try {
            norm.join();
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException eNew) {
            eNew.printStackTrace();
        }
        max.interrupt();
    }
}

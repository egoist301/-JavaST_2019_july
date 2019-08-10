package by.training.task02;

import java.util.concurrent.TimeUnit;

public class RunnablePerson extends Person implements Runnable {
    public RunnablePerson(final String nameNew) {
        super(nameNew);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": Hello world!");
            try {
                Thread.sleep(100);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException eNew) {
                eNew.printStackTrace();
            }
        }
    }
}

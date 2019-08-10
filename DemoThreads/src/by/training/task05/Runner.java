package by.training.task05;

import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Главный поток начал работу...");
        ThreadToDisable threadToDisable = new ThreadToDisable();
        Thread thread = new Thread(threadToDisable, "name of thread");
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            threadToDisable.disable();
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException eNew) {
            eNew.printStackTrace();
        }
        System.out.println("end");
    }
}

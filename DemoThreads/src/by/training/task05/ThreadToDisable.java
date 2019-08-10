package by.training.task05;

import java.util.concurrent.TimeUnit;

public class ThreadToDisable implements Runnable {
    private boolean isActive;
    void disable(){
        isActive = false;
    }

    public ThreadToDisable() {
        isActive = true;
    }

    @Override
    public void run() {
        System.out.printf("Поток %s начал работу...", Thread.currentThread().getName());
        int couter = 1;
        while (isActive){
            System.out.println("Цикл " + couter++);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException eNew) {
                System.out.println("Поток прерван");
            }
        }
        System.out.printf("Поток %s завершил работу...", Thread.currentThread().getName());
    }
}

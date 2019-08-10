package by.training.task20;

import java.util.concurrent.Exchanger;

public class GetThread implements Runnable {
    Exchanger<String> exchanger;
    String message;

    public GetThread(final Exchanger<String> exchangerNew) {
        exchanger = exchangerNew;
        message = "Привет мир!";
    }

    @Override
    public void run() {
        try {
            message += " - It is the message from GetThread (" + Thread.currentThread().getName() + ")";
            message = exchanger.exchange(message);
            System.out.println("GetThread " + Thread.currentThread().getName() + " получил: " + message);
        } catch (InterruptedException eNew) {
            eNew.printStackTrace();
        }
    }
}

package by.training.task09;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread {
    Store store;
    int product = 5;

    Producer(Store storeNew) {
        store = storeNew;
    }

    @Override
    public void run() {
        try {
            while(product > 0){
                product = product -store.put();
                System.out.println("производителю осталось произвести " + product + " товар(ов)");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException eNew){
            eNew.printStackTrace();
        }
    }
}

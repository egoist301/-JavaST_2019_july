package by.training.task09;

import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {
    Store store;
    int product = 0;
    final int N = 5;
    Consumer (Store storeNew){
        store = storeNew;
    }

    @Override
    public void run() {
        try{
            while (product < N){
                product = product + store.get();
                System.out.println("Потребитель купил "+ product + " товар(ов)");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException eNew){
            eNew.printStackTrace();
        }
    }
}

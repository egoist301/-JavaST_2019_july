package by.training.task12;

public class Producer implements Runnable {
    Store store;
    Producer(Store storeNew){
        store = storeNew;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; ++i){
            store.put();
        }
    }
}

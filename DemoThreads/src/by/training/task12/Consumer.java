package by.training.task12;

public class Consumer implements Runnable {
    Store store;

    Consumer(Store storeNew) {
        store = storeNew;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; ++i) {
            store.get();
        }
    }
}

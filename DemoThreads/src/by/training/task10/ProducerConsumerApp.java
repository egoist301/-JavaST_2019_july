package by.training.task10;

public class ProducerConsumerApp {
    public static void main(String[] args) {
        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();
    }
}

class Store {
    int counter = 0;
    final int N = 5;

    synchronized int put() {
        while (counter >= 3){
            try {
                wait();
            } catch (InterruptedException eNew){
                System.err.println(eNew);
            }
        }
        if (counter <= N) {
            counter++;
            System.out.println("склад имеет " + counter + " товаров");
            notify();
            return 1;
        }
        return 0;
    }

    synchronized int get() {
        while (counter == 0){
            try {
                wait();
            } catch (InterruptedException eNew){
                System.err.println(eNew);
            }
        }

        if (counter > 0) {
            counter--;
            System.out.println("склад имеет " + counter + " товаров");
            notify();
            return 1;
        }
        return 0;
    }
}

class Producer extends Thread {
    Store store;
    int product = 5;

    Producer(Store storeNew) {
        store = storeNew;
    }

    public void run() {
        try {
            while (product > 0) {
                product = product - store.put();
                System.out.println("производителю осталось произвести " + product);
                sleep(1000);
            }
        } catch (InterruptedException eNew) {
            System.err.println(eNew);
        }
        System.out.println("Поток производителя прерван");
    }
}

class Consumer extends Thread {
    Store store;
    int product = 0;
    final int N = 10;
    Consumer(Store storeNew){
        store = storeNew;
    }

    public void run(){
        try{
            while(product < N){
                product = product + store.get();
                System.out.println("Потребитель купил " + product);
                sleep(1000);
            }
        } catch (InterruptedException eNew){
            System.err.println(eNew);
        }
        System.out.println("Поток потребителя прерван");
    }
}
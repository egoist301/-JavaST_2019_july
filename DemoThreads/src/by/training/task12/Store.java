package by.training.task12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    private int product = 0;
    private ReentrantLock locker;
    private Condition condition;

    Store() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void get() {
        locker.lock();
        try {
            while (product < 1)
                condition.await();
            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);

            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
    public void put() {
        locker.lock();
        try {
            while (product >= 3)
            condition.await();
            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
}

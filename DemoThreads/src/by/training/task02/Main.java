package by.training.task02;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        RunnablePerson p1 = new RunnablePerson("Alice");
        Thread thread = new Thread(p1);
        thread.start();
        RunnablePerson p2 = new RunnablePerson("Bob");
        Thread thread1 = new Thread(p2);
        thread1.start();
        thread.join();
        System.out.println("end");
    }
}

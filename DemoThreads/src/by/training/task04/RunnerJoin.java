package by.training.task04;

public class RunnerJoin {
    static {
        System.out.println("Start thread main");
    }
    public static void main(String[] args) {
        JoinThread t1 = new JoinThread("First");
        JoinThread t2 = new JoinThread("Second");
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException eNew){
            eNew.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}

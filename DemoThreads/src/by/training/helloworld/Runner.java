package by.training.helloworld;

public class Runner {
    public static void main(String[] args) {
        Thread thread = new HelloWorld();
        thread.run();
        System.out.println("name = " + thread.getName() + " state = " + thread.getState());
        thread.start();
        System.out.println("state = " + thread.getState());
    }
}

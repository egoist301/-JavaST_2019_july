package by.training.helloworld;

public class HelloWorld extends Thread{
    public void run(){
        System.out.println(currentThread());
        System.out.println("Hello world");
    }
}

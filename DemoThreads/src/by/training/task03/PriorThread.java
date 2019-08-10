package by.training.task03;

public class PriorThread extends Thread {
    public PriorThread(final String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " " + i);
            yield();
            if (isInterrupted()) {
                return;
            }
        }
    }
}

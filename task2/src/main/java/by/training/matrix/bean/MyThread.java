package by.training.matrix.bean;

/**
 * MyThread.
 */
public class MyThread extends Thread {
    /**
     * Number associated with thread.
     */
    private int number;

    /**
     * @param runnableNew runnable.
     * @param numberNew number.
     */
    public MyThread(final Runnable runnableNew, final int numberNew) {
        super(runnableNew);
        number = numberNew;
    }

    /**
     * @return number.
     */
    public int getNumber() {
        return number;
    }
}

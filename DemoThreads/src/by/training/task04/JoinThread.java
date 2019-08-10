package by.training.task04;

import java.util.concurrent.TimeUnit;

public class JoinThread extends Thread {
    public JoinThread(final String name) {
        super(name);
    }

    @Override
    public void run() {
        String nameT = getName();
        long timeout = 0;
        System.out.println("Start thread " + nameT);
        try {
            switch (nameT) {
                case "First":
                    timeout = 5000;
                    break;
                case "Second":
                    timeout = 1000;
            }
            TimeUnit.MILLISECONDS.sleep(timeout);
            System.out.println("End thread " + nameT);
        } catch (InterruptedException eNew){
            eNew.printStackTrace();
        }
    }
}

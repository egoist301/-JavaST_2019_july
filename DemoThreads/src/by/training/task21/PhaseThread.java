package by.training.task21;

import java.util.concurrent.Phaser;

public class PhaseThread implements Runnable {
    Phaser phaser;
    String name;
    PhaseThread (Phaser phaserNew, String nameNew){
        phaser = phaserNew;
        name = nameNew;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println(name +  " выполняет фазу "+ phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name +  " выполняет фазу "+ phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name +  " выполняет фазу "+ phaser.getPhase());
        phaser.arriveAndDeregister();
    }
}

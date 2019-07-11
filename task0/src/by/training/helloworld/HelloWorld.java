package by.training.helloworld;

import org.apache.log4j.Logger;

public final class HelloWorld {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(HelloWorld.class);

    /**
     * Default constructor.
     */
    private HelloWorld() {
    }

    /**
     * @param args command line.
     */
    public static void main(final String[] args) {
        print("Hello world!!!");
        print("Hello java!!!");
    }

    private static void print(final Object object) {
        LOGGER.info(object);
    }
}

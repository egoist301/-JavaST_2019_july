package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.Flower;

import java.util.Set;

/**
 * Abstract builder.
 */
public interface AbstractBuilder {
    /**
     * Build set flowers.
     *
     * @param fileName fileName.
     */
    void buildSetFlowers(String fileName);

    /**
     * Getter.
     *
     * @return set flowers.
     */
    Set<Flower> getFlowers();
}

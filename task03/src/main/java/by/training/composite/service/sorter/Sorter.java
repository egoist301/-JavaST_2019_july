package by.training.composite.service.sorter;

import by.training.composite.bean.Component;

/**
 * Sorter interface.
 */
public interface Sorter {
    /**
     * Sort tree.
     * @param componentNew component.
     */
    void sort(Component componentNew);
}

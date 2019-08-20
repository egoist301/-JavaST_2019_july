package by.training.composite.bean;

/**
 * Component of composite.
 */
public interface Component {
    /**
     * Gathers text.
     *
     * @return string.
     */
    String compose();

    /**
     * Getter.
     *
     * @param index index in composite.
     * @return child component.
     */
    Component getChild(int index);

    /**
     * Remove component of composite.
     *
     * @param componentNew component.
     */
    void remove(Component componentNew);

    /**
     * Add component in composite.
     *
     * @param componentNew component.
     */
    void add(Component componentNew);

    /**
     * Calculate amount of children.
     *
     * @return amount of children.
     */
    int calculateAmountOfChildren();
}

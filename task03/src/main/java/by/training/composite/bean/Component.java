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
    default Component getChild(int index) {
        return null;
    }

    /**
     * Remove component of composite.
     *
     * @param componentNew component.
     */
    default void remove(Component componentNew) {
    }

    /**
     * Add component in composite.
     *
     * @param componentNew component.
     */
    default void add(Component componentNew) {

    }

    /**
     * Calculate amount of children.
     *
     * @return amount of children.
     */
    int calculateAmountOfChildren();
}

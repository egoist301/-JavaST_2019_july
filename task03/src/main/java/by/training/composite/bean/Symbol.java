package by.training.composite.bean;

/**
 * Symbol.
 */
public class Symbol implements Component {
    /**
     * Symbol.
     */
    private Character character;

    /**
     * Constructor.
     *
     * @param characterNew symbol.
     */
    public Symbol(final Character characterNew) {
        character = characterNew;
    }


    /**
     * Gathers text.
     *
     * @return string.
     */
    @Override
    public String collect() {
        return character.toString();
    }

    /**
     * Getter.
     *
     * @param index index in composite.
     * @return child component.
     */
    @Override
    public Component getChild(final int index) {
        return null;
    }

    /**
     * Remove component of composite.
     *
     * @param componentNew component.
     */
    @Override
    public void remove(final Component componentNew) {

    }

    /**
     * Add component in composite.
     *
     * @param componentNew component.
     */
    @Override
    public void add(final Component componentNew) {

    }

    /**
     * Calculate amount of children.
     *
     * @return amount of children.
     */
    @Override
    public int calculateAmountOfChildren() {
        return 0;
    }
}

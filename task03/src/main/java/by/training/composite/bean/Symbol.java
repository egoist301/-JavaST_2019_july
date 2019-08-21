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
    public String compose() {
        return character.toString();
    }
}

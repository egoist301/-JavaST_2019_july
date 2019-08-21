package by.training.composite.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Sentence.
 */
public class Sentence implements Component {
    /**
     * Delimiter.
     */
    private static final String REGEX_SEPARATOR = " ";
    /**
     * Lexemes.
     */
    private List<Component> lexemes = new ArrayList<>();

    /**
     * Gathers text.
     *
     * @return string.
     */
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(REGEX_SEPARATOR);
        for (Component component : lexemes) {
            stringJoiner.add(component.compose());
        }
        return stringJoiner.toString();
    }

    /**
     * Getter.
     *
     * @param index index in composite.
     * @return child component.
     */
    @Override
    public Component getChild(final int index) {
        return lexemes.get(index);
    }

    /**
     * Remove component of composite.
     *
     * @param componentNew component.
     */
    @Override
    public void remove(final Component componentNew) {
        if (componentNew != null) {
            lexemes.remove(componentNew);
        }

    }

    /**
     * Add component in composite.
     *
     * @param componentNew component.
     */
    @Override
    public void add(final Component componentNew) {
        if (componentNew != null) {
            lexemes.add(componentNew);
        }
    }

    /**
     * Calculate amount of children.
     *
     * @return amount of children.
     */
    @Override
    public int calculateAmountOfChildren() {
        return lexemes.size();
    }
}

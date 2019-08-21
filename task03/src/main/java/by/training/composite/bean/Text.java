package by.training.composite.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Text.
 */
public class Text implements Component {
    /**
     * Tab regex.
     */
    private static final String TAB_REGEX = "\t";
    /**
     * New line regex.
     */
    private static final String NEW_LINE_REGEX = "\n";
    /**
     * Delimiter regex.
     */
    private static final String DELIMITER_REGEX = "\n\t";
    /**
     * Paragraphs.
     */
    private List<Component> paragraphs = new ArrayList<>();

    /**
     * Gathers text.
     *
     * @return string.
     */
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER_REGEX,
                TAB_REGEX, NEW_LINE_REGEX);
        for (Component component : paragraphs) {
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
        return paragraphs.get(index);
    }

    /**
     * Remove component of composite.
     *
     * @param componentNew component.
     */
    @Override
    public void remove(final Component componentNew) {
        if (componentNew != null) {
            paragraphs.remove(componentNew);
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
            paragraphs.add(componentNew);
        }
    }

    /**
     * Calculate amount of children.
     *
     * @return amount of children.
     */
    @Override
    public int calculateAmountOfChildren() {
        return paragraphs.size();
    }
}

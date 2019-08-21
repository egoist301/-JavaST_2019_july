package by.training.composite.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Paragraph.
 */
public class Paragraph implements Component {
    /**
     * Delimiter.
     */
    private static final String REGEX_SEPARATOR = " ";
    /**
     * Sentences.
     */
    private List<Component> sentences = new ArrayList<>();

    /**
     * Gathers text.
     *
     * @return string.
     */
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(REGEX_SEPARATOR);
        for (Component component : sentences) {
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
        return sentences.get(index);
    }

    /**
     * Remove component of composite.
     *
     * @param componentNew component.
     */
    @Override
    public void remove(final Component componentNew) {
        if (componentNew != null) {
            sentences.remove(componentNew);
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
            sentences.add(componentNew);
        }
    }

    /**
     * Calculate amount of children.
     *
     * @return amount of children.
     */
    @Override
    public int calculateAmountOfChildren() {
        return sentences.size();
    }
}

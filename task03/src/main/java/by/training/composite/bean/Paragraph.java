package by.training.composite.bean;

import java.util.List;

/**
 * Paragraph.
 */
public class Paragraph implements Component {
    /**
     * Sentences.
     */
    private List<Component> sentences;

    /**
     * Constructor.
     *
     * @param sentencesNew sentences.
     */
    public Paragraph(final List<Component> sentencesNew) {
        sentences = sentencesNew;
    }

    /**
     * Gathers text.
     *
     * @return string.
     */
    @Override
    public String collect() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : sentences) {
            stringBuilder.append(component.collect());
        }
        return stringBuilder.toString();
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

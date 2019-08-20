package by.training.composite.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Paragraph.
 */
public class Paragraph implements Component {
    /**
     * Sentences.
     */
    private List<Component> sentences = new ArrayList<>();
    /**
     * Type of component.
     */
    private TypeComponent typeComponent = TypeComponent.PARAGRAPH;

    /**
     * Getter.
     *
     * @return type of component.
     */
    public TypeComponent getTypeComponent() {
        return typeComponent;
    }

    /**
     * Gathers text.
     *
     * @return string.
     */
    @Override
    public String compose() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : sentences) {
            stringBuilder.append(component.compose());
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

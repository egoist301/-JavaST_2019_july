package by.training.composite.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Text.
 */
public class Text implements Component {
    /**
     * Paragraphs.
     */
    private List<Component> paragraphs = new ArrayList<>();
    /**
     * Type of component.
     */
    private TypeComponent typeComponent = TypeComponent.TEXT;

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
        for (Component component : paragraphs) {
            stringBuilder.append("\t" + component.compose() + "\n");
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

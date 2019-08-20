package by.training.composite.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Word.
 */
public class Word implements Component {
    /**
     * List of symbols.
     */
    private List<Component> symbols = new ArrayList<>();
    /**
     * Type of component.
     */
    private TypeComponent typeComponent = TypeComponent.WORD;

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
        for (Component component : symbols) {
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
        return symbols.get(index);
    }

    /**
     * Remove component of composite.
     *
     * @param componentNew component.
     */
    @Override
    public void remove(final Component componentNew) {
        if (componentNew != null) {
            symbols.remove(componentNew);
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
            symbols.add(componentNew);
        }
    }

    /**
     * Calculate amount of children.
     *
     * @return amount of children.
     */
    @Override
    public int calculateAmountOfChildren() {
        return symbols.size();
    }
}

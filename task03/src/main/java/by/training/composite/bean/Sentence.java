package by.training.composite.bean;

import java.util.List;

/**
 * Sentence.
 */
public class Sentence implements Component {
    /**
     * Lexemes.
     */
    private List<Component> lexemes;

    /**
     * Constructor.
     *
     * @param lexemesNew lexemes.
     */
    public Sentence(final List<Component> lexemesNew) {
        lexemes = lexemesNew;
    }

    /**
     * Gathers text.
     *
     * @return string.
     */
    @Override
    public String collect() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : lexemes) {
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

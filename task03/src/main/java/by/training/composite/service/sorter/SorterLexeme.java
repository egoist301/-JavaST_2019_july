package by.training.composite.service.sorter;

import by.training.composite.bean.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sorts tokens in the text in descending order of occurrences of a given
 * character, and in case of equality - alphabetically.
 */
public class SorterLexeme implements Sorter {
    /**
     * Symbol.
     */
    private char symbol;

    /**
     * Constructor.
     *
     * @param symbolNew symbol.
     */
    public SorterLexeme(final char symbolNew) {
        symbol = symbolNew;
    }

    /**
     * Sort tree.
     *
     * @param componentNew component.
     */
    @Override
    public void sort(final Component componentNew) {
        Comparator<Component> componentComparator = (Component o1, Component o2)
                -> {
            char[] chars1 = o1.compose().toCharArray();
            char[] chars2 = o2.compose().toCharArray();
            int number1 = 0;
            for (char symbol1 : chars1) {
                if (symbol1 == symbol) {
                    ++number1;
                }
            }
            int number2 = 0;
            for (char symbol2 : chars2) {
                if (symbol2 == symbol) {
                    ++number2;
                }
            }
            return number2 - number1;
        };
        Comparator<Component> comparator =
                componentComparator.thenComparing(Component::compose);
        int countOfChildren = componentNew.calculateAmountOfChildren();
        for (int i = 0; i < countOfChildren; ++i) {
            int size = componentNew.getChild(i).calculateAmountOfChildren();
            for (int j = 0; j < size; ++j) {
                sortLexeme(componentNew.getChild(i).getChild(j),
                        comparator);
            }
        }
    }

    /**
     * Sort lexeme.
     *
     * @param componentNew           component.
     * @param componentComparatorNew comparator.
     */
    private void sortLexeme(final Component componentNew,
                            final Comparator<Component>
                                    componentComparatorNew) {
        List<Component> components = new ArrayList<>();
        int countOfChildren = componentNew.calculateAmountOfChildren();
        for (int i = 0; i < countOfChildren; ++i) {
            components.add(componentNew.getChild(i));
        }
        components.sort(componentComparatorNew);
        removeAll(components, componentNew);
        addAll(components, componentNew);
    }

    /**
     * Remove all component from tree.
     *
     * @param componentsNew list of component.
     * @param componentNew  component.
     */
    private void removeAll(final List<Component> componentsNew,
                           final Component componentNew) {
        for (Component component : componentsNew) {
            componentNew.remove(component);
        }
    }

    /**
     * Add all component in tree.
     *
     * @param componentsNew list of component.
     * @param componentNew  component.
     */
    private void addAll(final List<Component> componentsNew,
                        final Component componentNew) {
        for (Component component : componentsNew) {
            componentNew.add(component);
        }
    }
}

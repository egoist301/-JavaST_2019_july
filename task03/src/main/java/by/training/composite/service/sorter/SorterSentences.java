package by.training.composite.service.sorter;

import by.training.composite.bean.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Sort sentences in paragraphs by count of words.
 */
public class SorterSentences implements Sorter {
    /**
     * Sort tree.
     *
     * @param componentNew component.
     */
    @Override
    public void sort(final Component componentNew) {
        int countOfChildren = componentNew.calculateAmountOfChildren();
        for (int i = 0; i < countOfChildren; ++i) {
            sortSentences(componentNew.getChild(i));

        }
    }

    /**
     * Sort sentences.
     *
     * @param componentNew component.
     */
    private void sortSentences(final Component componentNew) {
        List<Component> components = new ArrayList<>();
        int countOfChildren = componentNew.calculateAmountOfChildren();
        for (int i = 0; i < countOfChildren; ++i) {
            components.add(componentNew.getChild(i));
        }
        components.sort((Component o1, Component o2)
                -> (o1.calculateAmountOfChildren()
                - o2.calculateAmountOfChildren()));
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

package by.training.composite.service.sorter;

import by.training.composite.bean.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Sort words in sentences by length.
 */
public class SorterWords implements Sorter {
    /**
     * Sort tree.
     *
     * @param componentNew component.
     */
    @Override
    public void sort(final Component componentNew) {
        int countOfChildren = componentNew.calculateAmountOfChildren();
        for (int i = 0; i < countOfChildren; ++i) {
            int size = componentNew.getChild(i).calculateAmountOfChildren();
            for (int j = 0; j < size; ++j) {
                sortWords(componentNew.getChild(i).getChild(j));
            }

        }
    }

    /**
     * Sort sentences.
     *
     * @param componentNew component.
     */
    private void sortWords(final Component componentNew) {
        List<Component> components = new ArrayList<>();
        int countOfChildren = componentNew.calculateAmountOfChildren();
        for (int i = 0; i < countOfChildren; ++i) {
            components.add(componentNew.getChild(i));
        }
        components.sort((Component o1, Component o2)
                -> (o1.getChild(0).calculateAmountOfChildren()
                - o2.getChild(0).calculateAmountOfChildren()));
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

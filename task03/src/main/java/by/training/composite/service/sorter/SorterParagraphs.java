package by.training.composite.service.sorter;

import by.training.composite.bean.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Sort paragraphs by count of sentence.
 */
public class SorterParagraphs implements Sorter {
    /**
     * Sort paragraphs of tree.
     *
     * @param componentNew component.
     */
    @Override
    public void sort(final Component componentNew) {
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
}

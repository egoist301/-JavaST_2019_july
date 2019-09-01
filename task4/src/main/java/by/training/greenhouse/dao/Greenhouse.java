package by.training.greenhouse.dao;

import by.training.greenhouse.bean.Flower;

import java.util.List;

public class Greenhouse {
    private List<Flower> flowers;
    private static final Greenhouse GREENHOUSE = new Greenhouse();

    public static Greenhouse getGREENHOUSE() {
        return GREENHOUSE;
    }

    public void add(final Flower flowerNew) {
        if (flowerNew != null) {
            flowers.add(flowerNew);
        }
    }

    public void remove(final Flower flowerNew) {
        if (flowerNew != null) {
            flowers.remove(flowerNew);
        }
    }

    public Flower get(final int index) {
        return flowers.get(index);
    }

    public int size() {
        return flowers.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Flower flower : flowers) {
            stringBuilder.append(flower);
        }
        return stringBuilder.toString();
    }
}

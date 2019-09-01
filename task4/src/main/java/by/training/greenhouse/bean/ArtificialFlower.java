package by.training.greenhouse.bean;

import java.util.Objects;

public class ArtificialFlower extends Flower {
    private String material;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(final String materialNew) {
        material = materialNew;
    }

    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof ArtificialFlower)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        ArtificialFlower that = (ArtificialFlower) oNew;
        return Objects.equals(getMaterial(), that.getMaterial());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMaterial());
    }

    @Override
    public String toString() {
        return "ArtificialFlower{" + super.toString() +
                "material='" + material + '\'' +
                '}';
    }
}

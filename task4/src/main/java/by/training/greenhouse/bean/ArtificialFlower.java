package by.training.greenhouse.bean;

import java.util.Objects;

/**
 * Artificial flower.
 */
public class ArtificialFlower extends Flower {
    /**
     * Material.
     */
    private String material;

    /**
     * Getter.
     *
     * @return material.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Setter.
     *
     * @param materialNew material.
     */
    public void setMaterial(final String materialNew) {
        material = materialNew;
    }

    /**
     * Equal this object with any object.
     *
     * @param oNew some object.
     * @return equal or not.
     */
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

    /**
     * Hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMaterial());
    }

    /**
     * To string.
     *
     * @return string object.
     */
    @Override
    public String toString() {
        return "ArtificialFlower{" + super.toString()
                + "material='" + material + '\'' + '}';
    }
}

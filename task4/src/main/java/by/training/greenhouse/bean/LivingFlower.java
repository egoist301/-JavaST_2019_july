package by.training.greenhouse.bean;

import java.util.Objects;

/**
 * Living flower.
 */
public class LivingFlower extends Flower {
    /**
     * Is medicinal.
     */
    private boolean medicinal;
    /**
     * Is photophilous.
     */
    private boolean photophilous;

    /**
     * Getter.
     *
     * @return medicinal.
     */
    public boolean isMedicinal() {
        return medicinal;
    }

    /**
     * Setter.
     *
     * @param medicinalNew medicinal.
     */
    public void setMedicinal(final boolean medicinalNew) {
        medicinal = medicinalNew;
    }

    /**
     * Getter.
     *
     * @return photophilous.
     */
    public boolean isPhotophilous() {
        return photophilous;
    }

    /**
     * Setter.
     *
     * @param photophilousNew photophilous.
     */
    public void setPhotophilous(final boolean photophilousNew) {
        photophilous = photophilousNew;
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
        if (!(oNew instanceof LivingFlower)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        LivingFlower that = (LivingFlower) oNew;
        return isMedicinal() == that.isMedicinal()
                && isPhotophilous() == that.isPhotophilous();
    }

    /**
     * Hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isMedicinal(), isPhotophilous());
    }

    /**
     * To string.
     *
     * @return string object.
     */
    @Override
    public String toString() {
        return "LivingFlower{" + super.toString()
                + "medicinal=" + medicinal
                + ", photophilous=" + photophilous + '}';
    }
}

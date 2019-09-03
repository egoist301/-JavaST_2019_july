package by.training.greenhouse.bean;

import java.util.Objects;

/**
 * Living flower.
 */
public class LivingFlower extends Flower {
    /**
     * Watering.
     */
    private int watering;
    /**
     * Soil.
     */
    private Soil soil;
    /**
     * Multiplying.
     */
    private Multiplying multiplying;
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
     * Getter.
     * @return soil.
     */
    public Soil getSoil() {
        return soil;
    }

    /**
     * Setter.
     * @param soilNew soil.
     */
    public void setSoil(final Soil soilNew) {
        soil = soilNew;
    }

    /**
     * Getter.
     * @return multiplying.
     */
    public Multiplying getMultiplying() {
        return multiplying;
    }

    /**
     * Setter.
     * @param multiplyingNew multiplying.
     */
    public void setMultiplying(final Multiplying multiplyingNew) {
        multiplying = multiplyingNew;
    }

    /**
     * Getter.
     * @return watering.
     */
    public int getWatering() {
        return watering;
    }

    /**
     * Setter.
     * @param wateringNew watering.
     */
    public void setWatering(final int wateringNew) {
        watering = wateringNew;
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
                && isPhotophilous() == that.isPhotophilous()
                && getSoil().equals(that.soil)
                && getMultiplying().equals(that.multiplying)
                && getWatering() == that.watering;
    }

    /**
     * Hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isMedicinal(), isPhotophilous(),
                getMultiplying(), getSoil(), getWatering());
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
                + ", photophilous=" + photophilous + ", watering=" + watering
                + ", soil=" + soil + ", multiplying=" + multiplying + '}';
    }
}

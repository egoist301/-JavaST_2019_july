package by.training.greenhouse.bean;

import java.util.Objects;

public class LivingFlower extends Flower {
    private boolean isMedicinal;
    private boolean isPhotophilous;

    public boolean isMedicinal() {
        return isMedicinal;
    }

    public void setMedicinal(final boolean medicinalNew) {
        isMedicinal = medicinalNew;
    }

    public boolean isPhotophilous() {
        return isPhotophilous;
    }

    public void setPhotophilous(final boolean photophilousNew) {
        isPhotophilous = photophilousNew;
    }

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
        return isMedicinal() == that.isMedicinal() &&
                isPhotophilous() == that.isPhotophilous();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isMedicinal(), isPhotophilous());
    }

    @Override
    public String toString() {
        return "LivingFlower{" + super.toString() +
                "isMedicinal=" + isMedicinal +
                ", isPhotophilous=" + isPhotophilous +
                '}';
    }
}

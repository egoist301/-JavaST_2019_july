package by.training.greenhouse.bean;

import by.training.greenhouse.garbage.Color;

import java.util.Objects;

public abstract class Flower {
    private String name;
    private String origin;
    private Color stemColor;
    private int height;
    private String temperature;
    private int id;

    public Flower() {

    }

    public String getName() {
        return name;
    }

    public void setName(final String nameNew) {
        name = nameNew;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String originNew) {
        origin = originNew;
    }

    public Color getStemColor() {
        return stemColor;
    }

    public void setStemColor(final Color stemColorNew) {
        stemColor = stemColorNew;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int heightNew) {
        height = heightNew;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(final String temperatureNew) {
        temperature = temperatureNew;
    }

    public int getId() {
        return id;
    }

    public void setId(final int idNew) {
        id = idNew;
    }

    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof Flower)) {
            return false;
        }
        Flower flower = (Flower) oNew;
        return getHeight() == flower.getHeight() &&
                getId() == flower.getId() &&
                Objects.equals(getName(), flower.getName()) &&
                Objects.equals(getOrigin(), flower.getOrigin()) &&
                getStemColor() == flower.getStemColor() &&
                Objects.equals(getTemperature(), flower.getTemperature());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getOrigin(), getStemColor(), getHeight(),
                getTemperature(), getId());
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", stemColor=" + stemColor +
                ", height=" + height +
                ", temperature='" + temperature + '\'' +
                ", id=" + id;
    }
}

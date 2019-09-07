package by.training.greenhouse.bean;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Flower. Abstract class.
 */
public abstract class Flower {
    /**
     * Name flower.
     */
    private String name;
    /**
     * Origin.
     */
    private String origin;
    /**
     * Stem color.
     */
    private Color stemColor;
    /**
     * Height.
     */
    private int height;
    /**
     * Temperature.
     */
    private String temperature;
    /**
     * ID.
     */
    private int id;
    /**
     * Discovery date.
     */
    private LocalDate discoveryDate;

    /**
     * Default constructor.
     */
    public Flower() {

    }

    /**
     * Getter.
     *
     * @return flower name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter.
     *
     * @param nameNew flower name.
     */
    public void setName(final String nameNew) {
        name = nameNew;
    }

    /**
     * Getter.
     *
     * @return origin.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Setter.
     *
     * @param originNew origin.
     */
    public void setOrigin(final String originNew) {
        origin = originNew;
    }

    /**
     * Getter.
     *
     * @return stem color.
     */
    public Color getStemColor() {
        return stemColor;
    }

    /**
     * Setter.
     *
     * @param stemColorNew stem color.
     */
    public void setStemColor(final Color stemColorNew) {
        stemColor = stemColorNew;
    }

    /**
     * Getter.
     *
     * @return height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter.
     *
     * @param heightNew height.
     */
    public void setHeight(final int heightNew) {
        height = heightNew;
    }

    /**
     * Getter.
     *
     * @return temperature.
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Setter.
     *
     * @param temperatureNew temperature.
     */
    public void setTemperature(final String temperatureNew) {
        temperature = temperatureNew;
    }

    /**
     * Getter.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     *
     * @param idNew id.
     */
    public void setId(final int idNew) {
        id = idNew;
    }

    /**
     * Getter.
     *
     * @return discovery date.
     */
    public LocalDate getDiscoveryDate() {
        return discoveryDate;
    }

    /**
     * Setter.
     *
     * @param discoveryDateNew discovery date.
     */
    public void setDiscoveryDate(final LocalDate discoveryDateNew) {
        discoveryDate = discoveryDateNew;
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
        if (!(oNew instanceof Flower)) {
            return false;
        }
        Flower flower = (Flower) oNew;
        return getHeight() == flower.getHeight()
                && getDiscoveryDate().equals(flower.discoveryDate)
                && getId() == flower.getId()
                && getName().equals(flower.getName())
                && getOrigin().equals(flower.getOrigin())
                && getStemColor() == flower.getStemColor()
                && getTemperature().equals(flower.getTemperature());
    }

    /**
     * Hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getOrigin(), getStemColor(), getHeight(),
                getTemperature(), getId(), getDiscoveryDate());
    }

    /**
     * To string.
     *
     * @return string object.
     */
    @Override
    public String toString() {
        return "id=" + id
                + ", name='" + name + '\''
                + ", origin='" + origin + '\''
                + ", stemColor=" + stemColor
                + ", height=" + height
                + ", temperature='" + temperature + '\''
                + ", discovery date='" + discoveryDate + '\'';
    }
}

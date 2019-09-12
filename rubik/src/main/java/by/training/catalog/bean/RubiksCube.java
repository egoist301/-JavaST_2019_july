package by.training.catalog.bean;

import java.time.LocalDateTime;
import java.util.Objects;

public class RubiksCube extends Entity {
    private String name;
    private double price;
    private String info;
    private Manufacturer manufacturer;
    private double weight;
    private PlasticColor plasticColor;
    private boolean primaryPlastic;
    private String size;
    private TypeCube typeCube;
    private LocalDateTime localDateTime;


    public RubiksCube() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String sizeNew) {
        size = sizeNew;
    }

    public TypeCube getTypeCube() {
        return typeCube;
    }

    public void setTypeCube(final TypeCube typeCubeNew) {
        typeCube = typeCubeNew;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(final LocalDateTime localDateTimeNew) {
        localDateTime = localDateTimeNew;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameNew) {
        name = nameNew;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double priceNew) {
        price = priceNew;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(final String infoNew) {
        info = infoNew;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final Manufacturer manufacturerNew) {
        manufacturer = manufacturerNew;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weightNew) {
        weight = weightNew;
    }

    public PlasticColor getPlasticColor() {
        return plasticColor;
    }

    public void setPlasticColor(final PlasticColor plasticColorNew) {
        plasticColor = plasticColorNew;
    }

    public boolean isPrimaryPlastic() {
        return primaryPlastic;
    }

    public void setPrimaryPlastic(final boolean primaryPlasticNew) {
        primaryPlastic = primaryPlasticNew;
    }

    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof RubiksCube)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        RubiksCube that = (RubiksCube) oNew;
        return Double.compare(that.getPrice(), getPrice()) == 0
                && Double.compare(that.getWeight(), getWeight()) == 0
                && isPrimaryPlastic() == that.isPrimaryPlastic()
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getInfo(), that.getInfo())
                && getManufacturer() == that.getManufacturer()
                && getPlasticColor() == that.getPlasticColor()
                && Objects.equals(getSize(), that.getSize())
                && getTypeCube() == that.getTypeCube() &&
                Objects
                        .equals(getLocalDateTime(), that.getLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getPrice(), getInfo(),
                getManufacturer(), getWeight(), getPlasticColor(),
                isPrimaryPlastic(), getSize(), getTypeCube(),
                getLocalDateTime());
    }

    @Override
    public String toString() {
        return "RubiksCube{" + super.toString()
                + ", name='" + name + '\''
                + ", price=" + price
                + ", info='" + info + '\''
                + ", manufacturer=" + manufacturer
                + ", weight=" + weight
                + ", plasticColor=" + plasticColor
                + ", primaryPlastic=" + primaryPlastic
                + ", size='" + size + '\''
                + ", typeCube=" + typeCube
                + ", localDateTime=" + localDateTime + '}';
    }
}

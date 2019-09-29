package by.training.catalog.bean;

import java.util.Date;
import java.util.Objects;

public class RubiksCube extends Entity {
    private String model;
    private double price;
    private double weight;
    private String info;
    private boolean primaryPlastic;
    private String size;
    private String plasticColor;
    private String manufacturer;
    private String form;
    private Date date;
    private boolean blocked;

    public RubiksCube(final long idNew) {
        super(idNew);
    }

    public RubiksCube(final long idNew, final String modelNew,
                      final double priceNew,
                      final double weightNew, final String infoNew,
                      final boolean primaryPlasticNew,
                      final String sizeNew,
                      final String plasticColorNew,
                      final String manufacturerNew,
                      final String formNew, final Date dateNew,
                      final boolean blockedNew) {
        super(idNew);
        model = modelNew;
        price = priceNew;
        weight = weightNew;
        info = infoNew;
        primaryPlastic = primaryPlasticNew;
        size = sizeNew;
        plasticColor = plasticColorNew;
        manufacturer = manufacturerNew;
        form = formNew;
        date = dateNew;
        blocked = blockedNew;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(final boolean blockedNew) {
        blocked = blockedNew;
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String sizeNew) {
        size = sizeNew;
    }

    public String getForm() {
        return form;
    }

    public void setForm(final String formNew) {
        form = formNew;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date dateNew) {
        date = dateNew;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String modelNew) {
        model = modelNew;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final String manufacturerNew) {
        manufacturer = manufacturerNew;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weightNew) {
        weight = weightNew;
    }

    public String getPlasticColor() {
        return plasticColor;
    }

    public void setPlasticColor(final String plasticColorNew) {
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
                && Objects.equals(getModel(), that.getModel())
                && Objects.equals(getInfo(), that.getInfo())
                && getManufacturer().equals(that.getManufacturer())
                && getPlasticColor().equals(that.getPlasticColor())
                && Objects.equals(getSize(), that.getSize())
                && getForm().equals(that.getForm())
                && Objects.equals(getDate(), that.getDate())
                && isBlocked() == that.isBlocked();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getPrice(), getInfo(),
                getManufacturer(), getWeight(), getPlasticColor(),
                isPrimaryPlastic(), getSize(), getForm(),
                getDate(), isBlocked());
    }

    @Override
    public String toString() {
        return "RubiksCube{" + super.toString()
                + ", model='" + model + '\''
                + ", price=" + price
                + ", info='" + info + '\''
                + ", manufacturer=" + manufacturer
                + ", weight=" + weight
                + ", plasticColor=" + plasticColor
                + ", primaryPlastic=" + primaryPlastic
                + ", size='" + size + '\''
                + ", typeCube=" + form
                + ", localDateTime=" + date
                + ", blocked=" + blocked + '}';
    }
}

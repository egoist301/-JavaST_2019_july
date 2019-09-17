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
    private PlasticColor plasticColor;
    private Manufacturer manufacturer;
    private Form form;
    private Date date;

    public RubiksCube(final long idNew, final String modelNew,
                      final double priceNew,
                      final double weightNew, final String infoNew,
                      final boolean primaryPlasticNew,
                      final String sizeNew,
                      final PlasticColor plasticColorNew,
                      final Manufacturer manufacturerNew,
                      final Form formNew, final Date dateNew) {
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
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String sizeNew) {
        size = sizeNew;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(final Form formNew) {
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
                && Objects.equals(getModel(), that.getModel())
                && Objects.equals(getInfo(), that.getInfo())
                && getManufacturer() == that.getManufacturer()
                && getPlasticColor() == that.getPlasticColor()
                && Objects.equals(getSize(), that.getSize())
                && getForm() == that.getForm() &&
                Objects
                        .equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getPrice(), getInfo(),
                getManufacturer(), getWeight(), getPlasticColor(),
                isPrimaryPlastic(), getSize(), getForm(),
                getDate());
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
                + ", localDateTime=" + date + '}';
    }
}

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
    private int plasticColorId;
    private int manufacturerId;
    private int formId;
    private Date date;

    public RubiksCube(final long idNew, final String modelNew,
                      final double priceNew,
                      final double weightNew, final String infoNew,
                      final boolean primaryPlasticNew,
                      final String sizeNew,
                      final int plasticColorIdNew,
                      final int manufacturerNew,
                      final int formIdNew, final Date dateNew,
                      final boolean blockedNew) {
        super(idNew, blockedNew);
        model = modelNew;
        price = priceNew;
        weight = weightNew;
        info = infoNew;
        primaryPlastic = primaryPlasticNew;
        size = sizeNew;
        plasticColorId = plasticColorIdNew;
        manufacturerId = manufacturerNew;
        formId = formIdNew;
        date = dateNew;
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String sizeNew) {
        size = sizeNew;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(final int formIdNew) {
        formId = formIdNew;
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

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(final int manufacturerIdNew) {
        manufacturerId = manufacturerIdNew;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weightNew) {
        weight = weightNew;
    }

    public int getPlasticColorId() {
        return plasticColorId;
    }

    public void setPlasticColorId(final int plasticColorIdNew) {
        plasticColorId = plasticColorIdNew;
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
                && getManufacturerId() == that.getManufacturerId()
                && getPlasticColorId() == that.getPlasticColorId()
                && Objects.equals(getSize(), that.getSize())
                && getFormId() == that.getFormId()
                && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getPrice(), getInfo(),
                getManufacturerId(), getWeight(), getPlasticColorId(),
                isPrimaryPlastic(), getSize(), getFormId(),
                getDate());
    }

    @Override
    public String toString() {
        return "RubiksCube{" + super.toString()
                + ", model='" + model + '\''
                + ", price=" + price
                + ", info='" + info + '\''
                + ", manufacturer=" + manufacturerId
                + ", weight=" + weight
                + ", plasticColor=" + plasticColorId
                + ", primaryPlastic=" + primaryPlastic
                + ", size='" + size + '\''
                + ", typeCube=" + formId
                + ", localDateTime=" + date + '}';
    }
}

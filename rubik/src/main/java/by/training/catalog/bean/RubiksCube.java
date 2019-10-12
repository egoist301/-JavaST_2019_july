package by.training.catalog.bean;

import java.util.Date;
import java.util.Objects;

/**
 * Cube of Rubik's.
 */
public class RubiksCube extends Entity {
    /**
     * Model of cube.
     */
    private String model;
    /**
     * Price of cube.
     */
    private double price;
    /**
     * Weight of cube.
     */
    private double weight;
    /**
     * Info about cube.
     */
    private String info;
    /**
     * Primary plastic. Contains or not.
     */
    private boolean primaryPlastic;
    /**
     * Size of cube. Example: 3x3 or 3x3x3.
     */
    private String size;
    /**
     * Plastic color of cube.
     */
    private String plasticColor;
    /**
     * Manufacturer of cube.
     */
    private String manufacturer;
    /**
     * Form of cube(cube or cuboid or any).
     */
    private String form;
    /**
     * Date add cube on site.
     */
    private Date date;
    /**
     * Blocked or not.
     */
    private boolean blocked;

    /**
     * Super constructor.
     *
     * @param idNew id of cube.
     */
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


    /**
     * Getter.
     *
     * @return blocked.
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Setter.
     *
     * @param blockedNew blocked.
     */
    public void setBlocked(final boolean blockedNew) {
        blocked = blockedNew;
    }

    /**
     * Getter.
     *
     * @return size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Setter.
     *
     * @param sizeNew size.
     */
    public void setSize(final String sizeNew) {
        size = sizeNew;
    }

    /**
     * Getter.
     *
     * @return form.
     */
    public String getForm() {
        return form;
    }

    /**
     * Setter.
     *
     * @param formNew form.
     */
    public void setForm(final String formNew) {
        form = formNew;
    }

    /**
     * Getter.
     *
     * @return date added.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter.
     *
     * @param dateNew date added.
     */
    public void setDate(final Date dateNew) {
        date = dateNew;
    }

    /**
     * Getter.
     *
     * @return model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter.
     *
     * @param modelNew model.
     */
    public void setModel(final String modelNew) {
        model = modelNew;
    }

    /**
     * Getter.
     *
     * @return price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter.
     *
     * @param priceNew price.
     */
    public void setPrice(final double priceNew) {
        price = priceNew;
    }

    /**
     * Getter.
     *
     * @return info.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Setter.
     *
     * @param infoNew info.
     */
    public void setInfo(final String infoNew) {
        info = infoNew;
    }

    /**
     * Getter.
     *
     * @return manufacturer.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Setter.
     *
     * @param manufacturerNew manufacturer.
     */
    public void setManufacturer(final String manufacturerNew) {
        manufacturer = manufacturerNew;
    }

    /**
     * Getter.
     *
     * @return weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setter.
     *
     * @param weightNew weight.
     */
    public void setWeight(final double weightNew) {
        weight = weightNew;
    }

    /**
     * Getter.
     *
     * @return plastic color.
     */
    public String getPlasticColor() {
        return plasticColor;
    }

    /**
     * Setter.
     *
     * @param plasticColorNew plastic color.
     */
    public void setPlasticColor(final String plasticColorNew) {
        plasticColor = plasticColorNew;
    }

    /**
     * Getter.
     *
     * @return primary plastic.
     */
    public boolean isPrimaryPlastic() {
        return primaryPlastic;
    }

    /**
     * Setter.
     *
     * @param primaryPlasticNew primary plastic.
     */
    public void setPrimaryPlastic(final boolean primaryPlasticNew) {
        primaryPlastic = primaryPlasticNew;
    }

    /**
     * Equal cube with any object.
     *
     * @param oNew any object.
     * @return equal or not.
     */
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

    /**
     * Hash code.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getPrice(), getInfo(),
                getManufacturer(), getWeight(), getPlasticColor(),
                isPrimaryPlastic(), getSize(), getForm(),
                getDate(), isBlocked());
    }

    /**
     * Cube to string.
     *
     * @return cube.
     */
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

package by.training.catalog.bean;

import java.util.Objects;

/**
 * Store which retrieves image of cube by it's id.
 */
public class StoreImage extends Entity {
    private RubiksCube rubiksCube;
    private String imgPath;

    /**
     * Super constructor.
     *
     * @param idNew new id for image.
     */
    public StoreImage(final long idNew) {
        super(idNew);
    }

    /**
     * Primary constructor.
     *
     * @param newId         new id of cube's image.
     * @param newRubiksCube cube which is tight to image.
     * @param newImgPath    path of image.
     */
    public StoreImage(final long newId,
                      final RubiksCube newRubiksCube, final String newImgPath) {
        super(newId);
        rubiksCube = newRubiksCube;
        imgPath = newImgPath;
    }

    /**
     * Cube getter.
     *
     * @return rubiksCube.
     */
    public RubiksCube getRubiksCube() {
        return rubiksCube;
    }

    /**
     * Cube setter.
     *
     * @param rubiksCubeNew rubiksCube.
     */
    public void setRubiksCube(final RubiksCube rubiksCubeNew) {
        rubiksCube = rubiksCubeNew;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(final String imgPathNew) {
        imgPath = imgPathNew;
    }

    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof StoreImage)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        StoreImage that = (StoreImage) oNew;
        return Objects.equals(getRubiksCube(), that.getRubiksCube())
                && Objects.equals(getImgPath(), that.getImgPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRubiksCube(), getImgPath());
    }

    @Override
    public String toString() {
        return "StoreImage{" + super.toString()
                + ", rubiksCube=" + rubiksCube
                + ", imgPath='" + imgPath + '\'' + '}';
    }
}

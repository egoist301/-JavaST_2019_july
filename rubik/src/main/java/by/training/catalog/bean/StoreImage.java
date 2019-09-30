package by.training.catalog.bean;

import java.util.Objects;

public class StoreImage extends Entity {
    private RubiksCube rubiksCube;
    private String imgPath;

    public StoreImage(final long idNew) {
        super(idNew);
    }

    public StoreImage(final long idNew,
                      final RubiksCube rubiksCubeNew, final String imgPathNew) {
        super(idNew);
        rubiksCube = rubiksCubeNew;
        imgPath = imgPathNew;
    }

    public RubiksCube getRubiksCube() {
        return rubiksCube;
    }

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

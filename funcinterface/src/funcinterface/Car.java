package funcinterface;

public class Car {
    private String name;
    private boolean isFullDrive;
    private boolean isGasEngine;

    public Car(String nameNew, boolean isFullDriveNew, boolean isGasEngineNew) {
        name = nameNew;
        isFullDrive = isFullDriveNew;
        isGasEngine = isGasEngineNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameNew) {
        name = nameNew;
    }

    public boolean isFullDrive() {
        return isFullDrive;
    }

    public void setFullDrive(boolean fullDriveNew) {
        isFullDrive = fullDriveNew;
    }

    public boolean isGasEngine() {
        return isGasEngine;
    }

    public void setGasEngine(boolean gasEngineNew) {
        isGasEngine = gasEngineNew;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", isFullDrive=" + isFullDrive +
                ", isGasEngine=" + isGasEngine +
                '}';
    }
}

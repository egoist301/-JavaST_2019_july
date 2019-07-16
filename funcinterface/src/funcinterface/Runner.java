package funcinterface;

public class Runner {
    public static void main(String[] args) {
        Car car1 = new Car("AudiA3", true,true);
        Car car2 = new Car("AudiA6", true,false);
        CheckCar checker = new CheckCar() {
            @Override
            public boolean test(Car carNew) {
                return carNew.isFullDrive();
            }
        };
        printTest(car1, checker);
        printTest(car2, checker);

        printTest(car1, carNew -> carNew.isGasEngine());
        printTest(car2, Car::isGasEngine);
    }
    private static void printTest(Car carNew, CheckCar checkCarNew){
        if (checkCarNew.test(carNew)){
            System.out.println(carNew);
        }
    }
}

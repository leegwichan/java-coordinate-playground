package fuel.domain.car;

public abstract class Car {

    private static final String REPORT_FORMAT = "%s : %f리터";

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    public final String generateReport() {
        return String.format(REPORT_FORMAT, getName(), getChargeQuantity());
    }

    private double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

package fuel.domain.car;

import java.text.DecimalFormat;

public abstract class Car {

    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("########.###");
    private static final String REPORT_FORMAT = "%s : %s리터%n";

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    public final String generateReport() {
        return String.format(REPORT_FORMAT, getName(), getChargeQuantity());
    }

    private String getChargeQuantity() {
        double chargeQuantity = getTripDistance() / getDistancePerLiter();
        return DOUBLE_FORMAT.format(chargeQuantity);
    }
}

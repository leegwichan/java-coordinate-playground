package coordinatecalculator;

import coordinatecalculator.controller.CoordinateCalculatorController;

public class CoordinateCalculatorApplication {

    private static final CoordinateCalculatorController controller
            = CoordinateCalculatorController.createConsoleController();

    private CoordinateCalculatorApplication() {
    }

    public static void main(String[] args) {
        controller.run();
    }
}

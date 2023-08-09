package coordinatecalculator.view;

import java.util.Objects;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;

public class OutputView {

    private final Printer printer;

    private OutputView(Printer printer) {
        this.printer = Objects.requireNonNull(printer);
    }

    public static OutputView of(Printer printer) {
        return new OutputView(printer);
    }

    public static OutputView createConsoleOutputView() {
        return of(new ConsolePrinter());
    }
}

package coordinatecalculator.view;

import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;
import coordinatecalculator.view.reader.ConsoleReader;
import coordinatecalculator.view.reader.Reader;
import java.util.Objects;

public final class InputView {

    private final Reader reader;
    private final Printer printer;

    private InputView(Reader reader, Printer printer) {
        this.reader = Objects.requireNonNull(reader);
        this.printer = Objects.requireNonNull(printer);
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer);
    }

    public static InputView createConsoleInputView() {
        return of(new ConsoleReader(), new ConsolePrinter());
    }
}
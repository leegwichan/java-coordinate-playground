package coordinatecalculator.view;

import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;
import coordinatecalculator.view.reader.ConsoleReader;
import coordinatecalculator.view.reader.Reader;
import java.util.Objects;

public final class InputView {

    private static final String POINTS_REQUEST_MESSAGE = "좌표를 입력하세요.\n";

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

    public PointsDto inputPoints() {
        printer.print(POINTS_REQUEST_MESSAGE);
        String message = reader.read();
        if (message.isBlank()) {
            throw new IllegalArgumentException("좌표 형식이 일치하지 않습니다");
        }
        return null;
    }
}
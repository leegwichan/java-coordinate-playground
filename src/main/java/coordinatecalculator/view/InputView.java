package coordinatecalculator.view;

import static java.util.stream.Collectors.toList;

import coordinatecalculator.dto.PointDto;
import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;
import coordinatecalculator.view.reader.ConsoleReader;
import coordinatecalculator.view.reader.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputView {

    private static final String POINTS_REQUEST_MESSAGE = "좌표를 입력하세요.\n";
    private static final String POINTS_DELIMITER = "-";
    private static final Pattern POINT_PATTERN = Pattern.compile("\\((.*),(.*)\\)");

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

        List<PointDto> points = Arrays.stream(reader.read().split(POINTS_DELIMITER))
                .map(this::toDto).collect(toList());
        return PointsDto.of(points);
    }

    private PointDto toDto(String message) {
        Matcher matcher = POINT_PATTERN.matcher(message);
        if (!matcher.find()) {
            throw new IllegalArgumentException("좌표 입력 형식이 일치하지 않습니다");
        }
        return toDto(matcher.group(1), matcher.group(2));
    }

    private PointDto toDto(String x, String y) {
        try {
            int xValue = Integer.parseInt(x);
            int yValue = Integer.parseInt(y);
            return PointDto.of(xValue, yValue);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("좌표 입력 형식이 일치하지 않습니다", exception);
        }
    }
}
package coordinatecalculator.view;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import coordinatecalculator.dto.PointDto;
import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;

public final class OutputView {

    private static final int MAX_COORDINATE_RANGE = 24;
    private static final int MIN_COORDINATE_RANGE = 1;

    private static final String ODD_PREFIX = "  |";
    private static final String EVEN_PREFIX_FORMAT = "%2d|";
    private static final String BLANK = "  ";
    private static final String DOT = " •";
    private static final String LINE_SEPARATOR = "\n";
    private static final String X_AXIS
            = "  +ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" + LINE_SEPARATOR
            + " 0    2   4   6   8   10  12  14  16  18  20  22  24" + LINE_SEPARATOR;

    private static final String LENGTH_FORMAT = "두 점 사이의 거리는 %f";
    private static final String ERROR_PREFIX = "[ERROR] ";
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

    public void printCoordinate(PointsDto points) {
        validate(points);
        String result = IntStream.rangeClosed(MIN_COORDINATE_RANGE, MAX_COORDINATE_RANGE)
                .map(yValue -> MIN_COORDINATE_RANGE + MAX_COORDINATE_RANGE - yValue)
                .mapToObj(yValue -> getCoordinateLine(yValue, points))
                .collect(joining()).concat(X_AXIS);
        printer.print(result);
    }

    public void printLength(double length) {
        String result = String.format(LENGTH_FORMAT, length);
        printer.print(result);
    }

    public void print(Exception e) {
        String message = ERROR_PREFIX.concat(e.getMessage());
        printer.print(message);
    }

    private void validate(PointsDto points) {
        for (PointDto point : points.getPoints()) {
            validate(point);
        }
    }

    private void validate(PointDto point) {
        int xValue = point.getX();
        int yValue = point.getY();

        if (xValue < MIN_COORDINATE_RANGE || xValue > MAX_COORDINATE_RANGE
                || yValue < MIN_COORDINATE_RANGE || yValue > MAX_COORDINATE_RANGE) {
            throw new IllegalArgumentException("출력할 수 있는 좌표 범위를 벗어났습니다");
        }
    }

    private String getCoordinateLine(int line, PointsDto points) {
        List<Integer> xValues = points.getPoints().stream()
                .filter(pointDto -> pointDto.getY() == line)
                .map(PointDto::getX).collect(toList());

        String coordinateResult = IntStream.rangeClosed(MIN_COORDINATE_RANGE, MAX_COORDINATE_RANGE)
                .mapToObj(xValue -> getCoordinateResult(xValues.contains(xValue)))
                .collect(joining());
        return getCoordinateLinePrefix(line).concat(coordinateResult).concat(LINE_SEPARATOR);
    }

    private String getCoordinateLinePrefix(int line) {
        if (line % 2 == 1) {
            return ODD_PREFIX;
        }
        return String.format(EVEN_PREFIX_FORMAT, line);
    }

    private String getCoordinateResult(boolean isExist) {
        if (isExist) {
            return DOT;
        }
        return BLANK;
    }
}

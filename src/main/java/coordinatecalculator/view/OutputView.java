package coordinatecalculator.view;

import java.util.Objects;
import coordinatecalculator.dto.PointDto;
import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;

public final class OutputView {

    private static final int MAX_COORDINATE_RANGE = 24;
    private static final int MIN_COORDINATE_RANGE = 1;

    private static final String BLANK = "  ";
    private static final String POINT = " •";
    private static final String LINE_SEPARATOR = "\n";

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
        validatePoints(points);
    }

    private void validatePoints(PointsDto points) {
        for (PointDto point : points.getPoints()) {
            validatePoint(point);
        }
    }

    private void validatePoint(PointDto point) {
        int xValue = point.getX();
        int yValue = point.getY();

        if (xValue < MIN_COORDINATE_RANGE || xValue > MAX_COORDINATE_RANGE
                || yValue < MIN_COORDINATE_RANGE || yValue > MAX_COORDINATE_RANGE) {
            throw new IllegalArgumentException("출력할 수 있는 좌표 범위를 벗어났습니다");
        }
    }
}

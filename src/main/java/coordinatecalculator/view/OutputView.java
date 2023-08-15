package coordinatecalculator.view;

import java.util.Objects;
import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;

public final class OutputView {

    private static final String LINE_SEPARATOR = "\n";
    private static final String LENGTH_FORMAT = "두 점 사이의 거리는 %f";
    private static final String RECTANGLE_AREA_FORMAT = "사각형 넓이는 %f";
    private static final String ERROR_FORMAT = "[ERROR] %s".concat(LINE_SEPARATOR);

    private final Printer printer;
    private final CoordinateView coordinateView = new CoordinateView();

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
        String result = coordinateView.getCoordinateView(points);
        printer.print(result);
    }

    public void printLength(double length) {
        String result = String.format(LENGTH_FORMAT, length);
        printer.print(result);
    }

    public void printSquareArea(double area) {
        String result = String.format(RECTANGLE_AREA_FORMAT, area);
        printer.print(result);
    }

    public void print(Exception exception) {
        String message = String.format(ERROR_FORMAT, exception.getMessage());
        printer.print(message);
    }
}

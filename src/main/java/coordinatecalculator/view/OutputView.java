package coordinatecalculator.view;

import java.util.Map;
import java.util.Objects;
import coordinatecalculator.dto.ShapeDto;
import coordinatecalculator.dto.ShapeResultDto;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;

public final class OutputView {

    private static final String LINE_SEPARATOR = "\n";

    private static final Map<ShapeDto, String> AREA_FORMAT
             = Map.of(ShapeDto.LINE, "두 점 사이의 거리는 %f",
                     ShapeDto.SQUARE, "사각형 넓이는 %f", ShapeDto.TRIANGLE, "삼각형의 넓이는 %f");
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

    public void print(ShapeResultDto resultDto) {
        String coordinate = coordinateView.getCoordinateView(resultDto.getPoints());
        String areaMessage = String.format(AREA_FORMAT.get(resultDto.getShape()), resultDto.getArea());

        printer.print(coordinate.concat(areaMessage));
    }

    public void print(Exception exception) {
        String message = String.format(ERROR_FORMAT, exception.getMessage());
        printer.print(message);
    }
}

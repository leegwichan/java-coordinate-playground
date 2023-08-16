package coordinatecalculator.view;

import java.util.Map;
import java.util.Objects;
import coordinatecalculator.dto.ShapeResultDto;
import coordinatecalculator.entity.Line;
import coordinatecalculator.entity.PlaneShape;
import coordinatecalculator.entity.Rectangle;
import coordinatecalculator.entity.Triangle;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;

public final class OutputView {

    private static final String LINE_SEPARATOR = "\n";

    private static final Map<Class<? extends PlaneShape>, String> AREA_FORMAT
             = Map.of(Line.class, "두 점 사이의 거리는 %f",
                     Rectangle.class, "사각형 넓이는 %f", Triangle.class, "삼각형의 넓이는 %f");
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

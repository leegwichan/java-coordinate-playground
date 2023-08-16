package coordinatecalculator.controller;

import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.dto.ShapeDto;
import coordinatecalculator.dto.ShapeResultDto;
import coordinatecalculator.entity.PlaneShape;
import coordinatecalculator.entity.Point;
import coordinatecalculator.view.InputView;
import coordinatecalculator.view.OutputView;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CoordinateCalculatorController extends ControllerHelper {

    private final InputView inputView;
    private final OutputView outputView;

    private CoordinateCalculatorController(InputView inputView, OutputView outputView) {
        super(outputView);
        this.inputView = Objects.requireNonNull(inputView);
        this.outputView = Objects.requireNonNull(outputView);
    }

    public static CoordinateCalculatorController createConsoleController() {
        return new CoordinateCalculatorController(
                InputView.createConsoleInputView(), OutputView.createConsoleOutputView());
    }

    public void run() {
        repeatWhenThrowException(this::runMainProgram);
    }

    private void runMainProgram() {
        PointsDto pointsDto = inputView.inputPoints();
        PlaneShape shape = PlaneShape.of(toPoints(pointsDto));

        ShapeResultDto dto = ShapeResultDto.builder()
                .points(pointsDto)
                .shape(ShapeDto.toDto(shape))
                .area(shape.calculateArea()).build();
        outputView.print(dto);
    }

    private List<Point> toPoints(PointsDto pointsDto) {
        return pointsDto.getPoints().stream()
                .map(pointDto -> Point.of(pointDto.getX(), pointDto.getY()))
                .collect(Collectors.toList());
    }
}

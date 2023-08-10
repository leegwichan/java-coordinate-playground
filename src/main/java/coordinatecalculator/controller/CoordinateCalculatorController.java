package coordinatecalculator.controller;

import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.entity.Point;
import coordinatecalculator.view.InputView;
import coordinatecalculator.view.OutputView;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CoordinateCalculatorController {

    private final InputView inputView;
    private final OutputView outputView;

    private CoordinateCalculatorController(InputView inputView, OutputView outputView) {
        this.inputView = Objects.requireNonNull(inputView);
        this.outputView = Objects.requireNonNull(outputView);
    }

    public static CoordinateCalculatorController createConsoleController() {
        return new CoordinateCalculatorController(
                InputView.createConsoleInputView(), OutputView.createConsoleOutputView());
    }

    public void run() {
        PointsDto pointsDto = inputView.inputPoints();
        if (pointsDto.size() != 2) {
            throw new IllegalArgumentException("점은 2개만 입력하여야 합니다");
        }

        List<Point> points = pointsDto.getPoints().stream()
                .map(pointDto -> Point.of(pointDto.getX(), pointDto.getY()))
                .collect(Collectors.toList());
        double length = points.get(0).calculateDistance(points.get(1));

        outputView.printCoordinate(pointsDto);
        outputView.printLength(length);
    }
}

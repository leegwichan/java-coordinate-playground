package coordinatecalculator.entity;

import java.util.List;
import java.util.Objects;

public final class Triangle implements PlaneShape {

    private List<Point> points;

    private Triangle(List<Point> points) {
        this.points = List.copyOf(Objects.requireNonNull(points));
    }

    public static Triangle of(List<Point> points) {
        validate(points);
        return new Triangle(points);
    }

    private static void validate(List<Point> points) {
        if (points.size() != 3) {
            throw new IllegalArgumentException("점의 개수는 3개이어야 합니다");
        }
        if (points.stream().distinct().count() != 3L) {
            throw new IllegalArgumentException("점들이 서로 중복되어서는 안됩니다");
        }
    }

    @Override
    public double calculateArea() {
        return 0;
    }
}

package coordinatecalculator.entity.planeshape;

import coordinatecalculator.entity.Point;
import java.util.List;

public final class Line extends PlaneShape {

    private final List<Point> points;

    private Line(List<Point> points) {
        this.points = List.copyOf(points);
    }

    public static Line of(List<Point> points) {
        if (points.size() != 2 || points.get(0).equals(points.get(1))) {
            throw new IllegalArgumentException("입력한 점들은 서로 다른 두 점이어야 합니다");
        }

        return new Line(points);
    }

    @Override
    public double calculateArea() {
        Point point1 = points.get(0);
        Point point2 = points.get(1);
        return point1.calculateDistance(point2);
    }
}

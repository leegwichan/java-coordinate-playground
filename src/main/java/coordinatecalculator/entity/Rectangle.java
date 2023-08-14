package coordinatecalculator.entity;

import java.util.List;
import java.util.Objects;

public final class Rectangle implements PlaneShape {

    private final Point leftBottom;
    private final Point rightTop;

    private Rectangle(Point leftBottom, Point rightTop) {
        this.leftBottom = Objects.requireNonNull(leftBottom);
        this.rightTop = Objects.requireNonNull(rightTop);
    }

    public static Rectangle of(List<Point> points) {
        List<Point> copyPoints = List.copyOf(points);
        validate(copyPoints);

        return new Rectangle(getLeftBottom(copyPoints), getRightTop(copyPoints));
    }

    private static void validate(List<Point> points) {
        if (points.size() != 4) {
            throw new IllegalArgumentException("입력한 점의 개수가 4개이어야 합니다");
        }
        if (countNonOverlappedPoint(points) != 4L) {
            throw new IllegalArgumentException("점은 중복되지 않아야 합니다");
        }
        if (!isRectangle(points)) {
            throw new IllegalArgumentException("점들이 직사각형 꼴이어야 합니다");
        }
    }

    private static long countNonOverlappedPoint(List<Point> points) {
        return points.stream().distinct().count();
    }

    private static boolean isRectangle(List<Point> points) {
        Point defaultPoint = points.get(0);
        return points.stream().mapToInt(point -> point.calculateXDifference(defaultPoint)).distinct().count() == 2
                && points.stream().mapToInt(point -> point.calculateYDifference(defaultPoint)).distinct().count() == 2;
    }

    private static Point getLeftBottom(List<Point> points) {
        Point leftBottom = points.get(0);
        for (Point point : points) {
            if (point.calculateXDifference(leftBottom) <= 0
                    && point.calculateYDifference(leftBottom) <= 0) {
                leftBottom = point;
            }
        }
        return leftBottom;
    }

    private static Point getRightTop(List<Point> points) {
        Point leftBottom = points.get(0);
        for (Point point : points) {
            if (point.calculateXDifference(leftBottom) >= 0
                    && point.calculateYDifference(leftBottom) >= 0) {
                leftBottom = point;
            }
        }
        return leftBottom;
    }

    @Override
    public double calculateArea() {
        return 0;
    }
}

package coordinatecalculator.entity;

import java.util.List;
import java.util.Objects;

public final class Triangle extends PlaneShape {

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
        double length1 = points.get(0).calculateDistance(points.get(1));
        double length2 = points.get(1).calculateDistance(points.get(2));
        double length3 = points.get(2).calculateDistance(points.get(0));
        return calculateArea(length1, length2, length3);
    }

    private double calculateArea(double length1, double length2, double length3) {
        double a = length1 + length2 + length3;
        double b = - length1 + length2 + length3;
        double c = length1 - length2 + length3;
        double d = length1 + length2 - length3;

        return Math.sqrt(a * b * c * d) / 4; // 헤론의 공식을 이용
    }
}

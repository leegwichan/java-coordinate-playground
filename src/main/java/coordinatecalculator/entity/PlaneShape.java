package coordinatecalculator.entity;

import java.util.List;

public interface PlaneShape {

    static PlaneShape of(List<Point> points) {
        if (points.size() == 3) {
            return Triangle.of(points);
        }
        if (points.size() == 4) {
            return Rectangle.of(points);
        }
        throw new IllegalArgumentException("해당 도형이 존재하지 않습니다");
    }

    double calculateArea();
}

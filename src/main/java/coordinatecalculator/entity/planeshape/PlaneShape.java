package coordinatecalculator.entity.planeshape;

import coordinatecalculator.entity.Point;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class PlaneShape {

    private static final Map<Integer, PlaneShapeCreator> CREATOR_BY_POINT_SIZE
            = Map.of(2, Line::of, 3, Triangle::of, 4, Rectangle::of);

    public static PlaneShape of(List<Point> points) {
        Objects.requireNonNull(points);
        PlaneShapeCreator creator = Optional.ofNullable(CREATOR_BY_POINT_SIZE.get(points.size()))
                .orElseThrow(() -> new IllegalArgumentException("해당 도형이 존재하지 않습니다"));
        return creator.create(points);
    }

    public abstract double calculateArea();

    @FunctionalInterface
    private interface PlaneShapeCreator {
        PlaneShape create(List<Point> points);
    }
}

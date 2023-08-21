package coordinatecalculator.entity.factory;

import coordinatecalculator.entity.Point;
import coordinatecalculator.entity.planeshape.Line;
import coordinatecalculator.entity.planeshape.PlaneShape;
import coordinatecalculator.entity.planeshape.Rectangle;
import coordinatecalculator.entity.planeshape.Triangle;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PlaneShapeFactory {

    private static final Map<Integer, PlaneShapeCreator> CREATOR_BY_POINT_SIZE
            = Map.of(2, Line::of, 3, Triangle::of, 4, Rectangle::of);

    private PlaneShapeFactory() {
    }

    public static PlaneShape create(List<Point> points) {
        Objects.requireNonNull(points);
        PlaneShapeCreator creator = Optional.ofNullable(CREATOR_BY_POINT_SIZE.get(points.size()))
                .orElseThrow(() -> new IllegalArgumentException("해당 도형이 존재하지 않습니다"));
        return creator.create(points);
    }

    @FunctionalInterface
    private interface PlaneShapeCreator {
        PlaneShape create(List<Point> points);
    }
}

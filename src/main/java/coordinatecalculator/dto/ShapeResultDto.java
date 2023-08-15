package coordinatecalculator.dto;

import coordinatecalculator.entity.PlaneShape;
import java.util.Objects;

public final class ShapeResultDto {

    private final PointsDto points;
    private final double area;
    private final Class<? extends PlaneShape> shape;

    private ShapeResultDto(PointsDto points, double area, Class<? extends PlaneShape> shape) {
        if (area < 0) {
            throw new IllegalArgumentException();
        }

        this.points = Objects.requireNonNull(points);
        this.area = area;
        this.shape = Objects.requireNonNull(shape);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PointsDto getPoints() {
        return points;
    }

    public double getArea() {
        return area;
    }

    public Class<? extends PlaneShape> getShape() {
        return shape;
    }

    public static class Builder {
        private PointsDto points;
        private double area;
        private Class<? extends PlaneShape> shape;

        public Builder points(PointsDto points) {
            this.points = points;
            return this;
        }

        public Builder area(double area) {
            this.area = area;
            return this;
        }

        public Builder shape(Class<? extends PlaneShape> shape) {
            this.shape = shape;
            return this;
        }

        public ShapeResultDto build() {
            return new ShapeResultDto(points, area, shape);
        }
    }
}

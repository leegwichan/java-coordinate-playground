package coordinatecalculator.dto;

import java.util.Objects;

public final class ShapeResultDto {

    private final PointsDto points;
    private final double area;
    private final ShapeDto shape;

    private ShapeResultDto(PointsDto points, double area, ShapeDto shape) {
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

    public ShapeDto getShape() {
        return shape;
    }

    public static class Builder {
        private PointsDto points;
        private double area;
        private ShapeDto shape;

        public Builder points(PointsDto points) {
            this.points = points;
            return this;
        }

        public Builder area(double area) {
            this.area = area;
            return this;
        }

        public Builder shape(ShapeDto shape) {
            this.shape = shape;
            return this;
        }

        public ShapeResultDto build() {
            return new ShapeResultDto(points, area, shape);
        }
    }
}

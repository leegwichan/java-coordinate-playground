package coordinatecalculator.dto;

import java.util.List;

public final class PointsDto {

    private final List<PointDto> points;

    private PointsDto(List<PointDto> points) {
        this.points = List.copyOf(points);
    }

    private PointsDto(PointDto[] points) {
        this.points = List.of(points);
    }

    public static PointsDto of(List<PointDto> points) {
        return new PointsDto(points);
    }

    public static PointsDto of(PointDto... points) {
        return new PointsDto(points);
    }

    public List<PointDto> getPoints() {
        return points;
    }

    public int size() {
        return points.size();
    }
}

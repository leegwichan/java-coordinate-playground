package coordinatecalculator.dto;

import coordinatecalculator.entity.Line;
import coordinatecalculator.entity.PlaneShape;
import coordinatecalculator.entity.Rectangle;
import coordinatecalculator.entity.Triangle;
import java.util.Map;
import java.util.Optional;

public enum ShapeDto {

    LINE, SQUARE, TRIANGLE;

    private static final Map<Class<? extends PlaneShape>, ShapeDto> SHAPE_BY_CLASS
            = Map.of(Line.class, LINE, Rectangle.class, SQUARE, Triangle.class, TRIANGLE);

    public static ShapeDto toDto(PlaneShape planeShape) {
        return Optional.ofNullable(SHAPE_BY_CLASS.get(planeShape.getClass()))
                .orElseThrow(() -> new IllegalArgumentException("해당 모양이 지정되지 않았습니다"));
    }
}

package coordinatecalculator.dto;

import coordinatecalculator.entity.planeshape.Line;
import coordinatecalculator.entity.planeshape.PlaneShape;
import coordinatecalculator.entity.planeshape.Rectangle;
import coordinatecalculator.entity.planeshape.Triangle;
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

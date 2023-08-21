package coordinatecalculator.entity.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import coordinatecalculator.entity.Point;
import coordinatecalculator.entity.planeshape.Line;
import coordinatecalculator.entity.planeshape.PlaneShape;
import coordinatecalculator.entity.planeshape.Rectangle;
import coordinatecalculator.entity.planeshape.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class PlaneShapeFactoryTest {

    @DisplayName("점을 1개 입력하면 예외를 던진다")
    @Test
    void createTest_whenPointSizeIs1_throwException() {
        List<Point> points = List.of(Point.of(1, 1));

        assertThatThrownBy(() -> PlaneShapeFactory.create(points))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 도형이 존재하지 않습니다");
    }

    @DisplayName("점을 5개 입력하면 예외를 던진다")
    @Test
    void createTest_whenPointSizeIs5_throwException() {
        List<Point> points = List.of(Point.of(1, 1), Point.of(2, 3),
                Point.of(4, 5), Point.of(10, 11), Point.of(15, 1));

        assertThatThrownBy(() -> PlaneShapeFactory.create(points))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 도형이 존재하지 않습니다");
    }

    @DisplayName("점을 2개 입력하면 직선을 생성한다")
    @Test
    void createTest_whenPointSizeIs2_createLine() {
        List<Point> points = List.of(Point.of(1, 1), Point.of(2, 3));

        PlaneShape shape = PlaneShapeFactory.create(points);

        assertThat(shape).isInstanceOf(Line.class);
    }

    @DisplayName("점을 3개 입력하면 삼각형을 생성한다")
    @Test
    void createTest_whenPointSizeIs3_createTriangle() {
        List<Point> points = List.of(Point.of(1, 1), Point.of(2, 3), Point.of(2, 10));

        PlaneShape shape = PlaneShapeFactory.create(points);

        assertThat(shape).isInstanceOf(Triangle.class);
    }

    @DisplayName("점을 4개 입력하면 직사각형을 생성한다")
    @Test
    void createTest_whenPointSizeIs4_createSquare() {
        List<Point> points = List.of(Point.of(1, 1), Point.of(10, 10),
                Point.of(10, 1), Point.of(1, 10));

        PlaneShape shape = PlaneShapeFactory.create(points);

        assertThat(shape).isInstanceOf(Rectangle.class);
    }
}
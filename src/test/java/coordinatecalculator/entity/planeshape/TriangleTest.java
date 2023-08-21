package coordinatecalculator.entity.planeshape;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.offset;

import coordinatecalculator.entity.Point;
import coordinatecalculator.entity.planeshape.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;

class TriangleTest {

    @DisplayName("객체를 생성할 수 있다")
    @Nested
    class CreationTest {

        @DisplayName("점의 개수가 2개인 경우 예외를 던진다")
        @Test
        void createTest_whenPointsSizeIs2_throwException() {
            List<Point> points = List.of(Point.of(1, 2), Point.of(3, 4));

            assertThatThrownBy(() -> Triangle.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("점의 개수는 3개이어야 합니다");
        }

        @DisplayName("점의 개수가 4개인 경우 예외를 던진다")
        @Test
        void createTest_whenPointsSizeIs4_throwException() {
            List<Point> points = List.of(Point.of(1, 2), Point.of(3, 4),
                    Point.of(5, 6), Point.of(7, 8));

            assertThatThrownBy(() -> Triangle.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("점의 개수는 3개이어야 합니다");
        }

        @DisplayName("점들이 서로 중복되어서는 안된다")
        @Test
        void createTest_whenPointsOverlapped_throwException() {
            List<Point> points = List.of(Point.of(1, 2), Point.of(3, 4), Point.of(1, 2));

            assertThatThrownBy(() -> Triangle.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("점들이 서로 중복되어서는 안됩니다");
        }

        @DisplayName("중복되지 않는 세 점을 입력할 경우, 정상적으로 객체가 생성된다")
        @Test
        void createTest() {
            List<Point> points = List.of(Point.of(1, 2), Point.of(3, 4), Point.of(5, 6));

            assertThatCode(() -> Triangle.of(points)).doesNotThrowAnyException();
        }
    }

    @DisplayName("삼각형의 넓이를 구할 수 있다")
    @Nested
    class AreaTest {

        @DisplayName("(1,1), (1,21), (7,11)의 넓이는 60이다")
        @Test
        void calculateAreaTest1() {
            List<Point> points = List.of(Point.of(1, 1), Point.of(1, 21), Point.of(7, 11));
            Triangle triangle = Triangle.of(points);
            double expected = 60;

            double actual = triangle.calculateArea();

            assertThat(actual).isEqualTo(expected, offset(0.001));
        }

        @DisplayName("(10,10) (14,15) (20,8)의 넓이는 29이다")
        @Test
        void calculateAreaTest2() {
            List<Point> points = List.of(Point.of(10, 10), Point.of(14, 15), Point.of(20, 8));
            Triangle triangle = Triangle.of(points);
            double expected = 29;

            double actual = triangle.calculateArea();

            assertThat(actual).isEqualTo(expected, offset(0.001));
        }

        @DisplayName("(11,20), (13,20), (20,16)의 넓이는 4이다")
        @Test
        void calculateAreaTest3() {
            List<Point> points = List.of(Point.of(11, 20), Point.of(13, 20), Point.of(20, 16));
            Triangle triangle = Triangle.of(points);
            double expected = 4;

            double actual = triangle.calculateArea();

            assertThat(actual).isEqualTo(expected, offset(0.001));
        }
    }
}
package coordinatecalculator.entity;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
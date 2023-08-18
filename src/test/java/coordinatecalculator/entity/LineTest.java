package coordinatecalculator.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.offset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

class LineTest {

    @DisplayName("객체를 경우에 따라 생성할 수 있다")
    @Nested
    class CreationTest {

        @DisplayName("입력한 점의 개수가 1개일 때, 예외를 던진다")
        @Test
        void createTest_whenPointsSizeIs1_throwException() {
            List<Point> points = List.of(Point.of(1, 2));

            assertThatThrownByPointsError(points);
        }

        @DisplayName("입력한 점의 개수가 3개일 때, 예외를 던진다")
        @Test
        void createTest_whenPointsSizeIs3_throwException() {
            List<Point> points = List.of(Point.of(1, 2), Point.of(5, 2), Point.of(10, 6));

            assertThatThrownByPointsError(points);
        }

        @DisplayName("입력한 점 2개가 서로 곂칠 떼, 예외를 던진다")
        @Test
        void createTest_whenPointsOverlapped_throwException() {
            List<Point> points = List.of(Point.of(1, 2), Point.of(1, 2));

            assertThatThrownByPointsError(points);
        }

        void assertThatThrownByPointsError(List<Point> points) {
            assertThatThrownBy(() -> Line.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력한 점들은 서로 다른 두 점이어야 합니다");
        }

        @DisplayName("입력한 점이 2개이고 서로 안곂칠 때, 정상적으로 객체를 생성한다")
        @Test
        void createTest() {
            List<Point> points = List.of(Point.of(1, 2), Point.of(10, 20));

            assertThatCode(() -> Line.of(points)).doesNotThrowAnyException();
        }
    }

    @DisplayName("두 점사이의 거리를 측정할 수 있다")
    @ParameterizedTest(name = "(1, 1)과 ({0}, {1}) 사이의 거리는 {2}이다")
    @CsvSource({"1,11,10", "11,1,10", "2,2,1.414", "11,6,11.18033"})
    void calculateAreaTest(int x2, int y2, double expected) {
        Point point1 = Point.of(1, 1);
        Point point2 = Point.of(x2, y2);
        Line line = Line.of(List.of(point1, point2));

        double actual = line.calculateArea();

        assertThat(actual).isEqualTo(expected, offset(0.001));
    }
}
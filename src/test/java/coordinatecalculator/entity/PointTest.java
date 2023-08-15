package coordinatecalculator.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PointTest {

    @DisplayName("두 점 사이의 거리를 계산할 수 있다")
    @Nested
    class DistanceTest {

        @DisplayName("두 점 사이의 거리를 계산할 수 있다")
        @ParameterizedTest(name = "(1,1)과 ({0},{1}) 사이의 거리는 {2}이다")
        @CsvSource({"4,5,5", "6,13,13", "1,11,10", "1,1,0"})
        void calculateDistanceTest1(int x, int y, double expected) {
            Point point1 = Point.of(1, 1);
            Point point2 = Point.of(x, y);

            double actual = point1.calculateDistance(point2);

            assertThat(actual).isEqualTo(expected, offset(0.001));
        }
    }

    @DisplayName("x 좌표, y 좌표 차이를 알 수 있다")
    @Nested
    class DifferenceTest {

        @DisplayName("x 좌표의 차이를 알 수 있다")
        @ParameterizedTest(name = "(10,10)과 ({0},{1}) 사이의 x좌표 차이는 {2}이다")
        @CsvSource({"4,5,6", "10,13,0", "15,11,-5", "10,10,0"})
        void calculateXDifferenceTest(int x, int y, double expected) {
            Point point1 = Point.of(10, 10);
            Point point2 = Point.of(x, y);

            double actual = point1.calculateXDifference(point2);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("y 좌표의 차이를 알 수 있다")
        @ParameterizedTest(name = "(10,10)과 ({0},{1}) 사이의 y좌표 차이는 {2}이다")
        @CsvSource({"4,5,5", "13,10,0", "15,11,-1", "10,10,0"})
        void calculateYDifferenceTest(int x, int y, double expected) {
            Point point1 = Point.of(10, 10);
            Point point2 = Point.of(x, y);

            double actual = point1.calculateYDifference(point2);

            assertThat(actual).isEqualTo(expected);
        }
    }

    @DisplayName("동치를 판단할 수 있다")
    @Nested
    class EqualTest {

        @DisplayName("x, y값이 같은 경우 같은 점이고, 해시코드가 같다")
        @ParameterizedTest(name = "x : {0}, y : {1}")
        @CsvSource({"12,24", "1,3", "16,8"})
        void equalsTest(int x, int y) {
            Point point1 = Point.of(x, y);
            Point point2 = Point.of(x, y);

            assertThat(point1).isEqualTo(point2)
                    .hasSameHashCodeAs(point2);
        }

        @DisplayName("x 또는 y 값이 다르면 다른 점점이다")
        @ParameterizedTest(name = "({0},{1}), ({2},{3})")
        @CsvSource({"1,2,2,1", "8,7,7,7", "10,20,10,19"})
        void equalsTest_whenNotEqual(int x1, int y1, int x2, int y2) {
            Point point1 = Point.of(x1, y1);
            Point point2 = Point.of(x2, y2);

            assertThat(point1).isNotEqualTo(point2);
        }
    }
}
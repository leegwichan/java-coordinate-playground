package coordinatecalculator.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PointTest {

    @DisplayName("동치를 판단할 수 있다")
    @Nested
    class EqualTest {

        @DisplayName("x, y값이 같은 경우 같은 점이고, 해시코드가 같다")
        @ParameterizedTest(name = "x : {0}, y : {1}")
        @CsvSource({"12,24", "1,3", "16,8"})
        void equalsTest(int x, int y) {
            Point point1 = Point.of(x, y);
            Point point2 = Point.of(x, y);

            assertThat(point1).isEqualTo(point2);
            assertThat(point1).hasSameHashCodeAs(point2);
        }

        @DisplayName("x 또는 y 값이 다르면 다른 점점이다")
       @ParameterizedTest(name = "({0},{1}), ({2},{3})")
        @CsvSource({"1,2,2,1","8,7,7,7","10,20,10,19"})
        void equalsTest_whenNotEqual(int x1, int y1, int x2, int y2) {
            Point point1 = Point.of(x1, y1);
            Point point2 = Point.of(x2, y2);

            assertThat(point1).isNotEqualTo(point2);
        }
    }
}
package coordinatecalculator.entity;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;

class RectangleTest {

    @DisplayName("객체 생성 테스트")
    @Nested
    class CreationTest {

        @DisplayName("입력한 점의 개수가 3개인 경우, 예외를 발생시킨다")
        @Test
        void createTest_whenPointsSizeIs3_throwException() {
            List<Point> points = List.of(Point.of(1,2), Point.of(1,5), Point.of(5,2));

            assertThatThrownBy(() -> Rectangle.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력한 점의 개수가 4개이어야 합니다");
        }

        @DisplayName("입력한 점의 개수가 5개인 경우, 예외를 발생시킨다")
        @Test
        void createTest_whenPointsSizeIs5_throwException() {
            List<Point> points = List.of(Point.of(1,2), Point.of(1,5), Point.of(5,2),
                    Point.of(5,5), Point.of(5,8));

            assertThatThrownBy(() -> Rectangle.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력한 점의 개수가 4개이어야 합니다");
        }

        @DisplayName("입력한 점이 중복되어 있을 경우, 예외를 발생시킨다")
        @Test
        void createTest_whenPointsOverlapped_throwException() {
            List<Point> points = List.of(Point.of(1,2), Point.of(1,5), Point.of(5,2),
                    Point.of(1,2));

            assertThatThrownBy(() -> Rectangle.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("점은 중복되지 않아야 합니다");
        }

        @DisplayName("직사각형 꼴이 아닌 경우, 예외를 발생시킨다")
        @Test
        void createTest_whenPointsNotRectangle_throwException() {
            List<Point> points = List.of(Point.of(1,2), Point.of(1,5), Point.of(5,2),
                    Point.of(5,6));

            assertThatThrownBy(() -> Rectangle.of(points))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("점들이 직사각형 꼴이어야 합니다");
        }

        @DisplayName("직사각형 꼴을 입력하면 정상적으로 객체를 생성한다")
        @Test
        void createTest() {
            List<Point> points = List.of(Point.of(1,2), Point.of(1,5), Point.of(5,2),
                    Point.of(5,5));

            assertThatCode(() -> Rectangle.of(points))
                    .doesNotThrowAnyException();
        }
    }
}
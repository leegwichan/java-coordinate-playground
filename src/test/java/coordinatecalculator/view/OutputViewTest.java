package coordinatecalculator.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import coordinatecalculator.dto.ShapeDto;
import coordinatecalculator.dto.ShapeResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import coordinatecalculator.dto.PointDto;
import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.view.printer.SpyPrinter;

class OutputViewTest {

    @DisplayName("정적 팩토리 메서드 테스트")
    @Nested
    class CreationTest {

        @DisplayName("printer에 null을 넣으면, 예외를 던진다")
        @Test
        void createTest_whenPrinterIsNull_throwException() {
            assertThatThrownBy(() -> OutputView.of(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("Console OutputView는 정상적으로 생성한다")
        @Test
        void createConsoleOutputViewTest() {
            assertThatCode(OutputView::createConsoleOutputView).doesNotThrowAnyException();
        }
    }

    @DisplayName("도형 출력 테스트")
    @Nested
    class ShapePrintTest {

        @DisplayName("사각형의 넓이를 출력할 수 있다")
        @Test
        void printTest_Rectangle() {
            SpyPrinter printer = new SpyPrinter();
            OutputView outputView = OutputView.of(printer);
            ShapeResultDto dto = getShapeResultDto(ShapeDto.SQUARE, 5.00);
            String expected = String.format("사각형 넓이는 %f", 5.00);

            outputView.print(dto);

            assertThat(printer.getPrintedMessage()).contains(expected);
        }

        @DisplayName("삼각형의 넓이를 출력할 수 있다")
        @Test
        void printTest_Triangle() {
            SpyPrinter printer = new SpyPrinter();
            OutputView outputView = OutputView.of(printer);
            ShapeResultDto dto = getShapeResultDto(ShapeDto.TRIANGLE, 5.00);
            String expected = String.format("삼각형의 넓이는 %f", 5.00);

            outputView.print(dto);

            assertThat(printer.getPrintedMessage()).contains(expected);
        }

        ShapeResultDto getShapeResultDto(ShapeDto shape, double area) {
            PointsDto points = PointsDto.of(PointDto.of(1, 2), PointDto.of(3, 4), PointDto.of(5, 6));
            return ShapeResultDto.builder()
                    .points(points).shape(shape).area(area).build();
        }

    }


    @DisplayName("예외 메세지를 출력할 수 있다")
    @Test
    void printErrorTest() {
        SpyPrinter printer = new SpyPrinter();
        OutputView outputView = OutputView.of(printer);
        Exception exception = new IllegalArgumentException("예외 메세지 입니다");
        String expected = "[ERROR] 예외 메세지 입니다\n";

        outputView.print(exception);

        assertThat(printer.getPrintedMessage()).isEqualTo(expected);
    }
}
package coordinatecalculator.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import coordinatecalculator.dto.ShapeResultDto;
import coordinatecalculator.entity.PlaneShape;
import coordinatecalculator.entity.Rectangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import coordinatecalculator.dto.PointDto;
import coordinatecalculator.dto.PointsDto;
import coordinatecalculator.view.printer.SpyPrinter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @DisplayName("거리를 출력할 수 있다")
    @ParameterizedTest
    @CsvSource({"1.4", "2.1803", "1.1412"})
    void printDistanceTest(double length) {
        SpyPrinter printer = new SpyPrinter();
        OutputView outputView = OutputView.of(printer);
        String expected = String.format("두 점 사이의 거리는 %f", length);

        outputView.printLength(length);

        assertThat(printer.getPrintedMessage()).isEqualTo(expected);
    }

    @DisplayName("도형 출력 테스트")
    @Nested
    class ShapePrintTest {

        @Mock
        private CoordinateView coordinateView;

        @InjectMocks
        private OutputView outputView;

        @DisplayName("좌표계 내용을 출력한다")
        @Test
        void printTest_checkPrintingCoordinate() {
            SpyPrinter printer = new SpyPrinter();
            OutputView outputView = OutputView.of(printer);
            when(coordinateView.getCoordinateView(any())).thenReturn("COORDINATE_RESULT");
            ShapeResultDto dto = getShapeResultDto(Rectangle.class, 5.00);

            outputView.print(dto);

            assertThat(printer.getPrintedMessage()).contains("COORDINATE_RESULT");
        }

        ShapeResultDto getShapeResultDto(Class<? extends PlaneShape> shape, double area) {
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
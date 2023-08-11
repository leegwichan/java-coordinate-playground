package coordinatecalculator.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @DisplayName("좌표계 출력 테스트")
    @Nested
    class CoordinateTest {

        private static final String LINE_SEPARATOR = "\n";

        @DisplayName("1 미만 또는 24 초과의 좌표를 입력하면 예외를 던진다")
        @ParameterizedTest(name = "x좌표 : {0}, y좌표 : {1}")
        @CsvSource({"0,12", "25,12", "12,0", "12,25"})
        void printCoordinateTest_whenOverCoordinateRange_throwException(int x, int y) {
            PointsDto pointsDto = PointsDto.of(PointDto.of(x, y));
            OutputView outputView = OutputView.of(new SpyPrinter());

            assertThatThrownBy(() -> outputView.printCoordinate(pointsDto))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("출력할 수 있는 좌표 범위를 벗어났습니다");
        }

        @DisplayName("점들을 입력받으면 정상적으로 출력한다 (10,10), (14,16)")
        @Test
        void printCoordinateTest_10_10_and_14_16() {
            SpyPrinter printer = new SpyPrinter();
            OutputView outputView = OutputView.of(printer);
            PointsDto pointsDto = PointsDto.of(PointDto.of(10, 10), PointDto.of(14, 16));

            outputView.printCoordinate(pointsDto);

            assertThat(printer.getPrintedMessage()).isEqualTo(getCoordinate_10_10_and_14_16());
        }

        @DisplayName("점들을 입력받으면 정상적으로 출력한다 (5,5), (24,1)")
        @Test
        void printCoordinateTest_5_5_and_24_1() {
            SpyPrinter printer = new SpyPrinter();
            OutputView outputView = OutputView.of(printer);
            PointsDto pointsDto = PointsDto.of(PointDto.of(5,5), PointDto.of(24,1));

            outputView.printCoordinate(pointsDto);

            assertThat(printer.getPrintedMessage()).isEqualTo(getCoordinate_5_5_and_24_1());
        }

        String getCoordinate_10_10_and_14_16() {
            return "24|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "22|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "20|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "18|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "16|                           •                    " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "14|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "12|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "10|                   •                            " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + " 8|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + " 6|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + " 4|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + " 2|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "  +ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" + LINE_SEPARATOR
                    + " 0    2   4   6   8   10  12  14  16  18  20  22  24" + LINE_SEPARATOR;
        }

        String getCoordinate_5_5_and_24_1() {
            return "24|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "22|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "20|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "18|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "16|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "14|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "12|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + "10|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + " 8|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + " 6|                                                " + LINE_SEPARATOR
                    + "  |         •                                      " + LINE_SEPARATOR
                    + " 4|                                                " + LINE_SEPARATOR
                    + "  |                                                " + LINE_SEPARATOR
                    + " 2|                                                " + LINE_SEPARATOR
                    + "  |                                               •" + LINE_SEPARATOR
                    + "  +ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" + LINE_SEPARATOR
                    + " 0    2   4   6   8   10  12  14  16  18  20  22  24" + LINE_SEPARATOR;
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
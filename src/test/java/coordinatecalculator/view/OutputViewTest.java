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

        @DisplayName("1 미만 또는 24 초과의 좌표를 입력하면 예외를 던진다")
        @Test
        void printCoordinateTest() {
            SpyPrinter printer = new SpyPrinter();
            OutputView outputView = OutputView.of(printer);
            PointsDto pointsDto = PointsDto.of(PointDto.of(10, 10), PointDto.of(14, 16));

            outputView.printCoordinate(pointsDto);

            assertThat(printer.getPrintedMessage()).isEqualTo(getCoordinate_10_10_and_14_16());
        }

        String getCoordinate_10_10_and_14_16() {
            String lineSeparator = "\n";

            return "24|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "22|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "20|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "18|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "16|                           •                    " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "14|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "12|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "10|                   •                            " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + " 8|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + " 6|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + " 4|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + " 2|                                                " + lineSeparator
                    + "  |                                                " + lineSeparator
                    + "  +ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" + lineSeparator
                    + " 0    2   4   6   8   10  12  14  16  18  20  22  24" + lineSeparator;
        }
    }
}
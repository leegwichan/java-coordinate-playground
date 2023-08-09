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
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;
import coordinatecalculator.view.printer.SpyPrinter;
import coordinatecalculator.view.reader.ConsoleReader;
import coordinatecalculator.view.reader.MockReader;
import coordinatecalculator.view.reader.Reader;

class InputViewTest {

    @DisplayName("정적 팩토리 테스트")
    @Nested
    class CreationTest {

        @DisplayName("Reader가 null인 경우 예외를 던진다")
        @Test
        void createTest_whenReaderIsNull_throwException() {
            Printer printer = new ConsolePrinter();

            assertThatThrownBy(() -> InputView.of(null, printer))
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("Printer가 null인 경우 예외를 던진다")
        @Test
        void createTest_whenPrinterIsNull_throwException() {
            Reader reader = new ConsoleReader();

            assertThatThrownBy(() -> InputView.of(reader, null))
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("Console InputView를 통해 정상적인 객체를 만들 수 있다")
        @Test
        void createTest_whenUseConsoleInput_createSuccessfully() {
            assertThatCode(InputView::createConsoleInputView)
                    .doesNotThrowAnyException();
        }
    }

    @DisplayName("좌표들을 입력 받을 수 있다")
    @Nested
    class PointsTest {

        private static final String DEFAULT_INPUT_MESSAGE = "(10,10)-(14,16)";

        @DisplayName("입력 요청 메세지를 입력받을 수 있다")
        @Test
        void inputPointsTest_checkRequestMessage() {
            SpyPrinter printer = new SpyPrinter();
            Reader reader = new MockReader(DEFAULT_INPUT_MESSAGE);
            InputView inputView = InputView.of(reader, printer);
            String expectedMessage = "좌표를 입력하세요.\n";

            inputView.inputPoints();

            assertThat(printer.getPrintedMessage()).isEqualTo(expectedMessage);
        }

        @DisplayName("형식에 맞춰 입력받지 못한 경우, 예외를 발생시킨다")
        @ParameterizedTest
        @CsvSource({"' '"})
        void inputPointsTest_whenNotFormatMatched_throwException(String message) {
            SpyPrinter printer = new SpyPrinter();
            Reader reader = new MockReader(message);
            InputView inputView = InputView.of(reader, printer);

            assertThatThrownBy(() -> inputView.inputPoints())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("좌표 형식이 일치하지 않습니다");
        }

//        @DisplayName("형식에 맞춰 좌표 2개를 입력받을 수 있다")
//        @Test
//        void inputPointsTest_whenInput2Points() {
//            String inputMessage = "(10,10)-(14,16)";
//            Reader reader = new MockReader(inputMessage);
//            InputView inputView = InputView.of(reader, new SpyPrinter());
//            PointsDto expect = PointsDto.of(PointDto.of(10, 10), PointDto.of(14, 16));
//
//            PointsDto actual = inputView.inputPoints();
//
//            assertThat(actual).usingRecursiveComparison().isEqualTo(expect);
//        }

    }
}
package coordinatecalculator.view;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import coordinatecalculator.view.printer.ConsolePrinter;
import coordinatecalculator.view.printer.Printer;
import coordinatecalculator.view.reader.ConsoleReader;
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
        void createTEst_whenUseConsoleInput_createSuccessfully() {
            assertThatCode(InputView::createConsoleInputView)
                    .doesNotThrowAnyException();
        }
    }
}
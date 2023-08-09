package coordinatecalculator.view;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
}
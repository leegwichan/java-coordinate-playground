package coordinatecalculator.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import coordinatecalculator.dto.PointDto;
import coordinatecalculator.dto.PointsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CoordinateViewTest {

    private static final String LINE_SEPARATOR = "\n";

    @DisplayName("1 미만 또는 24 초과의 좌표를 입력하면 예외를 던진다")
    @ParameterizedTest(name = "x좌표 : {0}, y좌표 : {1}")
    @CsvSource({"0,12", "25,12", "12,0", "12,25"})
    void printCoordinateTest_whenOverCoordinateRange_throwException(int x, int y) {
        PointsDto pointsDto = PointsDto.of(PointDto.of(x, y));
        CoordinateView coordinateView = new CoordinateView();

        assertThatThrownBy(() -> coordinateView.getCoordinateView(pointsDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출력할 수 있는 좌표 범위를 벗어났습니다");
    }

    @DisplayName("점들을 입력받으면 정상적으로 출력한다 (10,10), (14,16)")
    @Test
    void printCoordinateTest_10_10_and_14_16() {
        CoordinateView coordinateView = new CoordinateView();
        PointsDto pointsDto = PointsDto.of(PointDto.of(10, 10), PointDto.of(14, 16));

        String actual = coordinateView.getCoordinateView(pointsDto);

        assertThat(actual).isEqualTo(getCoordinate_10_10_and_14_16());
    }

    @DisplayName("점들을 입력받으면 정상적으로 출력한다 (5,5), (24,1)")
    @Test
    void printCoordinateTest_5_5_and_24_1() {
        CoordinateView coordinateView = new CoordinateView();
        PointsDto pointsDto = PointsDto.of(PointDto.of(5, 5), PointDto.of(24, 1));

        String actual = coordinateView.getCoordinateView(pointsDto);

        assertThat(actual).isEqualTo(getCoordinate_5_5_and_24_1());
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
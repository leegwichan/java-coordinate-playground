package coordinatecalculator.dto;

public final class PointDto {

    private final int x;
    private final int y;

    private PointDto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static PointDto of(int x, int y) {
        return new PointDto(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

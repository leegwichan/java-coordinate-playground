package coordinatecalculator.entity;

import java.util.Objects;

public final class Point {

    private final int x;
    private final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public double calculateDistance(Point point) {
        int xDifference = this.x - point.x;
        int yDifference = this.y - point.y;
        return Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
    }

    public int calculateXDifference(Point point) {
        return this.x - point.x;
    }

    public int calculateYDifference(Point point) {
        return this.y - point.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

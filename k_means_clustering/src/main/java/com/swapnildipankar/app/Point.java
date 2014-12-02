package com.swapnildipankar.app;

/**
 * Created by sdipankar on 11/30/14.
 */
public class Point implements Comparable<Point> {
    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Point point = (Point) object;
        double precision = 0.001;

        if (Math.abs(x - point.x) > precision) {
            return false;
        }
        if (Math.abs(y - point.y) > precision) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    @Override
    public int compareTo(Point point) {
        if(x < point.getX()) {
            return -1;
        } else if(x > point.getX()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

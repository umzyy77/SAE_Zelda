package com.zelda.zelda.modele.deplacement;

import java.util.Objects;

public class Point2D {
    private int x;
    private int y;

    public Point2D(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getPointX() {
        return x;
    }

    public int getPointY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return x == point2D.x && y == point2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D{" + "x=" + x + ", y=" + y + '}';
    }
}

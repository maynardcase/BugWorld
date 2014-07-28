package com.maynardcase.bugworld;


import static com.maynardcase.bugworld.Random.*;

/**
 * Created by maynard on 11/07/2014.
 */
public class GridLocation {

    private static int GRID_MAX_X = 100;
    private static int GRID_MAX_Y = 100;


    private int x;
    private int y;

    public GridLocation(int x, int y) {
        if (x > GRID_MAX_Y) { x = GRID_MAX_X; }
        if (y > GRID_MAX_Y) { y = GRID_MAX_Y; }
        if (x < 0) { x = 0; }
        if (y < 0) { y = 0; }
        this.x = x;
        this.y = y;
    }

    public GridLocation(GridLocation loc) {
        this.x = loc.getX();
        this.y = loc.getY();
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridLocation that = (GridLocation) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void goNorth() {
        if (y < GRID_MAX_Y) {
            y++;
        }
    }

    public void goEast() {
        if (x < GRID_MAX_X) {
            x++;
        }
    }

    public void goSouth() {
        if (y > 0) {
            y--;
        }
    }

    public void goWest() {
        if (x > 0) {
            x--;
        }
    }


    public String toString() {
        return String.format("GridLocation: %d,%d", x, y);
    }

    public int getGridMaxX() {
        return GRID_MAX_X;
    }

    public int getGridMaxY() {
        return GRID_MAX_Y;
    }

    public static GridLocation getRandomLocation() {
        int x = randInt(0, GRID_MAX_X);
        int y = randInt(0, GRID_MAX_Y);

        return new GridLocation(x, y);
    }


}

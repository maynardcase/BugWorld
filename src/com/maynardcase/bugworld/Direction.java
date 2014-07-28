package com.maynardcase.bugworld;

import static com.maynardcase.bugworld.Random.randInt;

/**
 * Created by maynard on 10/07/2014.
 */
public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static Direction getRandomDirection() {
        int x = Random.randInt(0, 3);
        switch (x) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            default:
                return NORTH;
        }
    }
}

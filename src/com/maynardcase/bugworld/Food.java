package com.maynardcase.bugworld;

/**
 * Created by maynard on 11/07/2014.
 */
public class Food {
    private GridLocation location;

    public Food(GridLocation location) {
        this.location = location;
    }

    public GridLocation getLocation() {
        return location;
    }
}

package com.maynardcase.bugworld.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import com.maynardcase.bugworld.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maynard on 15/07/2014.
 */
public class GridLocationTest {

    @Test
    public void testGridLocationGoNorth() {
        GridLocation loc = new GridLocation(1,1);
        loc.goNorth();

        assertThat(loc.getX(), is(1));
        assertThat(loc.getY(), is(2));
    }
}

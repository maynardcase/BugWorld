package com.maynardcase.bugworld.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import com.maynardcase.bugworld.*;
import com.maynardcase.bugworld.breeds.BreedSimpleDiagonal;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maynard on 14/07/2014.
 */
public class BugImplTest {

    private Breed breed;

    @Before
    public void setupTest() {
        GridLocation foodLocation = new GridLocation(50, 50);
        Grid.addFood(foodLocation);
        breed = new BreedSimpleDiagonal();
    }

    @Test
    public void testLookForFood() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.EAST, breed);
        assertThat(bug.canSeeFood(), is(true));
    }

    @Test
    public void testCannotSeeFoodDueToWrongDirection() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        assertThat(bug.canSeeFood(), is(false));
    }

    @Test
    public void testCannotSeeFoodDueToWrongLocation() {
        Bug bug = new BugImpl(100, new GridLocation(49,49), Direction.EAST, breed);
        assertThat(bug.canSeeFood(), is(false));
    }

    @Test
    public void testMoveNorth() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        bug.move();
        // Lose 2 energy each time we move
        assertThat(bug.getEnergy(), is(98));
        assertThat(bug.getLoc().getX(), is(49));
        assertThat(bug.getLoc().getY(), is(51));
    }

    @Test
    public void testTurnClockwise() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        bug.turnClockwise();

        assertThat(bug.getEnergy(), is(99));
        assertThat(bug.getDirection(), is(Direction.EAST));
        assertThat(bug.getLoc().getX(), is(49));
        assertThat(bug.getLoc().getY(), is(50));
    }

    @Test
    public void testTurnCounterClockwise() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        bug.turnCounterClockwise();

        assertThat(bug.getEnergy(), is(99));
        assertThat(bug.getDirection(), is(Direction.WEST));
        assertThat(bug.getLoc().getX(), is(49));
        assertThat(bug.getLoc().getY(), is(50));
    }

    @Test
    public void testWait() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        bug.waitForOneCycle();

        assertThat(bug.getEnergy(), is(99));
        assertThat(bug.getDirection(), is(Direction.NORTH));
        assertThat(bug.getLoc().getX(), is(49));
        assertThat(bug.getLoc().getY(), is(50));

    }

    @Test
    public void testIsDead() {
        Bug bug = new BugImpl(1, new GridLocation(49,50), Direction.NORTH, breed);

        assertThat(bug.isDead(), is(false));

        bug.waitForOneCycle();

        assertThat(bug.isDead(), is(true));
    }

    @Test
    public void testSetVariable() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        bug.setVariable("Test", 100);

        assertThat(bug.getEnergy(), is(99));

        assertThat(bug.getVariable("Test"), is(100));

        assertThat(bug.getVariable("NotPresent"), is(nullValue()));
    }

    @Test
    public void testIncrementVariable() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        bug.setVariable("Test", 100);

        bug.incrementVariable("Test");
        assertThat(bug.getEnergy(), is(98));
        assertThat(bug.getVariable("Test"), is(101));
    }

    @Test
    public void testDecrementVariable() {
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        bug.setVariable("Test", 100);

        bug.decrementVariable("Test");
        assertThat(bug.getEnergy(), is(98));
        assertThat(bug.getVariable("Test"), is(99));
    }



}

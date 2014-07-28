package com.maynardcase.bugworld.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import com.maynardcase.bugworld.*;
import com.maynardcase.bugworld.breeds.AbstractBreed;
import com.maynardcase.bugworld.breeds.BreedSimpleDiagonal;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;

/**
 * Created by maynard on 23/07/2014.
 */
public class BugControllerTest {
    private Breed breed;

    @Before
    public void setupTest() {
        GridLocation foodLocation = new GridLocation(50, 50);
        Grid.addFood(foodLocation);
        breed = new BreedTestBEQ();
        breed.initialise();
    }

    @Test
    public void testBEQ() {
        Bug bug = new Bug(100, new GridLocation(49,50), Direction.NORTH, breed);
        BugController bugController = new BugController(bug);

        // PC is zero
        assertThat(bugController.getPC(), is(0));
        bugController.execute(); // LBL START
        assertThat(bugController.getPC(), is(1));
        bugController.execute(); // STO VARIABLE 5
        assertThat(bugController.getPC(), is(2));
        assertThat(bugController.getBug().getVariable("VARIABLE").intValue(), is(5));
        bugController.execute(); // BEQ VARIABLE 4 START
        // The BEQ fails because the values are not equal
        assertThat(bugController.getPC(), is(3));
        bugController.execute(); // BEQ VARIABLE 5 START
        // This BEQ succeeds as the value is equal, so jump to the START label at PC 0
        assertThat(bugController.getPC(), is(0));


    }

    public class BreedTestBEQ extends AbstractBreed implements Breed {

        public void initialise() {
            beqProgram();
        }

        @Override
        public Color getColor() {
            return Color.RED;
        }

        public void beqProgram() {
            program.add("LBL START");
            program.add("STO VARIABLE 5");
            program.add("BEQ VARIABLE 4 START");
            program.add("BEQ VARIABLE 5 START");
        }
    }
}

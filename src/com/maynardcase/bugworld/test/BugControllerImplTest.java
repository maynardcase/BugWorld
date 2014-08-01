package com.maynardcase.bugworld.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.maynardcase.bugworld.*;
import com.maynardcase.bugworld.breeds.AbstractBreed;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Created by maynard on 23/07/2014.
 */
public class BugControllerImplTest {
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
        Bug bug = new BugImpl(100, new GridLocation(49,50), Direction.NORTH, breed);
        BugController bugController = new BugControllerImpl(bug);

        // PC is zero
        assertThat(bugController.getPC(), is(0));
        bugController.execute(); // LBL START
        assertThat(bugController.getPC(), is(1));
        bugController.execute(); // STO VARIABLE 5
        assertThat(bugController.getPC(), is(2));
        assertThat(bugController.getBug().getVariable("VARIABLE"), is(5));
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

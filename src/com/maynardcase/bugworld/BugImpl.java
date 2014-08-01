package com.maynardcase.bugworld;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by maynard on 10/07/2014.
 */
public class BugImpl implements Bug {

    private int energy;
    private GridLocation loc;
    private Direction direction;
    private Breed breed;
    private Map<String, Integer> variables = new HashMap<String, Integer>();
    private String name;
    final Logger logger = LoggerFactory.getLogger(BugImpl.class);



    private static final int LOOK_DISTANCE = 10;
    private static final int FOOD_ENERGY = 30;

    public BugImpl(int energy, GridLocation loc, Direction direction, Breed breed) {
        this.energy = energy;
        this.loc = loc;
        this.direction = direction;
        this.breed = breed;
        this.setEnergyByOffset(0);
        this.name=UUID.randomUUID().toString();

    }

    public String toString() {
        return String.format("Name: %s, Location: %s, Direction: %s, Energy: %d, Variables: %s", name, loc, direction, energy, variables);
    }



    /**
     * Move in the current direction one cell
     */
    public void move() {

        setEnergyByOffset(-2);

        switch (direction) {
            case NORTH:
                loc.goNorth();
                break;
            case EAST:
                loc.goEast();
                break;
            case SOUTH:
                loc.goSouth();
                break;
            case WEST:
                loc.goWest();
                break;
        }

        // Have we landed on some food?
        if (Grid.isFoodPresent(loc)) {
            energy = energy + FOOD_ENERGY;
            Grid.eatFood(loc);
        }

        logger.debug("After moving: " + this);

    }

    public void turnClockwise() {
        setEnergyByOffset(-1);
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }

        logger.debug("After turning clockwise: " + this);
    }

    public void turnCounterClockwise() {
        setEnergyByOffset(-1);
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }
        logger.debug("After turning counterclockwise: " + this);
    }

    public boolean canSeeFood() {
        setEnergyByOffset(-1); 

        GridLocation lookLoc = new GridLocation(loc);
        for (int i = 0; i < LOOK_DISTANCE; i++) {
            switch (direction) {
                case NORTH:
                    lookLoc.goNorth();
                    break;
                case EAST:
                    lookLoc.goEast();
                    break;
                case SOUTH:
                    lookLoc.goSouth();
                    break;
                case WEST:
                    lookLoc.goWest();
                    break;
            }
            if (Grid.isFoodPresent(lookLoc)) {
                logger.debug("Can see food: " + this);
                return true;
            }
        }
        logger.debug("Can't see food: " + this);
        return false;
    }

    public void waitForOneCycle() {
        setEnergyByOffset(-1);
        logger.debug("After waiting: " + this);
    }

    public void setVariable(String variable, Integer value) {
        setEnergyByOffset(-1);
        variables.put(variable, value);
        logger.debug("After setting variable: {1}", this);
    }


    public void incrementVariable(String variable) {
        setEnergyByOffset(-1);
        Integer value = variables.get(variable);
        value = new Integer(value.intValue() + 1);
        variables.put(variable, value);
    }

    public void decrementVariable(String variable) {
        setEnergyByOffset(-1);
        Integer value = variables.get(variable);
        value = new Integer(value.intValue() - 1);
        variables.put(variable, value);
    }

    /** Split into two bugs if the value of the variable is greater or equal to the test value */
    public void breedWhenReady(String variable, Integer testValue) {
        Integer variableValue = variables.get(variable);
        if (variableValue.compareTo(testValue) > 0) {
            int newEnergy = energy / 2;
            GridLocation childBugLoc = new GridLocation(loc.getX() + Random.randInt(-3,3), loc.getY() + Random.randInt(-3,3));
            Direction childBugDirection = Direction.getRandomDirection();
            Bug childBug = new BugImpl(newEnergy, childBugLoc, childBugDirection, breed);
            Grid.addBabyBug(childBug);
            setEnergy(newEnergy); // Halve the current energy of this bug
        } else {
            setEnergyByOffset(-1);
        }
    }

    private void setEnergyByOffset(int offset) {
        energy = energy + offset;
        variables.put("ENERGY", energy);
    }

    private void setEnergy(int value) {
        energy = value;
        variables.put("ENERGY", energy);
    }

    public String getInstruction(int pc) {
        return breed.getInstruction(pc);
    }

    public boolean isDead() {
        return (energy <= 0);
    }

    public int getEnergy() {
        return energy;
    }

    public GridLocation getLoc() {
        return loc;
    }

    public Direction getDirection() {
        return direction;
    }

    public Breed getBreed() {
        return breed;
    }

    public Integer getVariable(String variable) {
        return variables.get(variable);
    }

    public void drawBug(Graphics2D g2d) {
        g2d.fillRect(loc.getX(), loc.getY(), 1, 1);
    }
}

package com.maynardcase.bugworld;

import java.awt.*;

/**
 * Created by maynard on 01/08/2014.
 */
public interface Bug {
    public void move();
    public void turnClockwise();
    public void turnCounterClockwise();
    public boolean canSeeFood();
    public void waitForOneCycle();
    public void setVariable(String variable, Integer value);
    public void incrementVariable(String variable);
    public void decrementVariable(String variable);
    public void breedWhenReady(String variable, Integer testValue);
    public boolean isDead();
    public int getEnergy();
    public String getInstruction(int pc);
    public GridLocation getLoc();
    public Direction getDirection();
    public Breed getBreed();
    public Integer getVariable(String variable);
    public void drawBug(Graphics2D g2d);

}

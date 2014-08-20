package com.maynardcase.bugworld.breeds;

import com.maynardcase.bugworld.Breed;

import java.awt.*;

/**
 * Created by maynard on 10/07/2014.
 */
public class BreedMovingFoodHunt extends AbstractBreed implements Breed {

    public void initialise() {
        simpleFoodHunt();
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    @Override
    public String getName() {
        return "MovingFoodHunt";
    }

    public void simpleFoodHunt() {
        program.add("LBL MOVE_TO_FOOD");
        program.add("MOV");
        program.add("LOK MOVE_TO_FOOD");
        program.add("LBL SEARCH");
        program.add("TCW");
        program.add("MOV");
        program.add("MOV");
        program.add("LOK MOVE_TO_FOOD");
        program.add("JMP SEARCH");
    }
}

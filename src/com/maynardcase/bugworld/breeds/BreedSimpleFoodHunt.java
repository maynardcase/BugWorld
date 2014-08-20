package com.maynardcase.bugworld.breeds;

import com.maynardcase.bugworld.Breed;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maynard on 10/07/2014.
 */
public class BreedSimpleFoodHunt extends AbstractBreed implements Breed {

    public void initialise() {
        simpleFoodHunt();
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public String getName() {
        return "SimpleFoodHunt";
    }

    public void simpleFoodHunt() {
        program.add("LBL MOVE_TO_FOOD");
        program.add("MOV");
        program.add("LOK MOVE_TO_FOOD");
        program.add("LBL SEARCH");
        program.add("TCW");
        program.add("LOK MOVE_TO_FOOD");
        program.add("JMP SEARCH");
    }
}

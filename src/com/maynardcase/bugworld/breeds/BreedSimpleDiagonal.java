package com.maynardcase.bugworld.breeds;

import com.maynardcase.bugworld.Breed;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maynard on 10/07/2014.
 */
public class BreedSimpleDiagonal extends AbstractBreed implements Breed {

    public void initialise() {
        simpleDiagonal();
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public String getName() {
        return "SimpleDiagonal";
    }

    public void simpleDiagonal() {
        program.add("LBL START");
        program.add("MOV");
        program.add("TCW");
        program.add("MOV");
        program.add("TCC");
        program.add("JMP START");
    }
}

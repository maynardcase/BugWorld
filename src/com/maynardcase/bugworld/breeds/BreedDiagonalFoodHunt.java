package com.maynardcase.bugworld.breeds;

import com.maynardcase.bugworld.Breed;

import java.awt.*;

/**
 * Created by maynard on 10/07/2014.
 */
public class BreedDiagonalFoodHunt extends AbstractBreed implements Breed {

    public void initialise() {
        breedDiagonalFoodHunt();
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    public void breedDiagonalFoodHunt() {
        // Run towards food
        program.add("LBL MOVE_TO_FOOD");
        program.add("MOV");
        program.add("LOK MOVE_TO_FOOD");
        // Can we breed?
        program.add("BRD ENERGY 500");
        // Move diagonally north-east
        program.add("LBL MOVE_DIAGONALLY");
        program.add("MOV");
        program.add("TCW");
        program.add("MOV");
        program.add("TCC");
        // Look in all four directions for food
        program.add("STO SCAN_COUNT 0");
        program.add("LBL SEARCH");
        program.add("TCW");
        // Any food ahead? If so run towards it
        program.add("LOK MOVE_TO_FOOD");
        program.add("INC SCAN_COUNT");
        // When we've looked in all four directions try moving diagonally
        program.add("BEQ SCAN_COUNT 4 MOVE_DIAGONALLY");
        // Otherwise keep turning
        program.add("JMP SEARCH");
    }
}

package com.maynardcase.bugworld;

import com.maynardcase.bugworld.breeds.BreedDiagonalFoodHunt;
import com.maynardcase.bugworld.breeds.BreedMovingFoodHunt;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by maynard on 10/07/2014.
 */
public class Simulator {
    private static final int INITIAL_FOOD_SIZE = 1000;
    private static final int NUMBER_OF_BUGS = 5;
    private static final int FOOD_GROWTH_RATE = 2;
    private static GridView view;
    private static List<BugController> bugs;
    private static List<BugController> newBugs;
    private static List<BugController> deadBugs;
    private static Map<Breed,Queue<Integer>> populationHistory;

    public static void main(String[] args) {
        // Create bugs
        initialiseBugs();
        // Create food
        initialiseFoodLocations();
        // Initialise population history
        initialisePopulationHistory();
        // Set up the view
        initialiseView();


    }

    private static void initialisePopulationHistory() {
        populationHistory = new HashMap<Breed,Queue<Integer>>();
    }

    public static void updateBugStates() {
        bugs = Grid.getBugs();
        deadBugs = Grid.getDeadBugs();
        // Execute current instruction for each bug
        for (BugController b : bugs) {
            int exitStatus = b.execute();

            // Check for death
            if (exitStatus != 0 || b.getBug().isDead()) {
                deadBugs.add(b);
            }
        }
        // Remove dead bugs
        for (BugController b : deadBugs) {
            if (bugs.contains(b)) {
                bugs.remove(b);
            }
        }

        // Add new bugs
        newBugs = Grid.getNewBugs();
        for (BugController b : newBugs) {
            bugs.add(b);
        }
        newBugs.clear();

        // Grow new food
        for (int i=0; i<FOOD_GROWTH_RATE; i++) {
            Grid.addFood();
        }

        // Store history of population
        Set<Breed> allBreeds = Grid.getBugBreeds();
        for (Breed b : allBreeds)  {
            Queue<Integer> breedHistory = populationHistory.get(b);
            if (breedHistory == null) {
                breedHistory = new LinkedList<Integer>();
            }
            breedHistory.add(Grid.getBugPopulation(b));
            if (breedHistory.size() > 500) {
                breedHistory.remove();
            }
            populationHistory.put(b, breedHistory);
        }
    }

    private static void initialiseBugs() {
        Breed testBreed = new BreedMovingFoodHunt();
        testBreed.initialise();
        for (int i = 0; i < NUMBER_OF_BUGS; i++) {
            Grid.addBug(testBreed);
        }

        Breed foodHunter = new BreedDiagonalFoodHunt();
        foodHunter.initialise();
        for (int i = 0; i < NUMBER_OF_BUGS; i++) {
            Grid.addBug(foodHunter);
        }

    }

    private static void initialiseFoodLocations() {
        for (int i = 0; i < INITIAL_FOOD_SIZE; i++) {
            Grid.addFood();
        }

    }

    private static void initialiseView() {
         view = new SwingGridView();
        view.initialise();
    }


    public static boolean allBugsDead() {
        return (bugs.size() <= 0);
    }

    public static Map<Breed,Queue<Integer>> getPopulationHistory() {
        return populationHistory;
    }
}

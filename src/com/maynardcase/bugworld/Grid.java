package com.maynardcase.bugworld;

import java.util.*;

/**
 * Created by maynard on 11/07/2014.
 */
public class Grid {

    private static List<BugController> bugs = new ArrayList<BugController>();
    private static List<BugController> newBugs = new ArrayList<BugController>();
    private static List<BugController> deadBugs = new ArrayList<BugController>();
    private static List<Food> foods = new ArrayList<Food>();


    public static void addFood() {
        GridLocation loc = GridLocation.getRandomLocation();
        Food f = new Food(loc);
        foods.add(f);
    }

    public static void addFood(GridLocation location) {
        Food f = new Food(location);
        foods.add(f);
    }

    public static void addBug(Breed breed) {
        GridLocation location = GridLocation.getRandomLocation();
        Bug bug = new BugImpl(100, location, Direction.NORTH, breed);
        BugController bugController = new BugControllerImpl(bug);
        bugs.add(bugController);
    }

    public static void addBug(Bug bug) {
        BugController bugController = new BugControllerImpl(bug);
        bugs.add(bugController);
    }

    public static void addBabyBug(Bug bug) {
        BugController bugController = new BugControllerImpl(bug);
        newBugs.add(bugController);
    }


    /**
     * Is there food available at the given location? If so this might result in energy being added or the LOK command returning true
     */
    public static boolean isFoodPresent(GridLocation location) {
        for (Food f : foods) {
            if (location.equals(f.getLocation())) {
                // Found some food at the given location, so break out early
                return true;
            }
        }
        return false;
    }

    public static void eatFood(GridLocation location) {
        int index = 0;
        int foundIndex = -1;
        for (Food f : foods) {
            if (location.equals(f.getLocation())) {
                // Found some food at the given location, so break out early
                foundIndex = index;
                break;
            }
            index++;
        }
        if (foundIndex >= 0) {
            foods.remove(foundIndex);
        }
    }

    public static List<BugController> getBugs() {
        return bugs;
    }

    public static List<BugController> getDeadBugs() {
        return deadBugs;
    }

    public static List<Food> getFoods() {
        return foods;
    }

    public static List<BugController> getNewBugs() {
        return newBugs;
    }

    public static int getBugPopulation() {
        return bugs.size();
    }

    public static int getBugPopulation(Breed breed) {
        int breedCount = 0;
        for (BugController b : bugs) {
            if (breed.equals(b.getBug().getBreed())) {
                breedCount++;
            }
        }
        return breedCount;
    }

    public static Set<Breed> getBugBreeds() {
        Set<Breed> allBreeds = new HashSet<Breed>();
        for (BugController b : bugs) {
            allBreeds.add(b.getBug().getBreed());
        }
        return allBreeds;
    }
}

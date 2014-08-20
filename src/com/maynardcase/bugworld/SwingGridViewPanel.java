package com.maynardcase.bugworld;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by maynard on 16/07/2014.
 */
public class SwingGridViewPanel extends JPanel {

    private int gridWidth = 500;
    private int gridHeight = 500;
    private int scale = 4;
    private int populationStartPointx = 410;
    private int populationStartPointy = 400;


    BufferedImage bi = new BufferedImage(gridWidth, gridHeight, BufferedImage.TYPE_INT_RGB);
    //draw to this graphics
    Graphics2D g = bi.createGraphics();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Paint food
        g2.setColor(Color.GREEN);

        List<Food> foods = Grid.getFoods();
        for (Food food : foods) {
            GridLocation loc = food.getLocation();
            g2.fillRect(loc.getX() * scale, loc.getY() * scale, scale, scale);
        }


        // Paint bugs
        List<BugController> bugs = Grid.getBugs();
        for (BugController bugController : bugs) {
            Breed breed = bugController.getBug().getBreed();
            g2.setColor(breed.getColor());

            GridLocation loc = bugController.getBug().getLoc();
            g2.fillRect(loc.getX() * scale, loc.getY() * scale, scale, scale);
        }

        // Paint population statistics
        String population = "Population: " + new Integer(Grid.getBugPopulation()).toString();
        g2.drawString(population, gridWidth-90, 30);

        Map<Breed,Queue<Integer>> populationHistory = Simulator.getPopulationHistory();

        int breedCount = 0;
        for (Breed b : populationHistory.keySet()) {
            g2.setColor(b.getColor());
            String breedPopulation = "Population (" + b.getName() + "): " + new Integer(Grid.getBugPopulation(b)).toString();
            g2.drawString(breedPopulation, gridWidth-90, 30 + (20 * (breedCount + 2)));
            int count = 0;
            for (Integer i : populationHistory.get(b)) {
                g2.fillRect(populationStartPointx + (count / 5), populationStartPointy - i, 1, 1);
                count++;
                if (count > 500) {
                    break;
                }
            }
            breedCount++;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gridWidth+200, gridHeight);
    }

}

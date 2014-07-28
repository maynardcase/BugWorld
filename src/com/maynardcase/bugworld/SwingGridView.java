package com.maynardcase.bugworld;

import javax.swing.*;
import java.awt.*;

/**
 * Created by maynard on 13/07/2014.
 */
public class SwingGridView implements GridView {

    private JPanel panel = new SwingGridViewPanel();
    private boolean keeprunning;

    @Override
    public void redraw() {
        panel.repaint();
    }

    public void createGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BugWorld");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void initialise() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
                startAnimation();
            }
        });
    }


    public void startAnimation() {
        keeprunning=true;
        Thread animationThread = new Thread(
                new Runnable() {
                    public void run() {
                        while (keeprunning) {
                            Simulator.updateBugStates();
                            if (Simulator.allBugsDead()) {
                                keeprunning=false;
                            }

                            redraw();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        animationThread.start();
    }
}

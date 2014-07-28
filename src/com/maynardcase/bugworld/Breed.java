package com.maynardcase.bugworld;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maynard on 10/07/2014.
 */
public interface Breed {
    public void initialise();
    public String getInstruction(int pc);
    public Color getColor();

}

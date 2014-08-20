package com.maynardcase.bugworld.breeds;

import com.maynardcase.bugworld.Breed;

import java.awt.*;
import java.util.*;

/**
 * Created by maynard on 16/07/2014.
 */
public class AbstractBreed {
    protected java.util.List<String> program = new ArrayList<String>();

    public String getInstruction(int pc) {
        if (pc >= program.size()) {
            return "END";
        }
        return program.get(pc);
    }

    public String getName() {
        return "Unknown Breed";
    }
}

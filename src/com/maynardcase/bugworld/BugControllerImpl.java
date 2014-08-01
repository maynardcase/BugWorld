package com.maynardcase.bugworld;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maynard on 10/07/2014.
 */
public class BugControllerImpl implements BugController {
    private int pc; // Program Counter
    private Bug bug;
    private Map<String, Integer> labels;

    public BugControllerImpl(Bug bug) {
        this.bug = bug;
        this.pc = 0;
        this.labels = new HashMap<String, Integer>();
    }

    public int execute() {
        String instruction = bug.getInstruction(pc);

        // Parse the instruction and call the appropriate method on the Bug
        String[] tokens = instruction.split(" ");
        String cmd = tokens[0];

        if ("MOV".equals(cmd)) {
            // Move forwards
            bug.move();
            pc++;
        } else if ("TCW".equals(cmd)) {
            bug.turnClockwise();
            pc++;
        } else if ("TCC".equals(cmd)) {
            bug.turnCounterClockwise();
            pc++;
        } else if ("LOK".equals(cmd)) {
            if (bug.canSeeFood()) {
                String label = tokens[1];
                pc = labels.get(label); // Jump to new program counter location
            } else {
                pc++;
            }
        } else if ("END".equals(cmd)) {
            // Bug is dead
            return 1;
        } else if ("LBL".equals(cmd)) {
            // Store a label for this program counter position
            // Syntax: LBL [label name]
            if (tokens.length < 2) {
                System.out.println("Error: LBL must specify a label name");
                return 1;
            } else {
                String label = tokens[1];
                labels.put(label, pc);
                pc++;
            }
        } else if ("JMP".equals(cmd)) {
            // Jump to a label
            // Syntax: JMP [label name]
            if (tokens.length < 2) {
                System.out.println("Error: LBL must specify a label name");
                return 1;
            } else {
                String label = tokens[1];
                pc = labels.get(label);
            }
        } else if ("STO".equals(cmd)) {
            // Store a value in a variable
            // Syntax: STO [variable name] [integer value]
            if (tokens.length < 3) {
                System.out.println("Error: STO must specify a variable name and a integer value");
                return 1;
            } else {
                String variableName = tokens[1];
                Integer value = Integer.valueOf(tokens[2]);
                bug.setVariable(variableName, value);
                pc++;
            }
        } else if ("INC".equals(cmd)) {
            // Increment a variable
            // Syntax: INC [variable name]
            if (tokens.length < 2) {
                System.out.println("Error: INC must specify a variable name");
                return 1;
            } else {
                String variableName = tokens[1];
                bug.incrementVariable(variableName);
                pc++;
            }
        } else if ("DEC".equals(cmd)) {
            // Decrement a variable
            // Syntax: DEC [variable name]
            if (tokens.length < 2) {
                System.out.println("Error: DEC must specify a variable name");
                return 1;
            } else {
                String variableName = tokens[1];
                bug.decrementVariable(variableName);
                pc++;
            }

        } else if ("BEQ".equals(cmd)) {
            // Syntax: BEQ [variable name] [value] [label]
            // Jump to the given label if the variable is equal to the given value
            if (tokens.length < 4) {
                System.out.println("Error: BEQ must specify a variable name, an integer value, and a label to jump to if they are equal");
                return 1;
            } else {
                String variableName = tokens[1];
                Integer value = Integer.valueOf(tokens[2]);
                String label = tokens[3];

                if (bug.getVariable(variableName).equals(value)) {
                    if (labels.containsKey(label)) {
                        pc = labels.get(label);
                    } else {
                        System.out.println("Error: BEQ specified a non-existent label " + label);
                        return 1;
                    }
                } else {
                    pc++;
                }
            }
        } else if ("BRD".equals(cmd)) {
            // Syntax: BRD [variable name] [value]
            // Breed (split into two bugs, each with half the energy) if the variable is greater or equal to the value
            if (tokens.length < 3) {
                System.out.println("Error: BRD must specify a variable name and an integer value");
                return 1;
            } else {
                String variableName = tokens[1];
                Integer value = Integer.valueOf(tokens[2]);
                bug.breedWhenReady(variableName, value);
            }
            pc++;
        } else if ("WAI".equals(cmd)) {
            // Wait
            bug.waitForOneCycle();
        } else {
            // default; unknown instruction
            System.out.println("Unknown instruction " + cmd + " at pc " + pc);
            pc++;
        }

        return 0;
    }

    public String toString() {
        return String.format("Controller for bug with pc %d", pc);
    }

    public Bug getBug() {
        return bug;
    }

    public int getPC() {
        return pc;
    }
}

BugWorld
========

Bugs hunt for food on a 2D grid, running simple programs in a pseudo-assembler language that you can write!

Currently this is in development, so it may not be functional or the functionality may change dramatically without warning.

Bugs
====
A bug has these attributes:
* A breed (specifying a program to run)
* A current location in the program
* A location on a grid
* A direction (north, south, east or west)
* A current energy level
* A set of variables (see below)
* A set of labels (see below)

Bugs aim to survive. They do this by moving around the grid and eating food to gain energy. They can also "breed" by dividing into two bugs if desired. 

However, they are competing with other bugs running different programs.

Bug Programming
===============

You can create programs for bugs with the Bug Assembler Language. 
* One instruction per line
* Labels allow branching; they are a single string containing any character except space.
* Variables are stored

Current implemented instructions:
* MOV - move forward
* TCW - turn clockwise
* TCC - turn counter-clockwise
* LBL [label] - Create a label called "label" at this program location
* JMP [label] - Jump to a label
* STO [variable] [integer value] - store an integer value with name "variable"
* INC [variable] - add 1 to the variable
* DEC [variable] - subtract 1 from the variable
* LOK [label] - look in the current direction for food; if there is food jump to the label
* BEQ [variable] [value] [label] - If variable = value jump to the label
* BRD [variable] [value] - if variable >= value then split into two bugs, each with half as much energy

Example program:

    LBL START
    MOV
    TCW
    MOV
    TCC
    JMP START

This program makes the bugs go diagonally across the grid. 

    LBL MOVE_TO_FOOD
    MOV
    LOK MOVE_TO_FOOD
    LBL SEARCH
    TCW
    MOV
    MOV
    LOK MOVE_TO_FOOD
    JMP SEARCH

This program makes the bugs run towards food. If they can't see any food they stay still, rotating and looking for food. 

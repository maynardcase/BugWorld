Current list of features / bugs
===============================

* Improve logging - add INFO level on each iteration
* Refactor Bug into an interface and implement as BugImpl
* Refactor BugController into an interface and implement as BugControllerASMImpl
* Move all program state into BugImpl so that BugControllerASMImpl is purely focussed on parsing assembler programs and making appropriate calls into the Bug
* UI:
   * Population stats divided into Breeds
   * Button to Pause / Start / Reset
   * Clicking on a bug should give a view of the breed, program, PC, energy, etc
* Generate random programs and see if any are successful
* Mutation in programs
* Add LFB (LookForBugs) instruction, or extend LOK with additional optional label if a bug is seen
* Import programs from file at startup
* Import programs from file in GUI


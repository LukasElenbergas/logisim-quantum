# logisim-quantum
Quantum logic gate implementation in the Logisim (2.7.1) tool

## Important links:
- Other logisim repos:
    - https://github.com/logisim-evolution/logisim-evolution
    - https://github.com/Logisim-Ita/Logisim
    - https://github.com/lawrancej/logisim
- Useful links:
    - https://logisim.altervista.org/developerstutorial.php
    - https://reintech.io/blog/java-project-structure-organizing-managing-large-projects

## Application startup process:
1. Main.java startup.run() ->
2. Startup.java run() > ProjectActions.doNew(...) ->
3. ProjectActions.java doNew(...) > completeProject(...) > new CreateFrame(...) (RUNNABLE) > Frame(newProject) ->
4. Frame.java Canvas(proj) ->
5. Canvas.java paintThread.start() ->
6. Frame.java (everything else in constructor finishes) ->
7. ProjectActions.java CrateFrame run() (everything else in there finishes) ->
8. (we have a running Canvas thread)

## TODOS:
- [x] Setup jre, jdk, Intellij on work PC
- [x] Setup several GitHub Account access on work PC
- [x] Import missing external libraries to libs folder
- [x] Fix any outstanding errors
- [x] Figure out how to and compile the code and COMPILE
- [x] Prune any other unnecessary stuff from the repo
- [x] Explore how is it advised to introduce new changes in the guide
- [ ] (FIRST BIG CHANGE) Add a folder where quantum logic gates will be stored
- [ ] Introduction of complex numbers (add new external package?)
- [ ] Explore how the engine works right now to gauge work needed to be done
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
    - https://quantumcomputing.stackexchange.com/questions/1206/how-does-measurement-of-one-qubit-affect-the-others#:~:text=To%20represent%20a%20quantum%20computer's,a%20system%20of%20multiple%20qubits.
    - https://quantumcomputing.stackexchange.com/questions/9011/how-to-measure-one-of-the-qubits-in-a-two-qubit-register

## Notes:
- A .jar can be built via Build > Build Artifacts via Intellij
- There seem to be around 15-20 threads running at once, some of which include:
  - CanvasPaintThread - main thread where the GUI is running
  - SimulatorTicker - thread where the circuit simulation is running
- There is a method convertToNands() which indicates gate conversion of some kind in the engine

## Application startup process:
1. Main.java startup.run() ->
2. Startup.java run() > ProjectActions.doNew(...) ->
3. ProjectActions.java doNew(...) > completeProject(...) > new CreateFrame(...) (RUNNABLE) > Frame(newProject) ->
4. Frame.java Canvas(proj) ->
5. Canvas.java paintThread.start() > CanvasPaintThread Thread ->
6. Frame.java (everything else in constructor finishes) ->
7. ProjectActions.java CrateFrame run() (everything else in there finishes) ->
8. (we have a running CanvasPaintThread)

## TODOS:
- [x] Setup jre, jdk, Intellij on work PC
- [x] Setup several GitHub Account access on work PC
- [x] Import missing external libraries to libs folder
- [x] Fix any outstanding errors
- [x] Figure out how to and compile the code and COMPILE
- [x] Prune any other unnecessary stuff from the repo
- [x] Explore how is it advised to introduce new changes in the guide
- [x] NEW: Figure out how many threads are running during the programs lifespan
- [x] (FIRST BIG CHANGE) Add a folder where quantum logic gates will be stored
- [x] Introduction of complex numbers (add new external package?)
- [x] Define which exact gates will be implemented
- [x] Add all components needed for this project (qubits, gates, etc.)
- [x] Explore how the engine works right now to gauge work needed to be done
- [x] Implement qubit (make sure IDs are included)
- [x] Implement new value quantum components will output
- [x] Implement Identity
- [x] Make sure the values are passed through quantum components properly
- [x] Implement all the Quantum Gates
- [x] Change the icons to be less ugly...
- [x] Implement logic of the Measurement gate
- [x] Make sure measurement gate checks whether all entangled qubits are passed together
- [ ] Calculate collapsed qubits
- [ ] Swap Gate
- [ ] Remove Qubit ID functionality
- [ ] Remove QuantumValue debug printing

## IF THERE'S ENOUGH TIME:
- [ ] Add single qubit "measuring" - checking their complex vector state at a certain point
package com.cburch.logisim.std.quantum;

import java.util.Arrays;
import java.util.List;

import com.cburch.logisim.tools.AddTool;
import com.cburch.logisim.tools.Library;
import com.cburch.logisim.tools.Tool;

public class Quantum extends Library {
    private final List<Tool> tools;

    public Quantum() {
        tools = Arrays.asList(new Tool[] {
                new AddTool(Qubit.FACTORY),
                new AddTool(IdentityGate.FACTORY),
                new AddTool(ControlGate.FACTORY),
                new AddTool(PauliXGate.FACTORY),
                new AddTool(PauliYGate.FACTORY),
                new AddTool(PauliZGate.FACTORY),
                new AddTool(HadamardGate.FACTORY),
                new AddTool(PhaseSGate.FACTORY),
                new AddTool(PhaseTGate.FACTORY),
                new AddTool(PhaseGate.FACTORY),
                new AddTool(RxGate.FACTORY),
                new AddTool(RyGate.FACTORY),
                new AddTool(RzGate.FACTORY),
                new AddTool(MeasurementGate.FACTORY)
        });
    }

    @Override
    public String getName() { return "Quantum"; }

    @Override
    public String getDisplayName() { return Strings.get("quantumLibrary"); }

    @Override
    public List<Tool> getTools() { return tools; }
}

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
            new AddTool(IdentityGate.FACTORY)
        });
    }

    @Override
    public String getName() { return "Quantum"; }

    @Override
    public String getDisplayName() { return Strings.get("quantumLibrary"); }

    @Override
    public List<Tool> getTools() { return tools; }
}

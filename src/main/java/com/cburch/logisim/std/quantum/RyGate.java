package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class RyGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new RyGate();

    private RyGate() {
        super("Ry Gate", Strings.getter("ryGateComponent"));
    }
}

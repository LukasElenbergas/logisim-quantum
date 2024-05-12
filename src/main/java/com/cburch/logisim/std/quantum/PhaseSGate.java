package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class PhaseSGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PhaseSGate();

    private PhaseSGate() {
        super("S Gate", Strings.getter("sGateComponent"));
    }
}

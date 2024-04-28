package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class PhaseRGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PhaseRGate();

    private PhaseRGate() {
        super("Phase (R) Gate", Strings.getter("rGateComponent"));
    }
}

package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class PhaseTGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PhaseTGate();

    private PhaseTGate() {
        super("T Gate", Strings.getter("tGateComponent"));
    }
}

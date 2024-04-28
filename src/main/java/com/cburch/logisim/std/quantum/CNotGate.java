package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class CNotGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new CNotGate();

    private CNotGate() {
        super("CNOT Gate", Strings.getter("cnotGateComponent"));
    }
}

package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class PauliZGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PauliZGate();

    private PauliZGate() {
        super("Pauli-Z Gate", Strings.getter("paulizGateComponent"));
    }
}

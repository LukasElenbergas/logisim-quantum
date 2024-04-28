package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class PauliYGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PauliYGate();

    private PauliYGate() {
        super("Pauli-Y Gate", Strings.getter("pauliyGateComponent"));
    }
}

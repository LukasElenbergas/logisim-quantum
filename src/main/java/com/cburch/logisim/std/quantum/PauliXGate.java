package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class PauliXGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PauliXGate();

    private PauliXGate() {
        super("Pauli-X Gate", Strings.getter("paulixGateComponent"));
    }
}

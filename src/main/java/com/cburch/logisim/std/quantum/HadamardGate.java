package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class HadamardGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new HadamardGate();

    private HadamardGate() {
        super("Hadamard Gate", Strings.getter("hadamardGateComponent"));
    }

}

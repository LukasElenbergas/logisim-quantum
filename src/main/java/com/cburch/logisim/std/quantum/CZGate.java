package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class CZGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new CZGate();

    private CZGate() {
        super("CZ Gate", Strings.getter("czGateComponent"));
    }
}

package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class ToffoliGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new ToffoliGate();

    private ToffoliGate() {
        super("Toffoli Gate", Strings.getter("toffoliGateComponent"));
    }
}

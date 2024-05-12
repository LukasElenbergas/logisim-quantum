package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class RxGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new RxGate();

    private RxGate() {
        super("Rx Gate", Strings.getter("rxGateComponent"));
    }
}

package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class RzGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new RzGate();

    private RzGate() {
        super("Rz Gate", Strings.getter("rzGateComponent"));
    }
}

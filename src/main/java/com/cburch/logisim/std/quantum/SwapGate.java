package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.InstanceFactory;

class SwapGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new SwapGate();

    private SwapGate() {
        super("Swap Gate", Strings.getter("swapGateComponent"));
    }
}

package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.InstanceFactory;

class IdentityGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new IdentityGate();

    private IdentityGate() {
        super("Identity Gate", Strings.getter("identityGateComponent"));
    }
}

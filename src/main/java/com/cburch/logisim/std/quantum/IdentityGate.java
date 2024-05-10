package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.InstanceFactory;
import com.cburch.logisim.instance.InstanceState;
import com.cburch.logisim.util.Icons;

class IdentityGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new IdentityGate();

    private IdentityGate() {
        super("Identity Gate",
                Strings.getter("identityGateComponent"),
                Icons.getIcon("identityGate.gif"),
                "I");
    }

    @Override
    public void propagate(InstanceState state) { }
}

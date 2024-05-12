package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
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
    public void propagate(InstanceState state) {
        Value in = state.getPort(1);

        if (in.isQuantum()) {
            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "I"));

            state.setPort(0, out, 1);
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

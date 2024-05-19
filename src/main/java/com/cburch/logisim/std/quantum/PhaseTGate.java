package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class PhaseTGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PhaseTGate();

    private PhaseTGate() {
        super("T Gate",
                Strings.getter("tGateComponent"),
                Icons.getIcon("tGate.gif"),
                "T");
    }

    @Override
    public void propagate(InstanceState state) {
        Value in = state.getPort(1);

        if (in.isQuantum()) {
            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "T"));

            state.setPort(0, out, 1);
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

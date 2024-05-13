package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class PhaseSGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PhaseSGate();

    private PhaseSGate() {
        super("S Gate",
                Strings.getter("sGateComponent"),
                Icons.getIcon("sGate.gif"),
                "S");
    }

    @Override
    public void propagate(InstanceState state) {
        Value in = state.getPort(1);

        if (in.isQuantum()) {
            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "S"));

            state.setPort(0, out, 1);
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

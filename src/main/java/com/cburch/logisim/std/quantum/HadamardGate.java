package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class HadamardGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new HadamardGate();

    private HadamardGate() {
        super("Hadamard Gate",
                Strings.getter("hadamardGateComponent"),
                Icons.getIcon("hadamardGate.gif"),
                "H");
    }

    @Override
    public void propagate(InstanceState state) {
        Value in = state.getPort(1);

        if (in.isQuantum()) {
            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "H"));

            state.setPort(0, out, 1);
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

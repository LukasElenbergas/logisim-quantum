package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class PauliYGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PauliYGate();

    private PauliYGate() {
        super("Pauli-Y Gate",
                Strings.getter("pauliyGateComponent"),
                Icons.getIcon("yGate.gif"),
                "Y");
    }

    @Override
    public void propagate(InstanceState state) {
        Value in = state.getPort(1);

        if (in.isQuantum()) {
            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "Y"));

            state.setPort(0, out, 1);
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

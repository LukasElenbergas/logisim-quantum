package com.cburch.logisim.std.quantum;

import java.util.Objects;

import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class PhaseRGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PhaseRGate();

    private PhaseRGate() {
        super("Phase (R) Gate",
                Strings.getter("rGateComponent"),
                Icons.getIcon("rGate.gif"),
                "R",
                true);
    }

    @Override
    public void propagate(InstanceState state) {
        String radians = state.getAttributeValue(StdAttr.RADIANS);
        Value in = state.getPort(1);

        if (!Objects.equals(radians, "") && in.isQuantum()) {
            String instruction = "R(" + radians + ")";

            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, instruction));

            state.setPort(0, out, 1);
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

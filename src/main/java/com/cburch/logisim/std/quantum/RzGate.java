package com.cburch.logisim.std.quantum;

import java.util.Objects;

import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class RzGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new RzGate();

    private RzGate() {
        super("Rz Gate",
                Strings.getter("rzGateComponent"),
                Icons.getIcon("rzGate.gif"),
                "Rz",
                true);
    }

    @Override
    public void propagate(InstanceState state) {
        String degrees = state.getAttributeValue(StdAttr.THETA);
        Value in = state.getPort(1);

        if (!Objects.equals(degrees, "") && in.isQuantum()) {
            String instruction = "Rz" + degrees;

            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, instruction));

            state.setPort(0, out, 1);
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

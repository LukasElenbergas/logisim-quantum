package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.Direction;
import com.cburch.logisim.data.Location;
import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class PauliZGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new PauliZGate();

    private PauliZGate() {
        super("Pauli-Z Gate",
                Strings.getter("paulizGateComponent"),
                Icons.getIcon("zGate.gif"),
                "Z");
    }

    @Override
    public void configurePorts(Instance instance) {
        Direction facing = instance.getAttributeValue(StdAttr.FACING);
        Port[] ports = new Port[3];
        ports[0] = new Port(0, 0, Port.OUTPUT, StdAttr.WIDTH);
        Location out = Location.create(0, 0).translate(facing, -20);
        ports[1] = new Port(out.getX(), out.getY(), Port.INPUT, StdAttr.WIDTH);
        Location top = Location.create(0, 0).translate(facing, -10, -10);
        ports[2] = new Port(top.getX(), top.getY(), Port.INPUT, StdAttr.WIDTH);
        instance.setPorts(ports);
    }

    @Override
    public void propagate(InstanceState state) {
        Value in = state.getPort(1);
        Value top = state.getPort(2);

        if (in.isQuantum() && top != Value.ERROR) {

            if (top == Value.UNKNOWN) {
                Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "Z"));

                state.setPort(0, out, 1);

            } else if (top.isQuantum()) {
                Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "CZ"));

                state.setPort(0, out, 1);
            }
        } else {
            state.setPort(0, Value.ERROR, 1);
        }
    }
}

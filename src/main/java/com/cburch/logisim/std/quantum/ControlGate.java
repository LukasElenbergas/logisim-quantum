package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.Direction;
import com.cburch.logisim.data.Location;
import com.cburch.logisim.data.QuantumValue;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class ControlGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new ControlGate();

    private ControlGate() {
        super("Control Gate",
                Strings.getter("controlGateComponent"),
                Icons.getIcon("controlGate.gif"),
                "C");
    }

    @Override
    public void configurePorts(Instance instance) {
        Direction facing = instance.getAttributeValue(StdAttr.FACING);
        Port[] ports = new Port[4];
        ports[0] = new Port(0, 0, Port.OUTPUT, StdAttr.WIDTH);
        Location out = Location.create(0, 0).translate(facing, -20);
        ports[1] = new Port(out.getX(), out.getY(), Port.INPUT, StdAttr.WIDTH);
        Location top = Location.create(0, 0).translate(facing, -10, -10);
        ports[2] = new Port(top.getX(), top.getY(), Port.INPUT, StdAttr.WIDTH);
        Location bot = Location.create(0, 0).translate(facing, -10, 10);
        ports[3] = new Port(bot.getX(), bot.getY(), Port.OUTPUT, StdAttr.WIDTH);
        instance.setPorts(ports);
    }

    @Override
    public void propagate(InstanceState state) {
        Value in = state.getPort(1);
        Value top = state.getPort(2);

        if (in.isQuantum() && top == Value.UNKNOWN) {
            Value out = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "C"));

            state.setPort(0, out, 1);
            state.setPort(3, out, 1);

        } else if (in.isQuantum() && top.isQuantum() && !top.qVal.instructions.getLast().equals("CC")) {

            Value outRight = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "C"));
            state.setPort(0, outRight, 1);

            Value outBot = new Value(Value.QUANTUM, new QuantumValue(in.qVal, "CC"));
            state.setPort(3, outBot, 1);

        } else {
            state.setPort(0, Value.ERROR, 1);
            state.setPort(3, Value.ERROR, 1);
        }
    }
}

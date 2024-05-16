package com.cburch.logisim.std.quantum;

import com.cburch.logisim.data.*;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.Icons;

class MeasurementGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new MeasurementGate();

    private MeasurementGate() {
        super("Measurement Gate",
                Strings.getter("measurementComponent"),
                Icons.getIcon("measurementGate.gif"),
                "M");
    }

    @Override
    public AttributeSet createAttributeSet() { return new QuantumGateAttributes("Measure"); }

    //
    // graphics methods
    //
    @Override
    public Bounds getOffsetBounds(AttributeSet attrs) {
        Direction facing = attrs.getValue(StdAttr.FACING);
        Integer qubits = attrs.getValue(StdAttr.NUM_QUBITS);

        int width = 20;
        int height = qubits == 1 ? 20 : (qubits * 2 - 1) * 20;

        if (facing == Direction.SOUTH) return Bounds.create(-10, -20, height, width);
        if (facing == Direction.NORTH) return Bounds.create(-10, 0, height, width);
        if (facing == Direction.WEST) return Bounds.create(0, -10, width, height);
        return Bounds.create(-20, -10, width, height);
    }

    @Override
    public void configurePorts(Instance instance) {
        Direction facing = instance.getAttributeValue(StdAttr.FACING);
        Integer qubits = instance.getAttributeValue(StdAttr.NUM_QUBITS);

        int totalPorts = qubits * 2 + 1;
        Port[] ports = new Port[totalPorts];

        // Top port is for control: whether to measure or not
        Location top = Location.create(0, 0).translate(facing, -10, -10);
        ports[0] = new Port(top.getX(), top.getY(), Port.INPUT, StdAttr.WIDTH);

        int right = 0;
        for (int i = 1; i < totalPorts; i++) {
            if ((i % 2) != 0) { // Odd ports are inputs (QUBITS)
                Location in = Location.create(0, 0).translate(facing, -20, right);
                ports[i] = new Port(in.getX(), in.getY(), Port.INPUT, StdAttr.WIDTH);
            } else { // Even ports are outputs (BITS)
                Location out = Location.create(0, 0).translate(facing, 0, right);
                ports[i] = new Port(out.getX(), out.getY(), Port.OUTPUT, StdAttr.WIDTH);
                right += 40;
            }
        }

        instance.setPorts(ports);
    }

    @Override
    public void propagate(InstanceState state) {
        Integer qubits = state.getAttributeValue(StdAttr.NUM_QUBITS);
        Value top = state.getPort(0);
        Value[] inputs = new Value[qubits];
        Value[] outputs = new Value[qubits];
        boolean allQubitsQuantum = true;

        // GET INPUTS AND CONNECTION STATUS
        int index = 0;
        for (int i = 1; i < qubits * 2 + 1; i++) {
            if ((i % 2) != 0) { // Odd ports are inputs (QUBITS)
                inputs[index] = state.getPort(i);
                allQubitsQuantum = allQubitsQuantum == inputs[index].isQuantum();
                index++;
            }
        }

        if (!allQubitsQuantum || top == Value.ERROR || top == Value.UNKNOWN) {
            // SET OUTPUTS TO ERROR WHEN TOP CONTROL IS ERROR/ UNKNOWN OR NOT ALL INPUTS ARE QUANTUM
            for (int i = 1; i < qubits * 2 + 1; i++) {
                if ((i % 2) == 0) { // Even ports are outputs (BITS)
                    state.setPort(i, Value.ERROR, 1);
                }
            }
        } else {

            if (top == Value.FALSE) {
                // SET OUTPUTS TO UNKNOWN WHEN TOP CONTROL IS FALSE
                for (int i = 1; i < qubits * 2 + 1; i++) {
                    if ((i % 2) == 0) { // Even ports are outputs (BITS)
                        state.setPort(i, Value.UNKNOWN, 1);
                    }
                }
            } else if (top == Value.TRUE) {
                // MAIN LOGIC GOES HERE SINCE

            }
        }
    }
}

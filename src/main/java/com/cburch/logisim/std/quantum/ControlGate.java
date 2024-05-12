package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class ControlGate extends InstanceFactory {
    public static InstanceFactory FACTORY = new ControlGate();

    private ControlGate() {
        super("Control Gate", Strings.getter("controlGateComponent"));
    }

    @Override
    public void paintInstance(InstancePainter painter) {

    }

    @Override
    public void propagate(InstanceState state) {

    }
}

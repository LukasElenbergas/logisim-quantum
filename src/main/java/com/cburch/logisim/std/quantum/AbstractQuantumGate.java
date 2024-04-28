package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;
import com.cburch.logisim.util.StringGetter;

abstract class AbstractQuantumGate extends InstanceFactory {
    protected AbstractQuantumGate(String name, StringGetter desc) {
        super(name, desc);
    }

    @Override
    public void paintInstance(InstancePainter painter) {

    }

    @Override
    public void propagate(InstanceState state) {

    }
}

package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class IdentityGate extends InstanceFactory{
    public static InstanceFactory FACTORY = new IdentityGate();

    private IdentityGate() {
        super("Identity Gate", Strings.getter("identityGateComponent"));
    }

    @Override
    public void paintInstance(InstancePainter painter) {

    }

    @Override
    public void propagate(InstanceState state) {

    }
}

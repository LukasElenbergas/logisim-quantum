package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class Qubit extends InstanceFactory {
    public static InstanceFactory FACTORY = new Qubit();

    private Qubit() {
        super("Qubit", Strings.getter("qubitComponent"));
    }

    @Override
    public void paintInstance(InstancePainter painter) {

    }

    @Override
    public void propagate(InstanceState state) {

    }
}

package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class MeasurementGate extends InstanceFactory {
    public static InstanceFactory FACTORY = new MeasurementGate();

    private MeasurementGate() {
        super("Measurement Gate", Strings.getter("measurementComponent"));
    }

    @Override
    public void paintInstance(InstancePainter painter) {

    }

    @Override
    public void propagate(InstanceState state) {

    }
}

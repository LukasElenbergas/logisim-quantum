package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class MeasurementGate extends AbstractQuantumGate {
    public static InstanceFactory FACTORY = new MeasurementGate();

    private MeasurementGate() {
        super("Measurement Gate", Strings.getter("measurementComponent"));
    }
}

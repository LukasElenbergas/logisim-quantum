package com.cburch.logisim.std.quantum;

import com.cburch.logisim.instance.*;

class Control extends InstanceFactory {
    public static InstanceFactory FACTORY = new Control();

    private Control() {
        super("Control", Strings.getter("controlComponent"));
    }

    @Override
    public void paintInstance(InstancePainter painter) {

    }

    @Override
    public void propagate(InstanceState state) {

    }
}

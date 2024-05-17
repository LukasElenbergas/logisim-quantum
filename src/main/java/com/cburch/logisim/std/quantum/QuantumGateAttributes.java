package com.cburch.logisim.std.quantum;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import com.cburch.logisim.data.AbstractAttributeSet;
import com.cburch.logisim.data.Attribute;
import com.cburch.logisim.data.BitWidth;
import com.cburch.logisim.data.Direction;
import com.cburch.logisim.instance.StdAttr;
import com.cburch.logisim.std.wiring.Pin;

class QuantumGateAttributes extends AbstractAttributeSet {
    public static QubitAttributes instance = new QubitAttributes();

    private List<Attribute<?>> ATTRIBUTES
            = Arrays.asList(StdAttr.FACING, StdAttr.LABEL, Pin.ATTR_LABEL_LOC, StdAttr.LABEL_FONT);

    Direction facing = Direction.EAST;
    Integer qubits = 1;
    BitWidth width = BitWidth.ONE;
    String label = "";
    String rads = "";
    Direction labelloc = Direction.EAST;
    Font labelfont = StdAttr.DEFAULT_LABEL_FONT;

    public QuantumGateAttributes(boolean radsNeeded) {
        if (radsNeeded) this.ATTRIBUTES.set(1, StdAttr.DEGREES);
    }

    public QuantumGateAttributes(String measurement) {
        if (measurement.equals("Measure")) {
            this.ATTRIBUTES = Arrays.asList(
                    StdAttr.FACING, StdAttr.NUM_QUBITS, StdAttr.LABEL, Pin.ATTR_LABEL_LOC, StdAttr.LABEL_FONT
            );
        }
    }

    @Override
    protected void copyInto(AbstractAttributeSet dest) { }

    @Override
    public List<Attribute<?>> getAttributes() { return ATTRIBUTES; }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E getValue(Attribute<E> attr) {
        if (attr == StdAttr.FACING) return (E) facing;
        if (attr == StdAttr.NUM_QUBITS) return (E) qubits;
        if (attr == StdAttr.WIDTH) return (E) width;
        if (attr == StdAttr.LABEL) return (E) label;
        if (attr == StdAttr.DEGREES) return (E) rads;
        if (attr == Pin.ATTR_LABEL_LOC) return (E) labelloc;
        if (attr == StdAttr.LABEL_FONT) return (E) labelfont;
        return null;
    }

    @Override
    public <V> void setValue(Attribute<V> attr, V value) {
        if (attr == StdAttr.FACING) {
            facing = (Direction) value;
        } else if (attr == StdAttr.NUM_QUBITS) {
            qubits = (Integer) value;
        } else if (attr == StdAttr.WIDTH) {
            width = (BitWidth) value;
        } else if (attr == StdAttr.LABEL) {
            label = (String) value;
        } else if (attr == StdAttr.DEGREES) {
            rads = (String) value;
        } else if (attr == Pin.ATTR_LABEL_LOC) {
            labelloc = (Direction) value;
        } else if (attr == StdAttr.LABEL_FONT) {
            labelfont = (Font) value;
        } else {
            throw new IllegalArgumentException("unknown attribute");
        }
        fireAttributeValueChanged(attr, value);
    }
}

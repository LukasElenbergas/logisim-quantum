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

class QubitAttributes extends AbstractAttributeSet {
    public static QubitAttributes instance = new QubitAttributes();

    private static final List<Attribute<?>> ATTRIBUTES
            = Arrays.asList(StdAttr.FACING, StdAttr.QUBIT_ID, Pin.ATTR_LABEL_LOC, StdAttr.LABEL_FONT);

    Direction facing = Direction.EAST;
    BitWidth width = BitWidth.ONE;
    String id = "0";
    Direction labelloc = Direction.EAST;
    Font labelfont = StdAttr.DEFAULT_LABEL_FONT;

    public QubitAttributes() { }

    @Override
    protected void copyInto(AbstractAttributeSet dest) { }

    @Override
    public List<Attribute<?>> getAttributes() { return ATTRIBUTES; }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E getValue(Attribute<E> attr) {
        if (attr == StdAttr.FACING) return (E) facing;
        if (attr == StdAttr.WIDTH) return (E) width;
        if (attr == StdAttr.QUBIT_ID) return (E) id;
        if (attr == Pin.ATTR_LABEL_LOC) return (E) labelloc;
        if (attr == StdAttr.LABEL_FONT) return (E) labelfont;
        return null;
    }

    @Override
    public <V> void setValue(Attribute<V> attr, V value) {
        if (attr == StdAttr.FACING) {
            facing = (Direction) value;
        } else if (attr == StdAttr.WIDTH) {
            width = (BitWidth) value;
        } else if (attr == StdAttr.QUBIT_ID) {
            id = (String) value;
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

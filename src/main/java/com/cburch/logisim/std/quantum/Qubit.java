package com.cburch.logisim.std.quantum;

import java.awt.*;
import javax.swing.*;

import com.cburch.logisim.data.*;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.tools.key.BitWidthConfigurator;
import com.cburch.logisim.util.Icons;

class Qubit extends InstanceFactory {

    public static InstanceFactory FACTORY = new Qubit();

    private static final Icon ICON = Icons.getIcon("qubit.gif");
    private static final Font ICON_WIDTH_FONT = new Font("SansSerif", Font.BOLD, 9);
    private static final Color ICON_WIDTH_COLOR = Value.WIDTH_ERROR_COLOR.darker();

    private Qubit() {

        super("Qubit", Strings.getter("qubitComponent"));
        setFacingAttribute(StdAttr.FACING);
        setKeyConfigurator(new BitWidthConfigurator(StdAttr.WIDTH));
    }

    @Override
    public Bounds getOffsetBounds(AttributeSet attrs) {
        Direction facing = attrs.getValue(StdAttr.FACING);
        if (facing == Direction.SOUTH) return Bounds.create(-10, -20, 20, 20);
        if (facing == Direction.NORTH) return Bounds.create(-10, 0, 20, 20);
        if (facing == Direction.WEST) return Bounds.create(0, -10, 20, 20);
        return Bounds.create(-20, -10, 20, 20);
    }

    //
    // graphics methods
    //
    @Override
    public void paintIcon(InstancePainter painter) {
        Graphics g = painter.getGraphics();
        ICON.paintIcon(painter.getDestination(), g, 2, 2);
    }

    @Override
    public void paintGhost(InstancePainter painter) {

    }

    @Override
    public void paintInstance(InstancePainter painter) {

    }

    @Override
    public void propagate(InstanceState state) {

    }
}

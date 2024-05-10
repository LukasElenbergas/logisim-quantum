package com.cburch.logisim.std.quantum;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

import com.cburch.logisim.comp.TextField;
import com.cburch.logisim.data.*;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.tools.key.BitWidthConfigurator;
import com.cburch.logisim.tools.key.DirectionConfigurator;
import com.cburch.logisim.tools.key.JoinedConfigurator;
import com.cburch.logisim.util.GraphicsUtil;
import com.cburch.logisim.util.Icons;

class Qubit extends InstanceFactory {
    public static final Attribute<Direction> ATTR_LABEL_LOC
            = Attributes.forDirection("labelloc", Strings.getter("pinLabelLocAttr"));

    public static InstanceFactory FACTORY = new Qubit();

    private static final Icon ICON = Icons.getIcon("qubit.gif");

    private Qubit() {
        super("Qubit", Strings.getter("qubitComponent"));
        setFacingAttribute(StdAttr.FACING);
        setKeyConfigurator(JoinedConfigurator.create(
                new BitWidthConfigurator(StdAttr.WIDTH),
                new DirectionConfigurator(ATTR_LABEL_LOC, KeyEvent.ALT_DOWN_MASK)));
    }

    @Override
    public AttributeSet createAttributeSet() { return new QubitAttributes(); }

    //
    // graphics methods
    //
    @Override
    public Bounds getOffsetBounds(AttributeSet attrs) {
        Direction facing = attrs.getValue(StdAttr.FACING);
        if (facing == Direction.SOUTH) return Bounds.create(-10, -20, 20, 20);
        if (facing == Direction.NORTH) return Bounds.create(-10, 0, 20, 20);
        if (facing == Direction.WEST) return Bounds.create(0, -10, 20, 20);
        return Bounds.create(-20, -10, 20, 20);
    }

    @Override
    public void paintIcon(InstancePainter painter) {
        Graphics g = painter.getGraphics();
        ICON.paintIcon(painter.getDestination(), g, 2, 2);
    }

    @Override
    public void paintGhost(InstancePainter painter) {
        Location loc = painter.getLocation();
        Bounds bds = painter.getOffsetBounds();
        int x = loc.getX();
        int y = loc.getY();
        Graphics g = painter.getGraphics();
        GraphicsUtil.switchToWidth(g, 2);
        g.drawRect(x + bds.getX() + 1, y + bds.getY() + 1,
                bds.getWidth() - 1, bds.getHeight() - 1);
    }

    @Override
    public void paintInstance(InstancePainter painter) {
        Graphics g = painter.getGraphics();
        Bounds bds = painter.getInstance().getBounds();
        int x = bds.getX();
        int y = bds.getY();
        GraphicsUtil.switchToWidth(g, 2);
        g.setColor(Color.black);
        g.drawRect(x + 1, y + 1, bds.getWidth() - 1, bds.getHeight() - 1);

        painter.drawLabel();

        if (!painter.getShowState()) {
            g.setColor(Color.BLACK);
            GraphicsUtil.drawCenteredText(g, "x" + 1,
                    bds.getX() + bds.getWidth() / 2, bds.getY() + bds.getHeight() / 2);
        } else {
            g.setColor(Value.QUANTUM_COLOR);
            g.fillOval(x + 4, y + 4, 13, 13);
            g.setColor(Color.WHITE);
            GraphicsUtil.drawCenteredText(g, "q", x + 11, y + 8);
        }

        painter.drawPorts();
    }

    //
    // methods for instances
    //
    @Override
    protected void configureNewInstance(Instance instance) {
        QubitAttributes attrs = (QubitAttributes) instance.getAttributeSet();
        instance.addAttributeListener();
        configurePorts(instance);
        configureLabel(instance, attrs.labelloc, attrs.facing);
    }

    @Override
    protected void instanceAttributeChanged(Instance instance, Attribute<?> attr) {
        QubitAttributes attrs = (QubitAttributes) instance.getAttributeSet();
        instance.recomputeBounds();
        configurePorts(instance);
        configureLabel(instance, attrs.labelloc, attrs.facing);
    }

    private void configurePorts(Instance instance) {
        Direction facing = instance.getAttributeValue(StdAttr.FACING);
        Port[] ports = new Port[2];
        ports[0] = new Port(0, 0, Port.OUTPUT, StdAttr.WIDTH);
        Location out = Location.create(0, 0).translate(facing, -20);
        ports[1] = new Port(out.getX(), out.getY(), Port.INPUT, StdAttr.WIDTH);
        instance.setPorts(ports);
    }

    static void configureLabel(Instance instance, Direction labelLoc, Direction facing) {
        Bounds bds = instance.getBounds();
        int x, y, halign, valign;

        if (labelLoc == Direction.NORTH) {
            halign = TextField.H_CENTER;
            valign = TextField.V_BOTTOM;
            x = bds.getX() + bds.getWidth() / 2;
            y = bds.getY() - 2;
            if (facing == labelLoc) {
                halign = TextField.H_LEFT;
                x += 2;
            }
        } else if (labelLoc == Direction.SOUTH) {
            halign = TextField.H_CENTER;
            valign = TextField.V_TOP;
            x = bds.getX() + bds.getWidth() / 2;
            y = bds.getY() + bds.getHeight() + 2;
            if (facing == labelLoc) {
                halign = TextField.H_LEFT;
                x += 2;
            }
        } else if (labelLoc == Direction.EAST) {
            halign = TextField.H_LEFT;
            valign = TextField.V_CENTER;
            x = bds.getX() + bds.getWidth() + 2;
            y = bds.getY() + bds.getHeight() / 2;
            if (facing == labelLoc) {
                valign = TextField.V_BOTTOM;
                y -= 2;
            }
        } else { // WEST
            halign = TextField.H_RIGHT;
            valign = TextField.V_CENTER;
            x = bds.getX() - 2;
            y = bds.getY() + bds.getHeight() / 2;
            if (facing == labelLoc) {
                valign = TextField.V_BOTTOM;
                y -= 2;
            }
        }

        instance.setTextField(StdAttr.QUBIT_ID, StdAttr.LABEL_FONT, x, y, halign, valign);
    }

    @Override
    public void propagate(InstanceState state) {
        String idString = state.getAttributeValue(StdAttr.QUBIT_ID);
        Value in = state.getPort(1);

        if (Objects.equals(idString, "") || in == Value.ERROR) {
            state.setPort(0, Value.ERROR, 1);
        } else {
            int id = Integer.parseInt(idString);
            int bit = in == Value.TRUE ? 1 : 0;

            QuantumValue qVal = new QuantumValue(id, bit, new ArrayList<>());

            state.setPort(0, Value.QUANTUM, 1);

        }
    }
}

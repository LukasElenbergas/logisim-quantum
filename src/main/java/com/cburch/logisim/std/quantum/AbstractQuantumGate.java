package com.cburch.logisim.std.quantum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

import com.cburch.logisim.comp.TextField;
import com.cburch.logisim.data.*;
import com.cburch.logisim.instance.*;
import com.cburch.logisim.tools.key.BitWidthConfigurator;
import com.cburch.logisim.tools.key.DirectionConfigurator;
import com.cburch.logisim.tools.key.JoinedConfigurator;
import com.cburch.logisim.util.GraphicsUtil;
import com.cburch.logisim.util.StringGetter;

abstract class AbstractQuantumGate extends InstanceFactory {
    private static final Attribute<Direction> ATTR_LABEL_LOC
            = Attributes.forDirection("labelloc", Strings.getter("pinLabelLocAttr"));

    private final Icon ICON;
    private final String GATE_IDENTIFIER;
    private boolean RADS_NEEDED = false;

    protected AbstractQuantumGate(String name, StringGetter desc, Icon icon, String gate) {
        super(name, desc);
        this.ICON = icon;
        this.GATE_IDENTIFIER = gate;
        setFacingAttribute(StdAttr.FACING);
        setKeyConfigurator(JoinedConfigurator.create(
                new BitWidthConfigurator(StdAttr.WIDTH),
                new DirectionConfigurator(ATTR_LABEL_LOC, KeyEvent.ALT_DOWN_MASK)));
    }

    protected AbstractQuantumGate(String name, StringGetter desc, Icon icon, String gate, boolean rads) {
        this(name, desc, icon, gate);
        this.RADS_NEEDED = rads;
    }

    @Override
    public AttributeSet createAttributeSet() {
        if (!this.RADS_NEEDED) return new QuantumGateAttributes(false);
        else return new QuantumGateAttributes(true);
    }

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

        if (!Objects.equals(this.GATE_IDENTIFIER, "C")) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Tahoma", Font.BOLD, 12));
            GraphicsUtil.drawCenteredText(g, GATE_IDENTIFIER, x + 10, y + 9);
        } else {
            g.setColor(Color.BLACK);
            g.fillOval(x + 7, y + 7, 7, 7);
        }


        painter.drawPorts();
    }

    //
    // methods for instances
    //
    @Override
    protected void configureNewInstance(Instance instance) {
        QuantumGateAttributes attrs = (QuantumGateAttributes) instance.getAttributeSet();
        instance.addAttributeListener();
        configurePorts(instance);
        configureLabel(instance, attrs.labelloc, attrs.facing);
    }

    @Override
    protected void instanceAttributeChanged(Instance instance, Attribute<?> attr) {
        QuantumGateAttributes attrs = (QuantumGateAttributes) instance.getAttributeSet();
        instance.recomputeBounds();
        configurePorts(instance);
        configureLabel(instance, attrs.labelloc, attrs.facing);
    }

    public void configurePorts(Instance instance) {
        Direction facing = instance.getAttributeValue(StdAttr.FACING);
        Port[] ports = new Port[2];
        ports[0] = new Port(0, 0, Port.OUTPUT, StdAttr.WIDTH);
        Location out = Location.create(0, 0).translate(facing, -20);
        ports[1] = new Port(out.getX(), out.getY(), Port.INPUT, StdAttr.WIDTH);
        instance.setPorts(ports);
    }

    void configureLabel(Instance instance, Direction labelLoc, Direction facing) {
        Bounds bds = instance.getBounds();
        int x, y, halign, valign;

        if (labelLoc == Direction.NORTH) {
            halign = com.cburch.logisim.comp.TextField.H_CENTER;
            valign = com.cburch.logisim.comp.TextField.V_BOTTOM;
            x = bds.getX() + bds.getWidth() / 2;
            y = bds.getY() - 2;
            if (facing == labelLoc) {
                halign = com.cburch.logisim.comp.TextField.H_LEFT;
                x += 2;
            }
        } else if (labelLoc == Direction.SOUTH) {
            halign = com.cburch.logisim.comp.TextField.H_CENTER;
            valign = com.cburch.logisim.comp.TextField.V_TOP;
            x = bds.getX() + bds.getWidth() / 2;
            y = bds.getY() + bds.getHeight() + 2;
            if (facing == labelLoc) {
                halign = com.cburch.logisim.comp.TextField.H_LEFT;
                x += 2;
            }
        } else if (labelLoc == Direction.EAST) {
            halign = com.cburch.logisim.comp.TextField.H_LEFT;
            valign = com.cburch.logisim.comp.TextField.V_CENTER;
            x = bds.getX() + bds.getWidth() + 2;
            y = bds.getY() + bds.getHeight() / 2;
            if (facing == labelLoc) {
                valign = com.cburch.logisim.comp.TextField.V_BOTTOM;
                y -= 2;
            }
        } else { // WEST
            halign = com.cburch.logisim.comp.TextField.H_RIGHT;
            valign = com.cburch.logisim.comp.TextField.V_CENTER;
            x = bds.getX() - 2;
            y = bds.getY() + bds.getHeight() / 2;
            if (facing == labelLoc) {
                valign = TextField.V_BOTTOM;
                y -= 2;
            }
        }

        if (!this.RADS_NEEDED) {
            instance.setTextField(StdAttr.LABEL, StdAttr.LABEL_FONT, x, y, halign, valign);
        } else {
            instance.setTextField(StdAttr.DEGREES, StdAttr.LABEL_FONT, x, y, halign, valign);
        }

    }

    @Override
    public void propagate(InstanceState state) { }
}

/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.circuit;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.InputEvent;

import javax.swing.Icon;

import main.java.com.cburch.logisim.LogisimVersion;
import main.java.com.cburch.logisim.circuit.Strings;
import main.java.com.cburch.logisim.comp.AbstractComponentFactory;
import main.java.com.cburch.logisim.comp.Component;
import main.java.com.cburch.logisim.comp.ComponentDrawContext;
import main.java.com.cburch.logisim.data.Attribute;
import main.java.com.cburch.logisim.data.AttributeSet;
import main.java.com.cburch.logisim.data.Bounds;
import main.java.com.cburch.logisim.data.Location;
import main.java.com.cburch.logisim.instance.StdAttr;
import main.java.com.cburch.logisim.tools.key.BitWidthConfigurator;
import main.java.com.cburch.logisim.tools.key.IntegerConfigurator;
import main.java.com.cburch.logisim.tools.key.JoinedConfigurator;
import main.java.com.cburch.logisim.tools.key.KeyConfigurator;
import main.java.com.cburch.logisim.tools.key.ParallelConfigurator;
import main.java.com.cburch.logisim.util.Icons;
import main.java.com.cburch.logisim.util.StringGetter;

public class SplitterFactory extends AbstractComponentFactory {
	public static final SplitterFactory instance = new SplitterFactory();

	private static final Icon toolIcon = Icons.getIcon("splitter.gif");

	private SplitterFactory() { }

	@Override
	public String getName() { return "Splitter"; }

	@Override
	public StringGetter getDisplayGetter() {
		return Strings.getter("splitterComponent");
	}

	@Override
	public AttributeSet createAttributeSet() {
		return new SplitterAttributes();
	}
	
	@Override
	public Object getDefaultAttributeValue(Attribute<?> attr, LogisimVersion ver) {
		if (attr == SplitterAttributes.ATTR_APPEARANCE) {
			if (ver.compareTo(LogisimVersion.get(2, 6, 3, 202)) < 0) {
				return SplitterAttributes.APPEAR_LEGACY;
			} else {
				return SplitterAttributes.APPEAR_LEFT;
			}
		} else if (attr instanceof SplitterAttributes.BitOutAttribute) {
			SplitterAttributes.BitOutAttribute a;
			a = (SplitterAttributes.BitOutAttribute) attr;
			return a.getDefault();
		} else {
			return super.getDefaultAttributeValue(attr, ver);
		}
	}

	@Override
	public Component createComponent(Location loc, AttributeSet attrs) {
		return new Splitter(loc, attrs);
	}

	@Override
	public Bounds getOffsetBounds(AttributeSet attrsBase) {
		SplitterAttributes attrs = (SplitterAttributes) attrsBase;
		int fanout = attrs.fanout;
		SplitterParameters parms = attrs.getParameters();
		int xEnd0 = parms.getEnd0X();
		int yEnd0 = parms.getEnd0Y();
		Bounds bds = Bounds.create(0, 0, 1, 1);
		bds = bds.add(xEnd0, yEnd0);
		bds = bds.add(xEnd0 + (fanout - 1) * parms.getEndToEndDeltaX(),
				yEnd0 + (fanout - 1) * parms.getEndToEndDeltaY());
		return bds;
	}

	//
	// user interface methods
	//
	@Override
	public void drawGhost(ComponentDrawContext context,
			Color color, int x, int y, AttributeSet attrsBase) {
		SplitterAttributes attrs = (SplitterAttributes) attrsBase;
		context.getGraphics().setColor(color);
		Location loc = Location.create(x, y);
		if (attrs.appear == SplitterAttributes.APPEAR_LEGACY) {
			SplitterPainter.drawLegacy(context, attrs, loc);
		} else {
			SplitterPainter.drawLines(context, attrs, loc);
		}
	}

	@Override
	public void paintIcon(ComponentDrawContext c,
			int x, int y, AttributeSet attrs) {
		Graphics g = c.getGraphics();
		if (toolIcon != null) {
			toolIcon.paintIcon(c.getDestination(), g, x + 2, y + 2);
		}
	}

	@Override
	public Object getFeature(Object key, AttributeSet attrs) {
		if (key == FACING_ATTRIBUTE_KEY) {
			return StdAttr.FACING;
		} else if (key == KeyConfigurator.class) {
			KeyConfigurator altConfig = ParallelConfigurator.create(
					new BitWidthConfigurator(SplitterAttributes.ATTR_WIDTH),
					new IntegerConfigurator(SplitterAttributes.ATTR_FANOUT,
							1, 32, InputEvent.ALT_DOWN_MASK));
			return JoinedConfigurator.create(
				new IntegerConfigurator(SplitterAttributes.ATTR_FANOUT, 1, 32, 0),
				altConfig);
		}
		return super.getFeature(key, attrs);
	}
}

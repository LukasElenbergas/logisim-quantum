/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.appear;

import java.util.Collection;

import main.java.com.cburch.draw.canvas.Selection;
import main.java.com.cburch.draw.model.CanvasObject;
import main.java.com.cburch.logisim.circuit.appear.AppearanceElement;

public class AppearanceSelection extends Selection {
	@Override
	public void setMovingShapes(Collection<? extends CanvasObject> shapes, int dx, int dy) {
		if (shouldSnap(shapes)) {
			dx = (dx + 5) / 10 * 10;
			dy = (dy + 5) / 10 * 10;
		}
		super.setMovingShapes(shapes, dx, dy);
	}

	@Override
	public void setMovingDelta(int dx, int dy) {
		if (shouldSnap(getSelected())) {
			dx = (dx + 5) / 10 * 10;
			dy = (dy + 5) / 10 * 10;
		}
		super.setMovingDelta(dx, dy);
	}
	
	private boolean shouldSnap(Collection<? extends CanvasObject> shapes) {
		for (CanvasObject o : shapes) {
			if (o instanceof AppearanceElement) {
				return true;
			}
		}
		return false;
	}
}

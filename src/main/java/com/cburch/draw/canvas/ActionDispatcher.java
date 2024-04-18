/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.draw.canvas;

import main.java.com.cburch.draw.undo.Action;

public interface ActionDispatcher {
	public void doAction(Action action);
}

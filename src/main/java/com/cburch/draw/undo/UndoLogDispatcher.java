/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.draw.undo;

import main.java.com.cburch.draw.canvas.ActionDispatcher;

public class UndoLogDispatcher implements ActionDispatcher {
	private UndoLog log;
	
	public UndoLogDispatcher(UndoLog log) {
		this.log = log;
	}
	
	public void doAction(Action action) {
		log.doAction(action);
	}
}

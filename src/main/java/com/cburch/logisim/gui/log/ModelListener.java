/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.log;

import main.java.com.cburch.logisim.data.Value;

interface ModelListener {
	public void selectionChanged(ModelEvent event);
	public void entryAdded(ModelEvent event, Value[] values);
	public void filePropertyChanged(ModelEvent event);
}

/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.comp;

public interface ComponentListener {
	public void endChanged(ComponentEvent e);
	public void componentInvalidated(ComponentEvent e);
}

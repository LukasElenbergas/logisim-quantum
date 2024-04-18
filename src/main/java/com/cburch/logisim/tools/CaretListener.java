/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.tools;

public interface CaretListener {
	public void editingCanceled(CaretEvent e);
	public void editingStopped(CaretEvent e);
}

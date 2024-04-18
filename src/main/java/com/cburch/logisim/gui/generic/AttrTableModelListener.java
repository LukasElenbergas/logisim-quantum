/* Copyright (c) 2011, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.generic;

public interface AttrTableModelListener {
	public void attrTitleChanged(AttrTableModelEvent event);
	public void attrStructureChanged(AttrTableModelEvent event);
	public void attrValueChanged(AttrTableModelEvent event);
}

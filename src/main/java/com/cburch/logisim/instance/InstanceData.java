/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.instance;

import main.java.com.cburch.logisim.comp.ComponentState;

public interface InstanceData extends ComponentState {
	public Object clone();
}

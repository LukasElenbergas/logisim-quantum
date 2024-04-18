/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.instance;

import main.java.com.cburch.logisim.data.Value;

public abstract class InstanceLogger {
	public Object[] getLogOptions(InstanceState state) { return null; }
	public abstract String getLogName(InstanceState state, Object option);
	public abstract Value getLogValue(InstanceState state, Object option);
}

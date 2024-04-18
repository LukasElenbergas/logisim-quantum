/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.log;

import main.java.com.cburch.logisim.circuit.CircuitState;
import main.java.com.cburch.logisim.data.Value;

public interface Loggable {
	public Object[] getLogOptions(CircuitState state);
	public String getLogName(Object option);
	public Value getLogValue(CircuitState state, Object option);
}

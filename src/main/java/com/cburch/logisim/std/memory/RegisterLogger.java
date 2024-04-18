/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.std.memory;

import main.java.com.cburch.logisim.data.BitWidth;
import main.java.com.cburch.logisim.data.Value;
import main.java.com.cburch.logisim.instance.InstanceLogger;
import main.java.com.cburch.logisim.instance.InstanceState;
import main.java.com.cburch.logisim.instance.StdAttr;

public class RegisterLogger extends InstanceLogger {
	@Override
	public String getLogName(InstanceState state, Object option) {
		String ret = state.getAttributeValue(StdAttr.LABEL);
		return ret != null && !ret.equals("") ? ret : null;
	}

	@Override
	public Value getLogValue(InstanceState state, Object option) {
		BitWidth dataWidth = state.getAttributeValue(StdAttr.WIDTH);
		if (dataWidth == null) dataWidth = BitWidth.create(0);
		RegisterData data = (RegisterData) state.getData();
		if (data == null) return Value.createKnown(dataWidth, 0);
		return Value.createKnown(dataWidth, data.value);
	}
}

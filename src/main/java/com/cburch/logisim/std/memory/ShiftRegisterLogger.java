/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.std.memory;

import main.java.com.cburch.logisim.data.BitWidth;
import main.java.com.cburch.logisim.data.Value;
import main.java.com.cburch.logisim.instance.InstanceLogger;
import main.java.com.cburch.logisim.instance.InstanceState;
import main.java.com.cburch.logisim.instance.StdAttr;

public class ShiftRegisterLogger extends InstanceLogger {
	@Override
	public Object[] getLogOptions(InstanceState state) {
		Integer stages = state.getAttributeValue(ShiftRegister.ATTR_LENGTH);
		Object[] ret = new Object[stages.intValue()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = Integer.valueOf(i);
		}
		return ret;
	}
	
	@Override
	public String getLogName(InstanceState state, Object option) {
		String inName = state.getAttributeValue(StdAttr.LABEL);
		if (inName == null || inName.equals("")) {
			inName = Strings.get("shiftRegisterComponent")
				+ state.getInstance().getLocation();
		}
		if (option instanceof Integer) {
			return inName + "[" + option + "]";
		} else {
			return inName;
		}
	}

	@Override
	public Value getLogValue(InstanceState state, Object option) {
		BitWidth dataWidth = state.getAttributeValue(StdAttr.WIDTH);
		if (dataWidth == null) dataWidth = BitWidth.create(0);
		ShiftRegisterData data = (ShiftRegisterData) state.getData();
		if (data == null) {
			return Value.createKnown(dataWidth, 0);
		} else {
			int index = option == null ? 0 : ((Integer) option).intValue(); 
			return data.get(index);
		}
	}
}

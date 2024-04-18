/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.std.wiring;

import main.java.com.cburch.logisim.data.AttributeSet;
import main.java.com.cburch.logisim.data.BitWidth;
import main.java.com.cburch.logisim.instance.StdAttr;
import main.java.com.cburch.logisim.tools.key.IntegerConfigurator;

class ConstantConfigurator extends IntegerConfigurator {
	public ConstantConfigurator() {
		super(Constant.ATTR_VALUE, 0, 0, 0, 16);
	}

	@Override
	public int getMaximumValue(AttributeSet attrs) {
		BitWidth width = attrs.getValue(StdAttr.WIDTH);
		int ret = width.getMask();
		if (ret >= 0) {
			return ret;
		} else {
			return Integer.MAX_VALUE;
		}
	}

	@Override
	public int getMinimumValue(AttributeSet attrs) {
		BitWidth width = attrs.getValue(StdAttr.WIDTH);
		if (width.getWidth() < 32) {
			return 0;
		} else {
			return Integer.MIN_VALUE;
		}
	}
}

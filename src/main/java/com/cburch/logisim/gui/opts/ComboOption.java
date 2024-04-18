/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.opts;

import javax.swing.JComboBox;

import main.java.com.cburch.logisim.data.AttributeOption;
import main.java.com.cburch.logisim.util.StringGetter;

class ComboOption {
	private Object value;
	private StringGetter getter;
	
	ComboOption(String value, StringGetter getter) {
		this.value = value;
		this.getter = getter;
	}
	
	ComboOption(AttributeOption value) {
		this.value = value;
		this.getter = null;
	}
	
	@Override
	public String toString() {
		if (getter != null) return getter.get();
		if (value instanceof AttributeOption) return ((AttributeOption) value).toDisplayString();
		return "???";
	}
	
	public Object getValue() {
		return value;
	}
	
	static void setSelected(JComboBox combo, Object value) {
		for (int i = combo.getItemCount() - 1; i >= 0; i--) {
			ComboOption opt = (ComboOption) combo.getItemAt(i);
			if (opt.getValue().equals(value)) {
				combo.setSelectedItem(opt);
				return;
			}
		}
		combo.setSelectedItem(combo.getItemAt(0));
	}

}

/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.circuit;

import main.java.com.cburch.logisim.comp.Component;
import main.java.com.cburch.logisim.data.Attribute;

public interface CircuitMutator {
	public void clear(Circuit circuit);
	public void add(Circuit circuit, Component comp);
	public void remove(Circuit circuit, Component comp);
	public void replace(Circuit circuit, Component oldComponent, Component newComponent);
	public void replace(Circuit circuit, ReplacementMap replacements);
	public void set(Circuit circuit, Component comp, Attribute<?> attr, Object value);
	public void setForCircuit(Circuit circuit, Attribute<?> attr, Object value);
}

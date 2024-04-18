/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.tools;

import main.java.com.cburch.logisim.circuit.Circuit;
import main.java.com.cburch.logisim.comp.ComponentUserEvent;
import main.java.com.cburch.logisim.proj.Action;

public interface TextEditable {
	public Caret getTextCaret(ComponentUserEvent event);
	public Action getCommitAction(Circuit circuit, String oldText, String newText);
}

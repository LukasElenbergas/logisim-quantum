/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.tools.move;

public interface MoveRequestListener {
	public void requestSatisfied(MoveGesture gesture, int dx, int dy);
}

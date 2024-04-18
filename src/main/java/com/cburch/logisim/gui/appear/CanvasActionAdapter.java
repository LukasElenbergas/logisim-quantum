/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.appear;

import java.util.HashMap;
import java.util.Map;

import main.java.com.cburch.draw.actions.ModelAction;
import main.java.com.cburch.draw.model.CanvasObject;
import main.java.com.cburch.draw.undo.Action;
import main.java.com.cburch.logisim.circuit.Circuit;
import main.java.com.cburch.logisim.circuit.CircuitMutator;
import main.java.com.cburch.logisim.circuit.CircuitTransaction;
import main.java.com.cburch.logisim.circuit.appear.AppearanceElement;
import main.java.com.cburch.logisim.proj.Project;

public class CanvasActionAdapter extends main.java.com.cburch.logisim.proj.Action {
	private Circuit circuit;
	private Action canvasAction;
	private boolean wasDefault;
	
	public CanvasActionAdapter(Circuit circuit, Action action) {
		this.circuit = circuit;
		this.canvasAction = action;
	}

	@Override
	public String getName() {
		return canvasAction.getName();
	}

	@Override
	public void doIt(Project proj) {
		wasDefault = circuit.getAppearance().isDefaultAppearance();
		if (affectsPorts()) {
			ActionTransaction xn = new ActionTransaction(true);
			xn.execute();
		} else {
			canvasAction.doIt();
		}
	}

	@Override
	public void undo(Project proj) {
		if (affectsPorts()) {
			ActionTransaction xn = new ActionTransaction(false);
			xn.execute();
		} else {
			canvasAction.undo();
		}
		circuit.getAppearance().setDefaultAppearance(wasDefault);
	}
	
	private boolean affectsPorts() {
		if (canvasAction instanceof ModelAction) {
			for (CanvasObject o : ((ModelAction) canvasAction).getObjects()) {
				if (o instanceof AppearanceElement) {
					return true;
				}
			}
		}
		return false;
	}
	
	private class ActionTransaction extends CircuitTransaction {
		private boolean forward;
		
		ActionTransaction(boolean forward) {
			this.forward = forward;
		}
		
		@Override
		protected Map<Circuit, Integer> getAccessedCircuits() {
			Map<Circuit, Integer> accessMap = new HashMap<Circuit, Integer>();
			for (Circuit supercirc : circuit.getCircuitsUsingThis()) {
				accessMap.put(supercirc, READ_WRITE);
			}
			return accessMap;
		}

		@Override
		protected void run(CircuitMutator mutator) {
			if (forward) {
				canvasAction.doIt();
			} else {
				canvasAction.undo();
			}
		}
		
	}
}

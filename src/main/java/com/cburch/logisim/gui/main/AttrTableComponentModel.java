/* Copyright (c) 2011, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.main;

import main.java.com.cburch.logisim.circuit.Circuit;
import main.java.com.cburch.logisim.comp.Component;
import main.java.com.cburch.logisim.data.Attribute;
import main.java.com.cburch.logisim.gui.generic.AttrTableSetException;
import main.java.com.cburch.logisim.gui.generic.AttributeSetTableModel;
import main.java.com.cburch.logisim.proj.Project;
import main.java.com.cburch.logisim.tools.SetAttributeAction;

class AttrTableComponentModel extends AttributeSetTableModel {
	Project proj;
	Circuit circ;
	Component comp;

	AttrTableComponentModel(Project proj, Circuit circ, Component comp) {
		super(comp.getAttributeSet());
		this.proj = proj;
		this.circ = circ;
		this.comp = comp;
	}
	
	public Circuit getCircuit() {
		return circ;
	}
	
	public Component getComponent() {
		return comp;
	}

	@Override
	public String getTitle() {
		return comp.getFactory().getDisplayName();
	}

	@Override
	public void setValueRequested(Attribute<Object> attr, Object value)
			throws AttrTableSetException {
		if (!proj.getLogisimFile().contains(circ)) {
			String msg = Strings.get("cannotModifyCircuitError");
			throw new AttrTableSetException(msg);
		} else {
			SetAttributeAction act = new SetAttributeAction(circ,
					Strings.getter("changeAttributeAction"));
			act.set(comp, attr, value);
			proj.doAction(act);
		}
	}
}



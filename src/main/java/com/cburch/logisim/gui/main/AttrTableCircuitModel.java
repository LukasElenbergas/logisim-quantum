/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.main;

import main.java.com.cburch.logisim.circuit.Circuit;
import main.java.com.cburch.logisim.circuit.CircuitMutation;
import main.java.com.cburch.logisim.data.Attribute;
import main.java.com.cburch.logisim.gui.generic.AttrTableSetException;
import main.java.com.cburch.logisim.gui.generic.AttributeSetTableModel;
import main.java.com.cburch.logisim.proj.Project;

public class AttrTableCircuitModel extends AttributeSetTableModel {
	private Project proj;
	private Circuit circ;
	
	public AttrTableCircuitModel(Project proj, Circuit circ) {
		super(circ.getStaticAttributes());
		this.proj = proj;
		this.circ = circ;
	}

	@Override
	public String getTitle() {
		return Strings.get("circuitAttrTitle", circ.getName());
	}
	
	@Override
	public void setValueRequested(Attribute<Object> attr, Object value)
			throws AttrTableSetException {
		if (!proj.getLogisimFile().contains(circ)) {
			String msg = Strings.get("cannotModifyCircuitError");
			throw new AttrTableSetException(msg);
		} else {
			CircuitMutation xn = new CircuitMutation(circ);
			xn.setForCircuit(attr, value);
			proj.doAction(xn.toAction(Strings.getter("changeCircuitAttrAction")));
		}
	}
}


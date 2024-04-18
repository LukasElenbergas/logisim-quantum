/* Copyright (c) 2011, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.gui.main;

import main.java.com.cburch.logisim.data.Attribute;
import main.java.com.cburch.logisim.gui.generic.AttributeSetTableModel;
import main.java.com.cburch.logisim.proj.Project;
import main.java.com.cburch.logisim.tools.Tool;

public class AttrTableToolModel extends AttributeSetTableModel {
	Project proj;
	Tool tool;
	
	public AttrTableToolModel(Project proj, Tool tool) {
		super(tool.getAttributeSet());
		this.proj = proj;
		this.tool = tool;
	}
	
	@Override
	public String getTitle() {
		return Strings.get("toolAttrTitle", tool.getDisplayName());
	}
	
	public Tool getTool() {
		return tool;
	}
	
	@Override
	public void setValueRequested(Attribute<Object> attr, Object value) {
		proj.doAction(ToolAttributeAction.create(tool, attr, value));
	}
}

/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.logisim.std;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.java.com.cburch.logisim.std.arith.Arithmetic;
import main.java.com.cburch.logisim.std.base.Base;
import main.java.com.cburch.logisim.std.gates.Gates;
import main.java.com.cburch.logisim.std.io.Io;
import main.java.com.cburch.logisim.std.memory.Memory;
import main.java.com.cburch.logisim.std.plexers.Plexers;
import main.java.com.cburch.logisim.std.wiring.Wiring;
import main.java.com.cburch.logisim.tools.Library;
import main.java.com.cburch.logisim.tools.Tool;

public class Builtin extends Library {
	private List<Library> libraries = null;

	public Builtin() {
		libraries = Arrays.asList(new Library[] {
			new Base(),
			new Gates(),
			new Wiring(),
			new Plexers(),
			new Arithmetic(),
			new Memory(),
			new Io(),
		});
	}

	@Override
	public String getName() { return "Builtin"; }

	@Override
	public String getDisplayName() { return Strings.get("builtinLibrary"); }

	@Override
	public List<Tool> getTools() { return Collections.emptyList(); }
	
	@Override
	public List<Library> getLibraries() {
		return libraries;
	}
}

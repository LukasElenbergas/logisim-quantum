/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package main.java.com.cburch.draw.gui;

import java.awt.BorderLayout;
import java.util.Collections;

import javax.swing.JFrame;

import main.java.com.cburch.draw.canvas.Canvas;
import main.java.com.cburch.draw.model.CanvasObject;
import main.java.com.cburch.draw.model.Drawing;
import main.java.com.cburch.draw.shapes.Rectangle;
import main.java.com.cburch.draw.tools.DrawingAttributeSet;
import main.java.com.cburch.draw.undo.UndoLog;
import main.java.com.cburch.draw.undo.UndoLogDispatcher;
import main.java.com.cburch.logisim.gui.generic.AttrTable;
import main.java.com.cburch.logisim.util.HorizontalSplitPane;
import main.java.com.cburch.logisim.util.VerticalSplitPane;

public class Main {
	public static void main(String[] args) {
		DrawingAttributeSet attrs = new DrawingAttributeSet();
		Drawing model = new Drawing();
		CanvasObject rect = attrs.applyTo(new Rectangle(25, 25, 50, 50));
		model.addObjects(0, Collections.singleton(rect));

		showFrame(model, "Drawing 1");
		showFrame(model, "Drawing 2");
	}
	
	private static void showFrame(Drawing model, String title) {
		JFrame frame = new JFrame(title);
		DrawingAttributeSet attrs = new DrawingAttributeSet();

		Canvas canvas = new Canvas();
		Toolbar toolbar = new Toolbar(canvas, attrs);
		canvas.setModel(model, new UndoLogDispatcher(new UndoLog()));
		canvas.setTool(toolbar.getDefaultTool());
		
		AttrTable table = new AttrTable(frame);
		AttrTableDrawManager manager = new AttrTableDrawManager(canvas, table, attrs);
		manager.attributesSelected();
		HorizontalSplitPane west = new HorizontalSplitPane(toolbar, table, 0.5);
		VerticalSplitPane all = new VerticalSplitPane(west, canvas, 0.3);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(all, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
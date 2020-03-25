package com.kassmon.assembly.windows;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProgramEditor extends JInternalFrame {
	
	
	private JScrollPane pane;
	private JTextArea textArea;
	
	public ProgramEditor () {
		super("program editor");
		
		this.textArea = new JTextArea("");
		this.pane = new JScrollPane(textArea);
		
		//this.pane.setBounds(10,10, this.getWidth() - 50, this.getHeight() - 50);
		
		super.add(this.pane);
	}
	
}

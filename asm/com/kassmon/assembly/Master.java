package com.kassmon.assembly;

import com.kassmon.assembly.windows.ProgramEditor;

import javax.swing.*;


@SuppressWarnings({ "unused", "serial" })
public class Master extends JFrame {
	
	private JMenuBar menuBar;
	
	private JMenu windows;
	private JMenu settings;
	private ProgramEditor editor;
	
	
	
	public Master() {
		super("computer");
		super.setExtendedState(MAXIMIZED_BOTH);
		//super.setUndecorated(false);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLayout(null);
		super.setResizable(false);
		//menu
		this.menuBar = new JMenuBar();
		this.settings = new JMenu("settings");
		
		this.menuBar.add(this.settings);
		super.setJMenuBar(menuBar);
		//windows
		
		this.editor = new ProgramEditor();
		this.editor.setBounds(10,10, 150, 400);
		super.add(editor);
		this.editor.setVisible(true);
		this.editor.setResizable(true);
		//this.editor.setLayout(null);
		
		
		super.setVisible(true);
	}
	
	
	
}

package com.kassmon.assembly;

import javax.swing.*;

import com.kassmon.assembly.logic.RunTime;

@SuppressWarnings({ "unused" })
public class Window extends RunTime {
	
	private JFrame frame;
	//menu items
	private JMenuBar menu;
	
	private JMenu file;
	private JButton exit;
	
	private JMenu examples;
	
	private JMenu buildAndRun;
	//Programmer and debugger items
	private JScrollPane inputscroll;
	private JTextArea input;
	
	private JLabel memLabels[];
	private JLabel memReadouts[];
	
	public Window() {
		this.frame = new JFrame();
		//menu items
		
		//Programmer and debugger items
		this.memLabels = new JLabel[21];
		this.memReadouts = new JLabel[21];
		
		 
		
	}
	
	
	
}

package com.kassmon.assemblyIDE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kassmon.assemblyIDE.console.Console;

@SuppressWarnings("serial")
public class MasterWindow extends JFrame{
	
	private JMenuBar menuBar;
	private JButton exit;
	
	private Console console;
	
	public MasterWindow() {
		setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		setResizable(false);
		
		//menu items
		menuBar = new JMenuBar();
		exit = new JButton("EXIT");
		
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(exit);
		
		//ui elements
		console = new Console();
		console.setVisible(true);
		
		//adds
		setJMenuBar(menuBar);
		
		add(console);
		
		//events 
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
	}
	
}

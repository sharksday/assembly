package com.kassmon.assembly.lunchers.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.Log;
import com.kassmon.library.log.LogEntry;
import com.kassmon.library.log.NewLogEntryEvent;

@SuppressWarnings({ "unused", "serial" })
public class Window extends JFrame{
	RunTime runtime;
	boolean error = false;
	//menu items
	private JMenuBar menu;
	
	private JMenu file;
	private JButton exit;
	
	private JMenu examples;
	
	private JMenu buildAndRun;
	
	private JMenu Windows;
	private JButton programmerDebuggerView;
	//Programmer and debugger items
	private JScrollPane inputscroll;
	private JTextArea input;
	private JButton run;
	private JLabel memLabels[];
	private JLabel memReadouts[];
	
	public Window() {
		Log.addEventListener(new NewLogEntryEvent() {
			@Override public void newError(LogEntry logEntry) {
				error = true;
			}});
		super.setSize(800, 600);
		super.setLayout(null);
		this.runtime = new RunTime();
		//menu items
		this.menu = new JMenuBar();
		this.file = new JMenu("File");
		
		this.examples = new JMenu("Examples");
		//Programmer and debugger items
		this.input = new JTextArea("");
		this.inputscroll = new JScrollPane(this.input);
		this.run = new JButton("run");
		this.memLabels = new JLabel[19];
		this.memReadouts = new JLabel[19];
		
		for (int i = 0; i < 19; i++) {
			this.memLabels[i] = new JLabel();
			this.memReadouts[i] = new JLabel();
			this.memReadouts[i].setText("0");
			super.add(this.memLabels[i]);
			super.add(this.memReadouts[i]);
			this.setMemLoc(i, 520, (i * 20) + 10);
		}
		
		this.memLabels[0].setText("acc");
		this.memLabels[1].setText("adr");
		this.memLabels[2].setText("a00");
		this.memLabels[3].setText("a01");
		this.memLabels[4].setText("a02");
		this.memLabels[5].setText("a03");
		this.memLabels[6].setText("a04");
		this.memLabels[7].setText("a05");
		this.memLabels[8].setText("a06");
		this.memLabels[9].setText("a07");
		this.memLabels[10].setText("a08");
		this.memLabels[11].setText("a09");
		this.memLabels[12].setText("a10");
		this.memLabels[13].setText("a11");
		this.memLabels[14].setText("a12");
		this.memLabels[15].setText("a13");
		this.memLabels[16].setText("a14");
		this.memLabels[17].setText("a15");
		this.memLabels[18].setText("pc");
		 
		super.add(this.inputscroll);
		super.add(this.run);
		this.inputscroll.setBounds(10, 10, 500, super.getHeight() - 60);
		this.run.setBounds(518, 400, 60, 20);
		
		this.programmerDebuggerViewVisible(true);
		
		this.run.addActionListener(new ActionListener () {
			@Override public void actionPerformed(ActionEvent e) {runProgram();}});
	}
	
	public void setMemLoc(int i, int x, int y) {
		this.memLabels[i].setBounds(x, y, 30, 20);
		this.memReadouts[i].setBounds(x + 30, y, 40, 20);
	}
	
	public void programmerDebuggerViewVisible(boolean visible) {
		this.inputscroll.setVisible(visible);
		this.run.setVisible(visible);
		for (int i = 0; i < 19; i++) {
			this.memLabels[i].setVisible(visible);
			this.memReadouts[i].setVisible(visible);
		}
	}
	
	public void getMemValues() {
		this.memReadouts[0].setText(String.valueOf(runtime.getAcc()));
		this.memReadouts[1].setText(String.valueOf(runtime.getAdr()));
		this.memReadouts[3].setText(String.valueOf(runtime.getA(0)));
		this.memReadouts[4].setText(String.valueOf(runtime.getA(1)));
		this.memReadouts[5].setText(String.valueOf(runtime.getA(2)));
		this.memReadouts[6].setText(String.valueOf(runtime.getA(3)));
		this.memReadouts[7].setText(String.valueOf(runtime.getA(4)));
		this.memReadouts[8].setText(String.valueOf(runtime.getA(5)));
		this.memReadouts[9].setText(String.valueOf(runtime.getA(6)));
		this.memReadouts[10].setText(String.valueOf(runtime.getA(7)));
		this.memReadouts[11].setText(String.valueOf(runtime.getA(8)));
		this.memReadouts[12].setText(String.valueOf(runtime.getA(9)));
		this.memReadouts[13].setText(String.valueOf(runtime.getA(10)));
		this.memReadouts[14].setText(String.valueOf(runtime.getA(11)));
		this.memReadouts[15].setText(String.valueOf(runtime.getA(12)));
		this.memReadouts[16].setText(String.valueOf(runtime.getA(13)));
		this.memReadouts[17].setText(String.valueOf(runtime.getA(14)));
		this.memReadouts[18].setText(String.valueOf(runtime.getA(15)));
	}
	
	private void build() {
		//CommandTokenizer t = new CommandTokenizer();
		//t.setInput(this.input.getText());
		//this.runtime.setProgram(t.getProgram());
	}
	
	private void runProgram() {
		this.build();
		if (!error) {
			while (runtime.getPc() < runtime.getProgram().getProgramLength()) {
				runtime.runCommand(runtime.getPc());
				this.getMemValues();
			}
		}else {
			error = false;
		}
	}
	
}
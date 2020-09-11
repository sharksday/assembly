package com.kassmon.assembly.lunchers.window.testsingFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.assembly.externalBuss.Ram;
import com.kassmon.assembly.externalBuss.Stack;
import com.kassmon.assembly.io.OutputControler;
import com.kassmon.assembly.io.OutputEvent;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.assembly.tokenizer.TestingFrameTokenizer;
import com.kassmon.assembly.util.Vars;
import com.kassmon.library.tokenizers.Token;

public class FrameMain {
	
	private static JFrame frame = new JFrame();
	private static JTextArea consoleOutput = new JTextArea();
	private static JScrollPane consoleOutputPanel = new JScrollPane(consoleOutput);
	private static JTextField consoleInput = new JTextField();
	private static JTextArea programEditor = new JTextArea();
	private static JScrollPane programEditorPane = new JScrollPane(programEditor);
	private static JFileChooser fc = new JFileChooser();
	private static RunTime runtime = new RunTime();
	private static String command = "null";
	private static TestingFrameTokenizer t = new TestingFrameTokenizer();
	
	public static void main(String[] args) {
		com.kassmon.assembly.io.OutputControler.setOutputEvent(true);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setLayout(null);
		
		consoleOutputPanel.setSize(350, 520);
		consoleOutputPanel.setLocation(10, 10);
		consoleOutput.setEditable(false);
		consoleInput.setSize(350, 20);
		consoleInput.setLocation(10, 540);
		programEditorPane.setSize(350, 550);
		programEditorPane.setLocation(370, 10);
		programEditor.setEditable(true);
		programEditor.setLineWrap(true);
		
		frame.add(consoleOutputPanel);
		frame.add(consoleInput);
		frame.add(programEditorPane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		FileFilter ff = new FileNameExtensionFilter("Assembly File", "asm");
		
		fc.addChoosableFileFilter(ff);
		fc.setFileFilter(ff);
		fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
		
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);
		
		consoleInput.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {input();}
			});
		
		OutputControler.addOutputEventListener(new OutputEvent () {
			@Override public void output(String text) {
				consoleOutput.setText(consoleOutput.getText() + text);
			}});
		
		
	}
	
	private static void input() {
		t.setInput(consoleInput.getText());
		Token token = t.getNextToken();
		if (token.getType().equals("command")) {
			command = token.getToken();
		}
		switch (command) {
			case "open":
				OpenFile();
				break;
			case "save":
				saveFile();
				break;
			case "run":
				run();
				break;
			case "exit":
				System.exit(0);
				break;
			case "clear":
				clearScreen();
				break;
			case "bus":
				busCommands();
				break;
			case "help":
				help();
				break;
			default:
				break;
		}
		consoleInput.setText("");
	}
	
	private static void OpenFile() {
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				String string = "";
				String st = "";
				BufferedReader br = new BufferedReader(new FileReader(file));
				 while ((st = br.readLine()) != null) {
					 string = string + st + System.lineSeparator();
				 }
				 br.close();
				programEditor.setText(string);
			}catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}else {
			
		}
	}
	
	private static void saveFile() {
		if (fc.getSelectedFile() != null) {
			try {
				PrintWriter writer = new PrintWriter(fc.getSelectedFile());
				writer.print(programEditor.getText());
				writer.close();
			} catch (FileNotFoundException e) {
				addTextToConsole("no file open");
			}
		}else {
			addTextToConsole("no file open");
		}
	}	
	
	private static void run() {
		Token token;
		CommandTokenizer tokenizer = new CommandTokenizer(Vars.commandList);
		tokenizer.setInput(programEditor.getText());
		try {
			runtime.setProgram(tokenizer.getProgram());
			if (t.hasNextToken()) {
				token = t.getNextToken();
				if (token.getToken().equals("-v")) {
					try {
						runtime.RunProgram(true);
					} catch (RuntimeException e) {
						addTextToConsole(e.getMessage());
					}
				}else{
					try {
						runtime.RunProgram(false);
					} catch (RuntimeException e) {
						addTextToConsole(e.getMessage());
					}
				}
			}else {
				try {
					runtime.RunProgram(false);
				} catch (RuntimeException e) {
					addTextToConsole(e.getMessage());
				}
			}
		} catch (ParcerException e) {
			addTextToConsole(e.getMessage());
		}
	}
	
	private static void clearScreen() {
		Token temp = t.getNextToken();
		if (temp.getType().equals("componet")) {
			switch (temp.getToken()) {
				case "console":
					consoleOutput.setText("");
					break;
				case "program":
					programEditor.setText("");
					break;
				case "ls":
					addTextToConsole("console");
					addTextToConsole("program");
					break;
				default:
					break;
			}
		}
	}
	
	private static void busCommands() {
		Token token = t.getNextToken();
		if (token.getType().equals("componet")) {
			switch (token.getToken()) {
				case "make":
					busMakeItem();
					break;
				case "remove":
					token = t.getNextToken();
					int id = 0;
					if (token.getType().equals("value")) {
						id = Integer.parseInt(token.getToken());
					}else if (token.getToken().equals("hex")) {
						id = Integer.parseInt(token.getToken().substring(2), 16);
					}
					runtime.getBus().remove(id);
					break;
				case "list":
					ArrayList<ExternalBusItem> list = runtime.getBus();
					for (int i = 0; i < list.size(); i++) {
						String type = list.get(i).getClass().getName();
						String adr0 = String.valueOf(list.get(i).getCommandAdr());
						String adr1 = String.valueOf(list.get(i).getDataAdr());
						addTextToConsole(String.valueOf(i) + " " +type + " " + adr0 + " " + adr1);
					}
					break;
				case "info":
					busInfo();
					break;
				default:
					break;
			}
		}
	}
	
	private static void busMakeItem() {
		Token type = t.getNextToken();
		Token token = t.getNextToken();
		int adr[] = {0,0};
		for (int i = 0; i < 2; i ++) {
			token = t.getNextToken();
			if (token.getType().equals("value")) {
				adr[i] = Integer.parseInt(token.getToken());
			}else if (token.getType().equals("hex")) {
				adr[i] = Integer.parseInt(token.getToken().substring(2), 16);
			}
		}
		switch (type.getToken()) {
			case "ram":
				runtime.addBusItem(new Ram(adr[1], adr[0]));
				break;
			case "stack":
				runtime.addBusItem(new Stack(adr[1],adr[0]));
				break;
			default:
				break;
		}
	}
	
	private static void busInfo() {
		if (!t.hasNextToken()) {
			
		}else {
			Token token = t.getNextToken();
			if (token.getType().equals("bus")) {
				switch (token.getToken()) {
					case "ram":
						addTextToConsole("command \"1\" set ram cell to read or wright the data");
						addTextToConsole("command \"2\" sets data to selected ram cell");
						addTextToConsole("command \"3\" gest data from selected ram cell");
						break;
					case "stack":
						break;
					default:
						break;
				}
			}
		}
	}
	
	private static void addTextToConsole(String s) {
		consoleOutput.setText(consoleOutput.getText() + s + System.lineSeparator());
	}
	
	private static void help() {
		if (!t.hasNextToken()) {
			addTextToConsole("to show more help on a command use \"help (command)\"");
			addTextToConsole("\"open\" open a file of you choice.");
			addTextToConsole("\"save\" saves the program to the currently open file.");
			addTextToConsole("\"run\" runs the program displayed in the program editor.");
			addTextToConsole("\"exit\" closes the program.");
			addTextToConsole("\"clear\" alows the user to clear the console or the program editor.");
			addTextToConsole("\"bus\" prefix for all bus commands.");
		}else {
			Token token = t.getNextToken();
			if (token.getType().equals("command")) {
				switch (token.getToken()) {
				case "run":
					
					break;
				case "open":
					break;
				case "save":
					break;
				case "exit":
					break;
				case "clear":
					break;
				case "bus":
					break;
				case "help":
					break;
				}
			}
		}
	}
	
}

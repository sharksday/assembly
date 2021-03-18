package com.kassmon.assemblyIDE.console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.regex.Pattern;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kassmon.assemblyIDE.console.Commands.ConsoleCommand;
import com.kassmon.library.tokenizers.Tokenizer;

@SuppressWarnings({ "serial" })
public class Console extends JInternalFrame {
	
	
	private Tokenizer tokenizer;
	private JTextArea consoleOutput = new JTextArea();
	private JTextField consoleInput = new JTextField();
	private JScrollPane consoleOutputPanel = new JScrollPane(consoleOutput);
	
	private BaseCommand bcommand = new BaseCommand();
	
	public Console () {
		super("Console");
		setLayout(null);
		setClosable(true);
		setResizable(true);
		setSize(400,300);
		addComponentListener(new ResizeListener());
		
		consoleOutputPanel.setSize(getWidth() - 30, getHeight() - 80);
		consoleOutputPanel.setLocation(10, 10);
		consoleInput.setSize(getWidth() - 30, 20);
		consoleInput.setLocation(10, getHeight() - 60);
		
		add(consoleOutputPanel);
		add(consoleInput);
		
		tokenizer = setupTokenizer();
		
		consoleInput.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) { input(); }
		});
		
	}
	
	private Tokenizer setupTokenizer() {
		Tokenizer t = new Tokenizer();
		t.addPattern(Pattern.compile("^(exit\\b)"), "command");
		t.addPattern(Pattern.compile("^(new\\b)"), "command");
		t.addPattern(Pattern.compile("^(bus\\b)"), "command");
		t.addPattern(Pattern.compile("^(close\\b)"), "command");
		t.addPattern(Pattern.compile("^(open\\b)"), "command");
		t.addPattern(Pattern.compile("^([a-zA-Z0-9]\\b)"), "command");
		return t;
	}
	
	private void input() {
		tokenizer.setInput(consoleInput.getText());
		bcommand.run(tokenizer, this);
		consoleInput.setText("");
	}
	
	public void addLineToConsoleOutput(String text) {
		consoleOutput.setText(consoleOutput.getText() + text + System.lineSeparator());
	}
	
	public void clearConsoleOutput() {
		consoleOutput.setText("");
	}
	
	class ResizeListener extends ComponentAdapter {
    	public void componentResized(ComponentEvent e) {
    		consoleOutputPanel.setSize(getWidth() - 30, getHeight() - 80);
			consoleInput.setSize(getWidth() - 30, 20);
			consoleInput.setLocation(10, getHeight() - 60);
			repaint();
    	}
}
	
	private class BaseCommand implements ConsoleCommand {
		private ConsoleCommand nextCommand;
		@Override
		public void addCommand(ConsoleCommand command) {
			if (nextCommand == null) {
				nextCommand = command;
			}else {
				nextCommand.addCommand(command);
			}
		}

		@Override
		public void run(Tokenizer tokenizer, Console console) {
			nextCommand.run(tokenizer, console);
		}
		
	}
	
}
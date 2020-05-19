package com.kassmon.assembly.tokenizer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.kassmon.assembly.commands.*;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.program.ProgramLine;
import com.kassmon.library.log.Log;
import com.kassmon.library.log.LogEntry;
import com.kassmon.library.log.NewLogEntryEvent;
import com.kassmon.library.tokenizers.Token;

public class CommandTokenizer extends com.kassmon.library.tokenizers.Tokenizer {
	private boolean error = false;
	private ArrayList<Command> commands;
	
	public CommandTokenizer() {
		Log.addEventListener(new NewLogEntryEvent() {
			@Override public void newError(LogEntry logEntry) {
				error = true;
			}});
		commands = new ArrayList<>();
		
		commands.add(new Nop());
		commands.add(new Mov());
		
		commands.add(new Add());
		commands.add(new Sub());
		commands.add(new Mul());
		commands.add(new Div());
		commands.add(new Mod());
		
		commands.add(new Rpc());
		commands.add(new Wpc());
		
		commands.add(new Jmp());
		commands.add(new Jez());
		commands.add(new Jgz());
		commands.add(new Jlz());
		commands.add(new Jnz());
		
		commands.add(new Jsr());
		commands.add(new Rsr());
		
		commands.add(new Psh());
		commands.add(new Pul());
		commands.add(new Clk());
		
		
		
		for (Command command : commands) {
			super.addPattern(Pattern.compile("^(" + command.getPattern() + ")"), "command");
		}
		
		super.addPattern(Pattern.compile("^(acc)"), "mem");
		super.addPattern(Pattern.compile("^(adr)"), "mem");
		super.addPattern(Pattern.compile("^(pc)"), "mem");
		super.addPattern(Pattern.compile("^(null)"), "mem");
		super.addPattern(Pattern.compile("^(a[0-9][0-9])"), "mem");
		super.addPattern(Pattern.compile("^(0x[0-9abcdef][0-9abcdef])"), "hex");
		super.addPattern(Pattern.compile("^(0x[0-9abcdef][0-9abcdef][0-9abcdef][0-9abcdef])"), "hex");
		super.addPattern(Pattern.compile("^(0x[0-9abcdef][0-9abcdef][0-9abcdef][0-9abcdef][0-9abcdef][0-9abcdef])"), "hex");
		super.addPattern(Pattern.compile("^(-[a-zA-Z0-9])"), "char");
		super.addPattern(Pattern.compile("^([0-9]+)"), "value");
		super.addPattern(Pattern.compile("^(![a-zA-Z]+)"), "label");
		super.addPattern(Pattern.compile("^([a-zA-Z]+)"), "label");
		
		// super.addPattern(Pattern.compile("^([])"), "");
		
	}
	
	public Program getProgram() {
		Program program = new Program();
		while (super.hasNextToken()) {
			if (error) return new Program();
			Token t = super.getNextToken();
			if (t.getType().equals("null")) {
				return new Program();
			} else if (t.getType().equals("command")) {
				for (Command command : commands) {
					if (t.getToken().equals(command.getPattern())) {
						program.addProgramLine(new ProgramLine(command.parse(this)));
					}
				}
			} else if (t.getType().equals("label")) {
				program.addProgramLine(new ProgramLine(t.getToken().substring(1)));
			} else {
				System.out.println("token no id");
			}
		}
		return program;
	}
	
}
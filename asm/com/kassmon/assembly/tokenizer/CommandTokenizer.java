package com.kassmon.assembly.tokenizer;

import static com.kassmon.library.log.Log.newLogEntry;

import java.util.regex.Pattern;

import com.kassmon.assembly.commands.*;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.program.ProgramLine;
import com.kassmon.library.log.EntryType;
import com.kassmon.library.log.Log;
import com.kassmon.library.log.LogEntry;
import com.kassmon.library.log.NewLogEntryEvent;
import com.kassmon.library.tokenizers.Token;

public class CommandTokenizer extends com.kassmon.library.tokenizers.Tokenizer {
	
	private String path = "com.kassmon.assembly.tokenizer.CommandTokenizer";
	private Command commandList[];
	private boolean error = false;
	
	public CommandTokenizer(Command[] commandList) {
		
		this.commandList = commandList;
		
		Log.addEventListener(new NewLogEntryEvent() {
			@Override public void newError(LogEntry logEntry) {
				if (logEntry.getErrorType() == EntryType.ERROR) error = true;
			}});
		
		for (Command command: commandList) {
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
	
	private ProgramLine getProgramaLine() {
		Token t = super.getNextToken();
		switch (t.getType()) {
			case "command":
				newLogEntry(EntryType.INFO, path, "command made " + t.getToken());
				for (Command command : commandList) {
					if (t.getToken().equals(command.getPattern())) {
						return new ProgramLine(command.parse(this));
					}
				}
			case "label":
				return new ProgramLine(t.getToken().substring(1));
			default:
				newLogEntry(EntryType.ERROR, path, "not a valid command id");
		}
		return null;
	}
	
	public Program getProgram() {
		Program program = new Program();
		while (super.hasNextToken()) {
			if (!error){
				ProgramLine line = this.getProgramaLine();
				if (line != null) {
					program.addProgramLine(line);
				} else {
					newLogEntry(EntryType.ERROR, path, "program error: no valid program line");
					return null;
					}
			}else {
				newLogEntry(EntryType.ERROR, path, "program error: general");
				return null;
			}
		}
		return program;
	}
	
}
package com.kassmon.old.assembly.logic.tokenizer;

import java.util.regex.Pattern;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.ParserNoOutputExcption;
import com.kassmon.library.tokenizers.Token;
import com.kassmon.old.assembly.logic.commands.*;
import com.kassmon.old.assembly.logic.program.Program;
import com.kassmon.old.assembly.logic.program.ProgramLine;

public class CommandTokenizer extends com.kassmon.library.tokenizers.Tokenizer {
	
	private Command commandList[];
	
	public CommandTokenizer(Command[] commandList) {
		
		this.commandList = commandList;
		
		for (Command command: commandList) {
			super.addPattern(Pattern.compile("^(" + command.getPattern() + "\\b)"), "command");
		}
		
		//super.addPattern(Pattern.compile("^(acc)"), "mem");
		//super.addPattern(Pattern.compile("^(adr)"), "mem");
		//super.addPattern(Pattern.compile("^(pc)"), "mem");
		super.addPattern(Pattern.compile("^(null\\b)"), "mem");
		super.addPattern(Pattern.compile("^(a[0-9]+\\b)"), "mem");
		super.addPattern(Pattern.compile("^(0x[0-9abcdef]+\\b)"), "hex");
		super.addPattern(Pattern.compile("^(null\\b)"), "binary");
		super.addPattern(Pattern.compile("^(0b[0-1]+\\b)"), "char");
		super.addPattern(Pattern.compile("^([0-9]+\\b)"), "value");
		super.addPattern(Pattern.compile("^(![a-zA-Z]+\\b)"), "label");
		super.addPattern(Pattern.compile("^([a-zA-Z]+\\b)"), "label");
		super.addPattern(Pattern.compile("^(\\/\\/.*\\/\\/)"), "comment");
		super.addPattern(Pattern.compile("^(TRUE\\b)"), "boolean");
		super.addPattern(Pattern.compile("^(FALSE\\b)"), "boolean");
		// super.addPattern(Pattern.compile("^([])"), "");
	}
	
	private ProgramLine getProgramaLine() throws ParserException, ParserNoOutputExcption {
		Token t = super.getNextToken();
		switch (t.getType()) {
			case "command":
				for (Command command : commandList) {
					if (t.getToken().equals(command.getPattern())) {
						return new ProgramLine(command.parse(this));
					}
				}
			case "label":
				return new ProgramLine(t.getToken().substring(1));
			case "comment":
				throw new ParserNoOutputExcption("");
			default:
				throw new ParserException("CommandTokenizer : token error");
		}
	}
	
	public Program getProgram() throws ParserException {
		Program program = new Program();
		while (super.hasNextToken()) {
			ProgramLine line = null;
			try {
				line = this.getProgramaLine();
			} catch (ParserNoOutputExcption e) {}
			if (line != null) {
				program.addProgramLine(line);
			}
		}
		return program;
	}
	
}
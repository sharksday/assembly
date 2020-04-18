package com.kassmon.assembly.tokenizer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.program.ProgramLine;
import com.kassmon.assembly.program.commands.*;
import com.kassmon.library.tokenizers.Token;

public class Tokenizer extends com.kassmon.library.tokenizers.Tokenizer {
	
	private ArrayList<Command> commands;
	
	public Tokenizer() {
		
		commands = new ArrayList<>();
		
		commands.add(new Nop());
		commands.add(new Mov());
		
		commands.add(new Add());
		commands.add(new Sub());
		commands.add(new Mul());
		commands.add(new Div());
		commands.add(new Mod());
		
		commands.add(new Lda());
		commands.add(new Sva());
		commands.add(new Ldb());
		commands.add(new Svb());
		
		commands.add(new Rpc());
		commands.add(new Wpc());
		
		commands.add(new Jmp());
		commands.add(new Jez());
		commands.add(new Jgz());
		commands.add(new Jlz());
		commands.add(new Jnz());
		
		for (Command command : commands) {
			super.addPattern(Pattern.compile("^(" + command.getPattern() + ")"), "command");
		}
		
		super.addPattern(Pattern.compile("^(acc)"), "mem");
		super.addPattern(Pattern.compile("^(adr)"), "mem");
		super.addPattern(Pattern.compile("^(pc)"), "mem");
		super.addPattern(Pattern.compile("^(null)"), "mem");
		super.addPattern(Pattern.compile("^(a[0-9][0-9])"), "mem");
		super.addPattern(Pattern.compile("^([0-9]+)"), "value");
		super.addPattern(Pattern.compile("^(![a-zA-Z]+)"), "label");
		super.addPattern(Pattern.compile("^([a-zA-Z]+)"), "label");
		
		// super.addPattern(Pattern.compile("^([])"), "");
		
	}
	
	public Program getProgram() {
		Program program = new Program();
		while (super.hasNextToken()) {
			Token t = super.getNextToken();
			if (t.getType().equals("null")) {
				return null;
			} else if (t.getType().equals("command")) {
				for (Command command : commands) {
					if (t.getToken().equals(command.getPattern())) {
						program.addProgramLine(new ProgramLine(command.parse(this)));
					}
				}
			} else if (t.getType().equals("label")) {
				program.addProgramLine(new ProgramLine(t.getToken()));
			} else {
				System.out.println("token no id");
			}
		}
		return program;
	}
	
}

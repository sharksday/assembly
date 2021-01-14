package com.kassmon.old.assembly.logic.commands.controlCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Mov extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
		Argument a1 = super.getArg(t), a2 = super.getArg(t);
		if (a1 == null) throw new ParserException("mov : argument error : null arg");
		if (a2 == null) throw new ParserException("mov : argument error : null arg");
		if (a1.isLabel()) throw new ParserException("mov : argument error : illegal label");
		if (a2.isLabel()) throw new ParserException("mov : argument error : illegal label");
		if (a2.isNumber()) throw new ParserException("mov : argument error : illegal number");
		return new Mov (a1, a2);
	}
	
	@Override
	public String getPattern() {
		return "mov";
	}
	
	private Argument a1, a2;
	
	public Mov(Argument a1, Argument a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
	
	public Mov() {
		
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		super.setValue(runtime, a2, super.getValue(runtime, a1));
	}
	
}
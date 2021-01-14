package com.kassmon.old.assembly.logic.commands.mathCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Not extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParserException("not : argument error : null argument");
		if (a1.isLabel()) throw new ParserException("not : argument error : illegal label");
		if (a1.isNumber()) throw new ParserException("not : argument error : illegal number");
		return new Not(a1);
	}
	
	@Override
	public String getPattern() {
		return "not";
	}
	
	private Argument a1;
	
	public Not (Argument a1) {
		this.a1 = a1;
		
	}
	
	public Not () {
		
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		super.setValue(runtime, a1, (super.getValue(runtime, a1) * -1));
	}
	
}

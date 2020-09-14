package com.kassmon.assembly.commands.mathCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Not extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("not : argument error : null argument");
		if (a1.isLabel()) throw new ParcerException("not : argument error : illegal label");
		if (a1.isNumber()) throw new ParcerException("not : argument error : illegal number");
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

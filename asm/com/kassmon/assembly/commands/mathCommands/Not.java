package com.kassmon.assembly.commands.mathCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Not extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		boolean error = false;
		Argument a1 = super.getArg(t);
		if (a1 == null) error = true;
		if (!error) {
			if (a1.isLabel()) error = true;
		}
		if (!error) return new Not (a1);
		throw new ParcerException("not : argument error");
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
	public void run(RunTime runtime) {
		super.setValue(runtime, a1, (super.getValue(runtime, a1) * -1));
	}
	
}

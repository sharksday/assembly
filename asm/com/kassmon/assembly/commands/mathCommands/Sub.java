package com.kassmon.assembly.commands.mathCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Sub extends Command {
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		boolean error = false;
		Argument a1 = super.getArg(t);
		Argument a2 = super.getArg(t);
		Argument a3 = super.getArg(t);
		if (a1 == null) error = true;
		if (a2 == null) error = true;
		if (a3 == null) error = true;
		if (!error) {
			if (a1.isLabel()) error = true;
			if (a2.isLabel()) error = true;
			if (a3.isLabel()) error = true;
			if (a3.isNumber()) error = true;
		}
		if (!error) return new Sub (a1, a2, a3);
		throw new ParcerException("sub : argument error");
	}

	@Override
	public String getPattern() {
		 return "sub";
	}
	
	private Argument a1;
	private Argument a2;
	private Argument a3;
	
	public Sub (Argument a1, Argument a2, Argument a3) {
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
	}
	
	public Sub() {
		
	}

	@Override
	public void run(RunTime runtime) {
		int value = super.getValue(runtime, a1) - super.getValue(runtime, a2);
		super.setValue(runtime, a3, value);
	}

}

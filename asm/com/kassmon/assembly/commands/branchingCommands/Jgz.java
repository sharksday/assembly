package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Jgz extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = new Argument(true, false, "1"), a2 = new Argument(true, false, "1");;
		Argument a3 = super.getArg(t);
		if (a3 == null) throw new ParcerException("jez : argument error : null argument");
		if (!a3.isLabel()) throw new ParcerException("jez : argument error : illegal argument type");
		return new Bjp(a1, a2, a3);
	}
	
	@Override
	public String getPattern() {
		return "jgz";
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		
	}
	
}

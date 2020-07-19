package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Cmp extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("cmp : argument error : null argument");
		if (a1.isLabel()) throw new ParcerException("cmp : argument error : illegal label");
		return new Cmp(a1);
	}
	
	@Override
	public String getPattern() {
		return "cmp";
	}
	
	private Argument a1;
	
	public Cmp (Argument a1) {
		this.a1 = a1;
		
	}
	
	public Cmp () {
		
	}
	
	@Override
	public void run(RunTime runtime) throws RuntimeException {
		int t = super.getValue(runtime, a1);
		if (t == 0) {
			runtime.setJez(true);
			runtime.setJnz(false);
			runtime.setJgz(false);
			runtime.setJlz(false);
		}else if (t < 0) {
			runtime.setJez(false);
			runtime.setJnz(true);
			runtime.setJgz(false);
			runtime.setJlz(true);
		}else if (t > 0) {
			runtime.setJez(false);
			runtime.setJnz(true);
			runtime.setJgz(true);
			runtime.setJnz(false);
		}
		
	}
	
}

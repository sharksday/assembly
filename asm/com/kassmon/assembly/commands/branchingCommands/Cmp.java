package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.IRuntime;
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
	
	public Cmp() {
	}
	
	public Cmp (Argument a1) {
		this.a1 = a1;	
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		int t = super.getValue(runtime, a1);
		int flag = 0;
		if (t == 0) flag = flag + 0b00000001;
		if (t < 0) flag = flag + 0b00000010;
		if ((t % 2) == 1) flag = flag + 0b00000100;
		runtime.setFlags(flag);
	}
	
}
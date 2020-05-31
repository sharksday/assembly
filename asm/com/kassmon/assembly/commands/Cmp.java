package com.kassmon.assembly.commands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.EntryType;

public class Cmp extends Command {
	private String path = "com.kassmon.assembly.program.commands.Cmp";
	
	@Override
	public Command parse(CommandTokenizer t) {
		Argument a1 = super.getArg(t);
		if (a1 != null) if (a1.isLabel()) return new Jnz(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return new Cmp(a1);
	}

	@Override
	public String getPattern() {
		return "cmp";
	}
	
	public Cmp () {
		
	}
	
	private Argument a1;
	
	public Cmp (Argument a1) {
		this.a1 = a1;
	}
	
	@Override
	public void run(RunTime runtime) {
		int temp = runtime.getAcc();
		temp = temp - super.getValue(runtime, a1);
		if (temp == 0) {
			runtime.setZero(true);
			runtime.setNegative(false);
		}else if (temp < 0) {
			runtime.setZero(false);
			runtime.setNegative(true);
		}
	}
	
	
	
}

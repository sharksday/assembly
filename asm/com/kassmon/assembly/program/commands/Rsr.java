package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.Tokenizer;
import com.kassmon.library.log.EntryType;
import static com.kassmon.library.log.Log.newLogEntry;

public class Rsr extends Command {
	private String path = "com.kassmon.assembly.program.commands.Rsr";
	
	@Override
	public Command parse(Tokenizer t) {
		Argument a1 = super.getArg(t);
		if (!a1.isLabel())
			return new Rsr(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return null;
	}
	
	@Override
	public String getPattern() {
		return "rsr";
	}
	
	private Argument a1;
	
	public Rsr(Argument a1) {
		this.a1 = a1;
	}
	
	public Rsr() {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		
	}
	
}

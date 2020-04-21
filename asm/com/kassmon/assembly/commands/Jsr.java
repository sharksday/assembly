package com.kassmon.assembly.commands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.Tokenizer;
import com.kassmon.library.log.EntryType;

public class Jsr extends Command {
	private String path = "com.kassmon.assembly.program.commands.Jsr";
	
	@Override
	public Command parse(Tokenizer t) {
		Argument a1 = super.getArg(t);
		if (a1.isLabel()) return new Jsr(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return null;
	}
	
	@Override
	public String getPattern() {
		return "jsr";
	}
	
	private Argument a1;
	
	public Jsr(Argument a1) {
		this.a1 = a1;
	}
	
	public Jsr() {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		runtime.pushToStack(runtime.getPc());
		Program program = runtime.getProgram();
		for (int i = 0; i < program.getProgramLength(); i++) {
			if (!program.getProgramLine(i).isCommand()) {
				if (program.getProgramLine(i).getLabel().equals(a1.getValue())) {
					runtime.setPc(i);
				}
			}
		}
	}
	
}
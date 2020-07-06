package com.kassmon.assembly.commands.branchingCommands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.commands.controlCommands.Nop;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.EntryType;

public class Jnz extends Command {
	private String path = "com.kassmon.assembly.program.commands.Jnz";
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t);
		if (a1 != null) if (a1.isLabel()) return new Jnz(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return new Nop();
	}

	@Override
	public String getPattern() {
		 return "jnz";
	}
	
	private Argument a1;
	
	public Jnz (Argument a1) {
		this.a1 = a1;
	}
	
	public Jnz() {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		if (runtime.getAcc() != 0) {
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

}
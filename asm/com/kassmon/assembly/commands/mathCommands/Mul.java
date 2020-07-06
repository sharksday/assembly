package com.kassmon.assembly.commands.mathCommands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.commands.controlCommands.Nop;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.EntryType;

public class Mul extends Command {
	private String path = "com.kassmon.assembly.program.commands.Mul";
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t);
		if (a1 != null) {
			if (!a1.isLabel()) {
				return new Mul(a1);
			}
		}
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return new Nop();
	}

	@Override
	public String getPattern() {
		 return "mul";
	}
	
	private Argument a1;
	
	public Mul (Argument a1) {
		this.a1 = a1;
	}
	
	public Mul() {
		
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setAcc(runtime.getAcc() * super.getValue(runtime, a1));
	}

}

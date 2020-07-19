package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Jez extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("jez : argument error : null argument");
		if (!a1.isLabel()) throw new ParcerException("jez : argument error : illegal argument type");
		return new Jez(a1);
	}
	
	@Override
	public String getPattern() {
		return "jez";
	}
	
	private Argument a1;
	
	public Jez (Argument a1) {
		this.a1 = a1;
		
	}
	
	public Jez () {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		if (runtime.isJez()) {
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

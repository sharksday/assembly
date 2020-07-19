package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Jlz extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("jlz : argument error : null argument");
		if (!a1.isLabel()) throw new ParcerException("jlz : argument error : illegal argument type");
		return new Jlz(a1);
	}
	
	@Override
	public String getPattern() {
		return "jlz";
	}
	
	private Argument a1;
	
	public Jlz (Argument a1) {
		this.a1 = a1;
		
	}
	
	public Jlz () {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		if (runtime.isJnz()) {
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

package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Jgz extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("jgz : argument error : null argument");
		if (!a1.isLabel()) throw new ParcerException("jgz : argument error : illegal argument type");
		return new Jgz(a1);
	}
	
	@Override
	public String getPattern() {
		return "jgz";
	}
	
	Argument a1;
	
	public Jgz() {
	}
	
	public Jgz(Argument a1) {
		this.a1 = a1;
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		if ((runtime.getFlags() & 0b00000010) != 0) {
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

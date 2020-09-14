package com.kassmon.assembly.commands.controlCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Jsr extends Command {
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("jsr : argument error : null argument");
		if (!a1.isLabel()) throw new ParcerException("jsr : argument error : illegal argument type");
		return new Jsr(a1);
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
	public void run(IRuntime runtime) {
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
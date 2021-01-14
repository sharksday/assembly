package com.kassmon.old.assembly.logic.commands.controlCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.program.Program;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Jsr extends Command {
	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParserException("jsr : argument error : null argument");
		if (!a1.isLabel()) throw new ParserException("jsr : argument error : illegal argument type");
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
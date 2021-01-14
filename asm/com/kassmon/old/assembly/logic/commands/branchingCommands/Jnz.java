package com.kassmon.old.assembly.logic.commands.branchingCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.program.Program;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Jnz extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParserException("jnz : argument error : null argument");
		if (!a1.isLabel()) throw new ParserException("jnz : argument error : illegal argument type");
		return new Jnz(a1);
	}
	
	@Override
	public String getPattern() {
		return "jez";
	}
	
	Argument a1;
	
	public Jnz() {
		
	}
	
	public Jnz(Argument a1) {
		this.a1 = a1;
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		if ((runtime.getFlags() & 0b00000001) != 0) {
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

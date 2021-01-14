package com.kassmon.old.assembly.logic.commands.controlCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.program.Program;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Jmp extends Command {
	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParserException("jmp : argument error : null argument");
		if (!a1.isLabel()) throw new ParserException("jmp : argument error : illegal argument type");
		return new Jmp(a1);
	}

	@Override
	public String getPattern() {
		 return "jmp";
	}
	
	private Argument a1;
	
	public Jmp (Argument a1) {
		this.a1 = a1;
	}
	
	public Jmp () {
		
	}
	
	@Override
	public void run(IRuntime runtime) {
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

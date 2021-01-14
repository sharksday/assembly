package com.kassmon.old.assembly.logic.commands.branchingCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.*;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Bjp extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException {
		Argument a1 = super.getArg(t), a2 = super.getArg(t), a3 = super.getArg(t);
		if (a1 == null) throw new ParserException("bjp : argument error : null arg");
		if (a2 == null) throw new ParserException("bjp : argument error : null arg");
		if (a3 == null) throw new ParserException("bjp : argument error : null arg");
		if (a1.isLabel()) throw new ParserException("bjp : argument error : illegal label");
		if (a2.isLabel()) throw new ParserException("bjp : argument error : illegal label");
		if (!a3.isLabel()) throw new ParserException("bjp : argument error : illegal arg type");
		return null;
	}
	
	@Override
	public String getPattern() {
		return "bjp";
	}
	
	Argument a1, a2, a3;
	
	public Bjp(){
		
	}
	
	public Bjp(Argument a1, Argument a2, Argument a3){
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		int mask = super.getValue(runtime, a1);
		int check = super.getValue(runtime, a2);
		int flag = 0;
		if ((runtime.getFlags() & mask) != 0) flag = 1;
		if (flag == check) {
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

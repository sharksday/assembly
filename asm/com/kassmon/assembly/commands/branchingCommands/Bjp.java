package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.*;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Bjp extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = super.getArg(t), a2 = super.getArg(t), a3 = super.getArg(t);
		if (a1 == null) throw new ParcerException("bjp : argument error : null arg");
		if (a2 == null) throw new ParcerException("bjp : argument error : null arg");
		if (a3 == null) throw new ParcerException("bjp : argument error : null arg");
		if (a1.isLabel()) throw new ParcerException("bjp : argument error : illegal label");
		if (a2.isLabel()) throw new ParcerException("bjp : argument error : illegal label");
		if (!a3.isLabel()) throw new ParcerException("bjp : argument error : illegal arg type");
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

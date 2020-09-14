package com.kassmon.assembly.commands.mathCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Xor extends Command {
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t), a2 = super.getArg(t), a3 = super.getArg(t);
		if (a1 == null) throw new ParcerException("xor : argument error : null arg");
		if (a2 == null) throw new ParcerException("xor : argument error : null arg");
		if (a3 == null) throw new ParcerException("xor : argument error : null arg");
		if (a1.isLabel()) throw new ParcerException("xor : argument error : illegal label");
		if (a2.isLabel()) throw new ParcerException("xor : argument error : illegal label");
		if (a3.isLabel()) throw new ParcerException("xor : argument error : illegal label");
		if (a3.isNumber()) throw new ParcerException("xor : argument error : illegal number");
		return new Xor (a1, a2, a3);
	}
	
	@Override
	public String getPattern() {
		return "xor";
	}
	
	private Argument a1;
	private Argument a2;
	private Argument a3;
	
	public Xor (Argument a1, Argument a2, Argument a3) {
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
	}
	
	public Xor () {
		
	}
	
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		int value = super.getValue(runtime, a1) ^ super.getValue(runtime, a2);
		super.setValue(runtime, a3, value);
	}
	
}

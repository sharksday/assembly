package com.kassmon.assembly.commands.busCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Psh extends Command {
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t), a2 = super.getArg(t);
		if (a1 == null) throw new ParcerException("psh : argument error : null arg");
		if (a2 == null) throw new ParcerException("psh : argument error : null arg");
		if (a1.isLabel()) throw new ParcerException("psh : argument error : illegal label");
		if (a2.isLabel()) throw new ParcerException("psh : argument error : illegal label");
		return new Psh(a1, a2);
	}
	
	@Override
	public String getPattern() {
		return "psh";
	}
	
	private Argument a1;
	private Argument a2;
	
	public Psh(Argument a1, Argument a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
	
	public Psh() {
		
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		for (ExternalBusItem obj: runtime.getBus()) {
			obj.push(super.getValue(runtime, a1), super.getValue(runtime, a2));
		}
	}
	
}

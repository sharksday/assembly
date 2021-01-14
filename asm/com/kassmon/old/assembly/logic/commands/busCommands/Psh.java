package com.kassmon.old.assembly.logic.commands.busCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Psh extends Command {
	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
		Argument a1 = super.getArg(t), a2 = super.getArg(t);
		if (a1 == null) throw new ParserException("psh : argument error : null arg");
		if (a2 == null) throw new ParserException("psh : argument error : null arg");
		if (a1.isLabel()) throw new ParserException("psh : argument error : illegal label");
		if (a2.isLabel()) throw new ParserException("psh : argument error : illegal label");
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

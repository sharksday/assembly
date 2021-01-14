package com.kassmon.old.assembly.logic.commands.outputCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Och extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParserException("och : argument error : null argument");
		if (a1.isLabel()) throw new ParserException("och : argument error : illegal argument type");
		return new Och(a1);
	}
	
	@Override
	public String getPattern() {
		return "och";
	}
	
	private Argument a1;
	
	public Och(Argument a1){
		this.a1 = a1;
	}
	
	public Och() {
		
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		com.kassmon.old.assembly.logic.io.OutputControler.output(String.valueOf((char) super.getValue(runtime, a1)));
	}
	
}

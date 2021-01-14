package com.kassmon.old.assembly.logic.commands.outputCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.program.Argument;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Onm extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParserException("onm : argument error : null argument");
		if (a1.isLabel()) throw new ParserException("onm : argument error : illegal argument type");
		return new Onm(a1);
	}
	
	@Override
	public String getPattern() {
		return "onm";
	}
	
	private Argument a1;
	
	public Onm(Argument a1){
		this.a1 = a1;
	}
	
	public Onm() {
		
	}
	
	@Override
	public void run(IRuntime runtime) throws RuntimeException {
		com.kassmon.old.assembly.logic.io.OutputControler.output(String.valueOf(super.getValue(runtime, a1)));
	}
	
}

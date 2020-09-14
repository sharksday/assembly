package com.kassmon.assembly.commands.outputCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Onm extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("onm : argument error : null argument");
		if (a1.isLabel()) throw new ParcerException("onm : argument error : illegal argument type");
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
		com.kassmon.assembly.io.OutputControler.output(String.valueOf(super.getValue(runtime, a1)));
	}
	
}

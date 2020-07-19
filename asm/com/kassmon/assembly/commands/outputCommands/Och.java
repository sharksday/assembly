package com.kassmon.assembly.commands.outputCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Och extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		Argument a1 = super.getArg(t);
		if (a1 == null) throw new ParcerException("och : argument error : null argument");
		if (a1.isLabel()) throw new ParcerException("och : argument error : illegal argument type");
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
	public void run(RunTime runtime) throws RuntimeException {
		com.kassmon.assembly.io.OutputControler.output(String.valueOf((char) super.getValue(runtime, a1)));
	}
	
}

package com.kassmon.assembly.commands.busCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.BusNoOutputExcption;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Pul extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t), a2 = super.getArg(t);
		if (a1 == null) throw new ParcerException("pul : argument error : null arg");
		if (a2 == null) throw new ParcerException("pul : argument error : null arg");
		if (a1.isLabel()) throw new ParcerException("pul : argument error : illegal label");
		if (a2.isLabel()) throw new ParcerException("pul : argument error : illegal label");
		return new Pul(a1, a2);
	}
	
	@Override
	public String getPattern() {
		return "pul";
	}
	
	private Argument a1;
	private Argument a2;
	
	public Pul(Argument a1, Argument a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
	
	public Pul() {
		
	}
	
	
	
	@Override
	public void run(RunTime runtime) throws RuntimeException {
		for (ExternalBusItem obj: runtime.getBus()) {
			try {
				super.setValue(runtime, a2, obj.pull(super.getValue(runtime, a1)));
			} catch (BusNoOutputExcption e) {
				break;	
			}
		}
		
	}
	
}
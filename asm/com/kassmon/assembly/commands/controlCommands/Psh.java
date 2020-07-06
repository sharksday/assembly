package com.kassmon.assembly.commands.controlCommands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.externalBuss.old.ExternalBusItem;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.EntryType;

public class Psh extends Command {
	private String path = "com.kassmon.assembly.program.commands.Psh";
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = super.getArg(t);
		if (a1 != null) if (!a1.isLabel()) return new Psh(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return new Nop();
	}
	
	@Override
	public String getPattern() {
		return "psh";
	}
	
	private Argument a1;
	
	public Psh() {
		
	}
	
	public Psh(Argument a1) {
		this.a1 = a1;
	}
	
	@Override
	public void run(RunTime runtime) {
		if (a1.isNumber()) {
			for (ExternalBusItem obj: runtime.getBus()) {
				obj.push(runtime.getAdr(), Integer.parseInt(a1.getValue()));
			}
		}else {
			if (a1.getValue().equals("acc")) {
				for (ExternalBusItem obj: runtime.getBus()) {
					obj.push(runtime.getAdr(), runtime.getAcc());
				}
			}else if (a1.getValue().equals("adr")) {
				for (ExternalBusItem obj: runtime.getBus()) {
					obj.push(runtime.getAdr(), runtime.getAdr());
				}
			}else if (a1.getValue().contains("a")) {
				if (runtime.getALength() > Integer.parseInt(a1.getValue().substring(1))) {
					for (ExternalBusItem obj: runtime.getBus()) {
						obj.push(runtime.getAdr(), runtime.getA(Integer.parseInt(a1.getValue().substring(1))));
					}
				}else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
				}
			}
		}
	}
	
}

package com.kassmon.assembly.commands.controlCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.EntryType;

import static com.kassmon.library.log.Log.newLogEntry;

public class Mov extends Command {
	private String path = "com.kassmon.assembly.program.commands.Mov";
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		Argument a1 = null, a2 = null;
		Argument temp = super.getArg(t);
		if (temp != null) {
			if (!temp.isLabel()) {
				if (!temp.isNumber() & temp.getValue().equals("pc")) {
					newLogEntry(EntryType.ERROR, path, "not a valid argument");
					return new Nop();
				}
				a1 = temp;
			}else {
				newLogEntry(EntryType.ERROR, path, "not a valid argument");
				return new Nop();
			}
			temp = super.getArg(t);
			if (temp != null) {
				if (!temp.isLabel()) {
					if (!temp.isNumber()) {
						a2 = temp;
					}else {
						newLogEntry(EntryType.ERROR, path, "not a valid argument");
						return new Nop();
					}
				}else {
					newLogEntry(EntryType.ERROR, path, "not a valid argument");
					return new Nop();
				}
				return new Mov(a1, a2);
			}else {
				newLogEntry(EntryType.ERROR, path, "not a valid argument");
				return new Nop();
			}
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument");
			return new Nop();
		}
	}
	
	@Override
	public String getPattern() {
		return "mov";
	}
	
	private Argument a1, a2;
	
	public Mov(Argument a1, Argument a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
	
	public Mov() {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		if (a2.getValue().equals("acc")) {
			runtime.setAcc(super.getValue(runtime, a1));
		}else if (a2.getValue().equals("adr")) {
			runtime.setAdr(super.getValue(runtime, a1));
		}else if (a2.getValue().contains("a")) {
			if (runtime.getALength() > Integer.parseInt(a2.getValue().substring(1))) {
				runtime.setA(Integer.parseInt(a2.getValue().substring(1)), super.getValue(runtime, a1));
			}else {
				newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
			}
		}else {
			newLogEntry(EntryType.ERROR, path, "RunTime Error: destination error");
		}
	}
	
}
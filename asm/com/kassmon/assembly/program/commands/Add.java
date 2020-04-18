package com.kassmon.assembly.program.commands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.Tokenizer;
import com.kassmon.library.log.EntryType;

public class Add extends Command {
	private String path = "com.kassmon.assembly.program.commands.Add";
	
	@Override
	public Command parse(Tokenizer t) {
		Argument a1 = super.getArg(t);
		if (!a1.isLabel()) return new Add(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return null;
	}

	@Override
	public String getPattern() {
		return "add";
	}
	
	private Argument a1;
	
	public Add (Argument a1) {
		this.a1 = a1;
	}
	
	public Add() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(RunTime runtime) {
		if (a1.isNumber()) {
			runtime.setAcc(runtime.getAcc() + Integer.parseInt(a1.getValue()));
		}else {
			if (a1.getValue().equals("acc")) {
				runtime.setAcc(runtime.getAcc() * 2);
			}else if (a1.getValue().equals("adr")) {
				runtime.setAcc(runtime.getAcc() + runtime.getAdr());
			}else if (a1.getValue().contains("a")) {
				if (runtime.getALength() > Integer.parseInt(a1.getValue().substring(1))) {
					runtime.setAcc(runtime.getAcc() + runtime.getA(Integer.parseInt(a1.getValue())));
				}
			}
		}
	}

}

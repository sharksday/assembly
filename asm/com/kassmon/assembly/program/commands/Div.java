package com.kassmon.assembly.program.commands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.Tokenizer;
import com.kassmon.library.log.EntryType;

public class Div extends Command {
	private String path = "com.kassmon.assembly.program.commands.Div";
	
	@Override
	public Command parse(Tokenizer t) {
		Argument a1 = super.getArg(t);
		if (!a1.isLabel()) return new Div(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return null;
	}

	@Override
	public String getPattern() {
		 return "div";
	}
	
	private Argument a1;
	
	public Div (Argument a1) {
		this.a1 = a1;
	}
	
	public Div() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(RunTime runtime) {
		if (a1.isNumber()) {
			runtime.setAcc(runtime.getAcc() / Integer.parseInt(a1.getValue()));
		}else {
			if (a1.getValue().equals("acc")) {
				runtime.setAcc(1);
			}else if (a1.getValue().equals("adr")) {
				runtime.setAcc(runtime.getAcc() / runtime.getAdr());
			}else if (a1.getValue().contains("a")) {
				if (runtime.getALength() > Integer.parseInt(a1.getValue().substring(1))) {
					runtime.setAcc(runtime.getAcc() / runtime.getA(Integer.parseInt(a1.getValue().substring(1))));
				}else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
				}
			}
		}
	}
	
}

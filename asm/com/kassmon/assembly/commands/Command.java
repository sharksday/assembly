package com.kassmon.assembly.commands;

import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.EntryType;
import com.kassmon.library.tokenizers.Token;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;

public abstract class Command {
	private String path = "com.kassmon.assembly.program.commands.Command";
	
	public Argument getArg(CommandTokenizer t) {
		Token token = t.getNextToken();
		switch (token.getType()){
			case "mem":
				return new Argument (false, false, token.getToken());
			case "value":
				return new Argument (true, false, token.getToken());
			case "label":
				return new Argument (false, true, token.getToken());
			case "hex":
				return new Argument (true, false, String.valueOf(Integer.parseInt(token.getToken().substring(2), 16)));
			case "char":
				int charter = token.getToken().substring(1).toCharArray()[0];
				return new Argument (true, false, String.valueOf(charter));
		}
		return null;	
	}
	
	public int getValue(RunTime runtime, Argument arg) {
		if (arg.isNumber()) {
			return Integer.parseInt(arg.getValue());
		}else {
			if (arg.getValue().equals("acc")) {
				return runtime.getAcc();
			}else if (arg.getValue().equals("adr")) {
				return runtime.getAdr();
			}else if (arg.getValue().contains("a")) {
				if (runtime.getALength() > Integer.parseInt(arg.getValue().substring(1))) {
					return runtime.getA(Integer.parseInt(arg.getValue().substring(1)));
				}else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
				}
			}
		}
		return 0;
	}
	
	public void setFlags(RunTime runtime) {
		if (runtime.getAcc() == 0) {
			runtime.setZero(true);
			runtime.setNegative(false);
		}else if (runtime.getAcc() < 0) {
			runtime.setZero(false);
			runtime.setNegative(true);
		}
	}
	
	public abstract Command parse(CommandTokenizer t);
	
	public abstract String getPattern();
	
	public abstract void run(RunTime runtime);
	
}
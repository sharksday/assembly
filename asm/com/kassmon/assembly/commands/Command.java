package com.kassmon.assembly.commands;

import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.tokenizers.Token;
import com.kassmon.assembly.exceptions.*;
import com.kassmon.assembly.exceptions.RuntimeException;

public abstract class Command {
	
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
			case "binary":
				return new Argument (true, false, String.valueOf(Integer.parseInt(token.getToken().substring(2), 2)));
			case "char":
				int charter = token.getToken().substring(1).toCharArray()[0];
				return new Argument (true, false, String.valueOf(charter));
		}
		return null;	
	}
	
	public int getValue(IRuntime runtime, Argument arg) throws RuntimeException {
		if (arg.isNumber()) {
			return Integer.parseInt(arg.getValue());
		}else if (arg.getValue().contains("a")) {
			if (runtime.SiseOfA() > Integer.parseInt(arg.getValue().substring(1))) {
				return runtime.getA(Integer.parseInt(arg.getValue().substring(1)));
			}else {
				throw new RuntimeException("RunTime Error: Location out of bounds");
			}
		}else {
			throw new RuntimeException("RunTime Error: Location error");
		}
	}
	
	
	public void setValue(IRuntime runtime, Argument arg, int value) {
		if (arg.getValue().contains("a")) {
			int aLoc = Integer.parseInt(arg.getValue().substring(1));
			if (runtime.SiseOfA() > aLoc) {
				runtime.setA(aLoc, value);
			}
		}
	}
	
	public abstract Command parse(CommandTokenizer t) throws ParcerException;
	
	public abstract String getPattern();
	
	public abstract void run(IRuntime runtime) throws RuntimeException;
	
}
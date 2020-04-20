package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.tokenizer.Tokenizer;
import com.kassmon.library.tokenizers.Token;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;

public abstract class Command {
	
	public Argument getArg(Tokenizer t) {
		Token token = t.getNextToken();
		if (token.getType().equals("mem")) {
			return new Argument (false, false, token.getToken());
		}else if (token.getType().equals("value")){
			return new Argument (true, false, token.getToken());
		}else if (token.getType().equals("label")) {
			return new Argument (false, true, token.getToken());
		}else if (token.getType().equals("")) {
			return null;
		}else {
			return null;
		}	
	}
	
	public abstract Command parse(Tokenizer t);
	
	public abstract String getPattern();
	
	public abstract void run(RunTime runtime);
	
}

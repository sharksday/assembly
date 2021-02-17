package com.kassmon.assembly.buildTime.parser;

import static com.kassmon.assembly.runTime.envirment.data.argument.ArgumentType.*;

import com.kassmon.assembly.buildTime.tokenizer.*;
import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.TokenizerExcption;
import com.kassmon.assembly.runTime.envirment.Program.ProgramLine;
import com.kassmon.assembly.runTime.envirment.data.argument.Argument;

public abstract class Parser {
	
	public Argument getArgument (Token token) throws ParserException {
		switch (token.getTokenType()) {
			case MEM:
				return new Argument(MEM_LOC, token.getTokenData());
			case HEX:
				return new Argument(NUMBER, String.valueOf(Integer.parseInt(token.getTokenData().substring(2), 16)));
			case BINARY:
				return new Argument(NUMBER, String.valueOf(Integer.parseInt(token.getTokenData().substring(2), 2)));
			case CHAR:
				int charter = token.getTokenData().substring(1).toCharArray()[0];
				return new Argument (NUMBER, String.valueOf(charter));
			case INT:
				return new Argument (NUMBER, token.getTokenData());
			case LABEL:
				if (token.getTokenData().contains("!")) return new Argument(LABEL, token.getTokenData().substring(1));
				return new Argument(LABEL, token.getTokenData());
			case COMMENT:
				return new Argument(COMMENT, token.getTokenData());
			case BOOLEAN:
				if (token.getTokenData().equals("true")) return new Argument(NUMBER, "1");
				if (token.getTokenData().equals("false")) return new Argument(NUMBER, "0");
			default:
				throw new  ParserException("Argument error");
		}
	}
	
	public abstract void addParser(Parser parser);
	public abstract ProgramLine Parse(Token command, Tokenizer tokenizer) throws ParserException, TokenizerExcption;
	
	
}

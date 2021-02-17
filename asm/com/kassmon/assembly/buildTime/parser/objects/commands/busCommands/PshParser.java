package com.kassmon.assembly.buildTime.parser.objects.commands.busCommands;

import com.kassmon.assembly.buildTime.parser.Parser;
import com.kassmon.assembly.buildTime.tokenizer.Token;
import com.kassmon.assembly.buildTime.tokenizer.TokenType;
import com.kassmon.assembly.buildTime.tokenizer.Tokenizer;
import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.TokenizerExcption;
import com.kassmon.assembly.runTime.envirment.Program.ProgramLine;
import com.kassmon.assembly.runTime.objects.commands.busCommands.Psh;

public class PshParser extends Parser {
	
	private Parser nextParser;
	
	@Override
	public void addParser(Parser parser) {
		if (nextParser == null) {
			nextParser = parser;
		} else {
			nextParser.addParser(parser);
		}
	}
	
	@Override
	public ProgramLine Parse(Token command, Tokenizer tokenizer) throws ParserException, TokenizerExcption {
		if (command.getTokenData().equals("psh")) {
			Token t = tokenizer.getNextToken();
			if (t.getTokenType().equals(TokenType.COMMAND)) throw new ParserException("Argument Error");
			if (t.getTokenType().equals(TokenType.LABEL)) throw new ParserException("Argument Error");
			if (t.getTokenType().equals(TokenType.COMMENT)) throw new ParserException("Argument Error");
			return new ProgramLine(new Psh(getArgument(t)));
		}
		if (nextParser != null) return nextParser.Parse(command, tokenizer);
		throw new ParserException("no parser for command");
	}
	
}

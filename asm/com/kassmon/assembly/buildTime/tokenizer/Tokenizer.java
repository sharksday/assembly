package com.kassmon.assembly.buildTime.tokenizer;

import static com.kassmon.assembly.buildTime.tokenizer.TokenType.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

import com.kassmon.assembly.exceptions.TokenizerExcption;

public class Tokenizer {
	
	String input = "";
	private ArrayList<TokenData> tokenData;
	
	public Tokenizer (String[] commandList) {
		
		for (String command: commandList) {
			tokenData.add(new TokenData("^(" + command + "\\b)", TokenType.COMMAND));
		}
		
		tokenData.add(new TokenData("^(null\\b)", MEM));
		tokenData.add(new TokenData("^(a[0-9]+\\b)", MEM));
		tokenData.add(new TokenData("(0x[0-9abcdef]+\\b)", HEX));
		tokenData.add(new TokenData("(0b[01]+\\b", BINARY));
		tokenData.add(new TokenData("^(-[a-zA-Z]\\b)", CHAR));
		tokenData.add(new TokenData("^([0-9]+\\b)", INT));
		tokenData.add(new TokenData("^(![a-zA-Z]+\\b", LABEL));
		tokenData.add(new TokenData("^([a-zA-Z]+\\b", LABEL));
		tokenData.add(new TokenData("^(\\/\\/.*\\/\\/)", COMMENT));
		tokenData.add(new TokenData("^(true\\b)", BOOLEAN));
		tokenData.add(new TokenData("^(false\\b)", BOOLEAN));
		
		
		
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public boolean hasNextToken(){
		return !input.isEmpty();
	}
	
	public Token getNextToken() throws TokenizerExcption{
		if (hasNextToken()){
			input = input.trim();
			for (TokenData data: tokenData){
				Matcher matcher = data.getPattern().matcher(input);
				if (matcher.find()){
					String obj = matcher.group().trim();
					input = matcher.replaceFirst("");
					return (new Token(obj, data.getTokenType()));
				} else {
					throw new TokenizerExcption("invalid input");
				}
			}
		}
		throw new TokenizerExcption("no Token");
	}
	
}

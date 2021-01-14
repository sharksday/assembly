package com.kassmon.assembly.buildTime.tokenizer;

import java.util.regex.Pattern;

public class TokenData {
	
	
	private Pattern pattern;
	private TokenType tokenType;
	
	public TokenData(Pattern pattern, TokenType tokenType) {
		this.pattern = pattern;
		this.tokenType = tokenType;
	}
	
	public TokenData(String pattern, TokenType tokenType) {
		this.pattern = Pattern.compile(pattern);
		this.tokenType = tokenType;
	}
	
	public Pattern getPattern() {
		return pattern;
	}

	public TokenType getTokenType() {
		return tokenType;
	}
	
	
	
}

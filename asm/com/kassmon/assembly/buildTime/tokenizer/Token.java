package com.kassmon.assembly.buildTime.tokenizer;
public class Token {
	
	private String tokenData;
	private com.kassmon.assembly.buildTime.tokenizer.TokenType tokenType;
	
	public Token(String tokenData, com.kassmon.assembly.buildTime.tokenizer.TokenType tokenType) {
		this.tokenData = tokenData;
		this.tokenType = tokenType;
	}

	public String getTokenData() {
		return tokenData;
	}

	public com.kassmon.assembly.buildTime.tokenizer.TokenType getTokenType() {
		return tokenType;
	}
	
	
	
}

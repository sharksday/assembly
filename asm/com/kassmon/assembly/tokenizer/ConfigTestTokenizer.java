package com.kassmon.assembly.tokenizer;

import java.util.regex.Pattern;

public class ConfigTestTokenizer extends com.kassmon.library.tokenizers.Tokenizer{
	//super.addPattern(Pattern.compile("^(\\b)"), "");
	
	public ConfigTestTokenizer() {
		super.addPattern(Pattern.compile("^(ram\\b)"), "bus");
		super.addPattern(Pattern.compile("^(stack\\b)"), "bus");
		
		super.addPattern(Pattern.compile("^([0-9]+\\b)"), "value");
		super.addPattern(Pattern.compile("^(0x[0-9abcdef][0-9abcdef]\\b)"), "hex");
		
	}
	
}

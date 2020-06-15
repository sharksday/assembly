package com.kassmon.assembly.tokenizer;

import java.util.regex.Pattern;

import com.kassmon.library.tokenizers.*;

public class ConfigTokenizer extends Tokenizer {
	
	public ConfigTokenizer () {
		
		super.addPattern(Pattern.compile("^(ram)"), "control");
		super.addPattern(Pattern.compile("^(stack)"), "control");
		super.addPattern(Pattern.compile("^(textDisplay)"), "control");
		super.addPattern(Pattern.compile("^([0-9]+)"), "value");
		
	}
	
}

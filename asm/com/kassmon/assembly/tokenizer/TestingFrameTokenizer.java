package com.kassmon.assembly.tokenizer;

import java.util.regex.Pattern;

public class TestingFrameTokenizer extends com.kassmon.library.tokenizers.Tokenizer{
	//super.addPattern(Pattern.compile("^(\\b)"), "");
	
	public TestingFrameTokenizer () {
		
		super.addPattern(Pattern.compile("^(run\\b)"), "command");
		super.addPattern(Pattern.compile("^(open\\b)"), "command");
		super.addPattern(Pattern.compile("^(save\\b)"), "command");
		super.addPattern(Pattern.compile("^(run\\b)"), "command");
		super.addPattern(Pattern.compile("^(exit\\b)"), "command");
		super.addPattern(Pattern.compile("^(clear\\b)"), "command");
		super.addPattern(Pattern.compile("^(bus\\b)"), "command");
		
		super.addPattern(Pattern.compile("^(console\\b)"), "componet");
		super.addPattern(Pattern.compile("^(program\\b)"), "componet");
		super.addPattern(Pattern.compile("^(make\\b)"), "componet");
		super.addPattern(Pattern.compile("^(remove\\b)"), "componet");
		super.addPattern(Pattern.compile("^(list\\b)"), "componet");
		
		super.addPattern(Pattern.compile("^(-v\\b)"), "flag");
		super.addPattern(Pattern.compile("^(-b\\b)"), "flag");
		
		super.addPattern(Pattern.compile("^(ram\\b)"), "bus");
		
		super.addPattern(Pattern.compile("^([0-9]+\\b)"), "value");
		super.addPattern(Pattern.compile("^(0x[0-9abcdef][0-9abcdef]\\b)"), "hex");
		
	}
	
	
	
}

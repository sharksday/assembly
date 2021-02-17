package com.kassmon.assembly.buildTime;

import com.kassmon.assembly.buildTime.parser.Parser;
import com.kassmon.assembly.buildTime.parser.objects.commands.branchingCommands.*;
import com.kassmon.assembly.buildTime.parser.objects.commands.busCommands.*;
import com.kassmon.assembly.buildTime.parser.objects.commands.controlCommands.*;
import com.kassmon.assembly.buildTime.parser.objects.commands.mathCommands.*;
import com.kassmon.assembly.buildTime.parser.objects.commands.outputCommands.*;
import com.kassmon.assembly.buildTime.tokenizer.Tokenizer;
import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.exceptions.TokenizerExcption;
import com.kassmon.assembly.runTime.envirment.Program.Program;

public class BuildController {
	
	private Tokenizer tokenizer;
	private Parser parser;
	private String[] commandList = {
			"nop","jmp","jsr","lda","ldb","mov","rsr","sta","stb",
			"add","and","div","mod","mul","not","or","sub","xor",
			"och","olf","onm",
			"clk","psh","pul",
			"cmp","jez","jnz","jgz","jlz"};
	
	public BuildController() {
		tokenizer = new Tokenizer(this.commandList);
		//control parsers
		parser = new NopParser();
		parser.addParser(new JmpParser());
		parser.addParser(new JsrParser());
		parser.addParser(new LdaParser());
		parser.addParser(new LdbParser());
		parser.addParser(new MovParser());
		parser.addParser(new RsrParser());
		parser.addParser(new StaParser());
		parser.addParser(new StbParser());
		//math parsers
		parser.addParser(new AddParser());
		parser.addParser(new AndParser());
		parser.addParser(new DivParser());
		parser.addParser(new ModParser());
		parser.addParser(new MulParser());
		parser.addParser(new NotParser());
		parser.addParser(new OrParser());
		parser.addParser(new SubParser());
		parser.addParser(new XorParser());
		
		//output parser
		parser.addParser(new OchParser());
		parser.addParser(new OlfParser());
		parser.addParser(new OnmParser());
		
		//bus parser
		parser.addParser(new ClkParser());
		parser.addParser(new PshParser());
		parser.addParser(new PulParser());
		
		//branching parser
		parser.addParser(new CmpParser());
		parser.addParser(new JezParser());
		parser.addParser(new JgzParser());
		parser.addParser(new JlzParser());
		parser.addParser(new JnzParser());
		
	}
	
	public Tokenizer getTokenizer() {
		return tokenizer;
	}

	public void setInput(String input) {
		tokenizer.setInput(input);
	}
	
	public Program getProgram() {
		Program p = new Program();
		while (tokenizer.hasNextToken()) {
			try {
				p.addProgramLine(parser.Parse(tokenizer.getNextToken(), tokenizer));
			} catch (ParserException | TokenizerExcption e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
}
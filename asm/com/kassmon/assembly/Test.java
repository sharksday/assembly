package com.kassmon.assembly;

import com.kassmon.assembly.program.LineTypes;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.program.ProgramLine;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Test {
	private Tokenizer t = new Tokenizer();
	private String programString;
	
	public Test (String programString) {
		this.programString = programString;
	}
	
	public Program testTokenizer() {
		t.setInput(programString);
		Program pro = new Program();
		
		while (t.hasNextToken()) {
			ProgramLine line = t.getNextLine();
			
			if (line.getLineType().equals(LineTypes.LABEL)) {
				System.out.println(line.getLabel());
				pro.makeProgramLine(line);
			} else if (line.getLineType().equals(LineTypes.COMMAND)){
				pro.makeProgramLine(line);
				System.out.print(line.getCommandType().toString());
				System.out.print(" ");
				if (line.hasArguments()) {
					for (int i = 0; i < line.argumentNumber(); i++){
						System.out.print(line.getArgument(i).getValue());
						System.out.print(" ");
					}
				}
				System.out.println("");
			} else {
				return null;
			}
		}
		
		
		return pro;
	}
	
	public Program testtokenizer() {
		Program pro = new Program();
		t.setInput(programString);
		while (t.hasNextToken()) {
			ProgramLine temp = t.getNextLine();
			if (temp.getLineType().equals(LineTypes.LABEL)) {
				System.out.println(temp.getLabel());
			} else if (temp.getCommandType() == null) return null;
			pro.makeProgramLine(temp);
			System.out.print(temp.getCommandType().toString());
			System.out.print(" ");
			if (temp.hasArguments()) {
				for (int i = 0; i < temp.argumentNumber(); i++){
					System.out.print(temp.getArgument(i).getValue());
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
		return pro;
	}
	
}

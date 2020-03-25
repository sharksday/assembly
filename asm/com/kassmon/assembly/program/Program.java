package com.kassmon.assembly.program;

import java.util.ArrayList;

public class Program {
	
	private ArrayList<ProgramLine> programLines;
	
	public Program () {
		this.programLines = new ArrayList<>();
	}
	
	public ProgramLine getProgramLine(int lineNumber){
		return programLines.get(lineNumber);
	}
	
	public void makeProgramLine(ProgramLine line) {
		programLines.add(line);
	}
	
	public void makeProgramLine(String label){
		programLines.add(new ProgramLine(label));
	}
	
	public int getSize(){
		return this.programLines.size();
	}
	
}
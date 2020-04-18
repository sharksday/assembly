package com.kassmon.assembly.program;

import java.util.ArrayList;

public class Program {
	
	private ArrayList<ProgramLine> programLines;
	
	public Program() {
		this.programLines = new ArrayList<>();
	}
	
	public void addProgramLine(ProgramLine line) {
		this.programLines.add(line);
	}
	
	public ProgramLine getProgramLine(int i) {
		return this.programLines.get(i);
	}
	
	public int getProgramLength() {
		return this.programLines.size();
	}
	
}

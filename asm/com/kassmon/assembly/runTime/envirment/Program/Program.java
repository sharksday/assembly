package com.kassmon.assembly.runTime.envirment.Program;

import java.util.ArrayList;

import com.kassmon.old.assembly.logic.program.ProgramLine;

public class Program {
	
	private static ArrayList<ProgramLine> programLines = new ArrayList<>();;
	
	public Program() {
		
	}
	
	public void addProgramLine(ProgramLine line) {
		Program.programLines.add(line);
	}
	
	public ProgramLine getProgramLine(int i) {
		return Program.programLines.get(i);
	}
	
	public int getProgramLength() {
		return Program.programLines.size();
	}
	
	public void clearProgram() {
		Program.programLines = new ArrayList<>();
	}
	
}

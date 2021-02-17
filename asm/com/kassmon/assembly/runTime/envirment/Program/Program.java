package com.kassmon.assembly.runTime.envirment.Program;

import java.util.ArrayList;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;

public class Program {
	
	private ArrayList<ProgramLine> programLines = new ArrayList<>();;
	
	public Program() {
		
	}
	
	public void addProgramLine(ProgramLine line) {
		programLines.add(line);
	}
	
	public ProgramLine getProgramLine(int i) {
		return programLines.get(i);
	}
	
	public int getProgramLength() {
		return programLines.size();
	}
	
	public void clearProgram() {
		programLines = new ArrayList<>();
	}
	
	public void run(Memory memory, Flags flags) throws RuntimeException {
		ProgramLine line = programLines.get(memory.getPc());
		if (line.isCommand()) {
			line.getCommand().run(memory, flags);
		}
	}
	
}

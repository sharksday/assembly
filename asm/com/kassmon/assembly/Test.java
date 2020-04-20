package com.kassmon.assembly;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.program.ProgramLine;
import com.kassmon.assembly.program.commands.*;

public class Test {
	
	public static void main(String[] args) {
		com.kassmon.library.log.Log.setReport(true);
		RunTime r = new RunTime();
		Program p = new Program();
		
		p.addProgramLine(new ProgramLine(new Add(new Argument (false, false, "a19")))); 
		
		r.setProgram(p);
		r.test();
		
		
		
		
	}
	
}

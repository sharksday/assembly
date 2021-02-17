package com.kassmon.assembly.runTime;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.BusMaster;
import com.kassmon.assembly.runTime.envirment.Program.Program;
import com.kassmon.assembly.runTime.envirment.data.memory.*;

public class RuntimeController {
	
	private Program program;
	
	private Flags flags;
	private Memory memory;
	
	public RuntimeController(Program program, BusMaster busMaster) {
		flags = new Flags();
		this.program = program;
		memory = new Memory(program, busMaster);
	}
	
	public void runProgram () throws RuntimeException {
		while (memory.getPc() != -1) {
			if (memory.getPc() < program.getProgramLength()) {
				program.run(memory, flags);
				memory.setPc(memory.getPc() + 1);
			}else {
				memory.setPc(-1);
			}
			
		}
	}
	
}

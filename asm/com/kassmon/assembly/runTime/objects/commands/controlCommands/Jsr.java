package com.kassmon.assembly.runTime.objects.commands.controlCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.Program.Program;
import com.kassmon.assembly.runTime.envirment.data.argument.Argument;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Jsr extends Command {
	
	private Argument a1;
	private Program p;
	
	public Jsr (Argument a1) {
		this.a1 = a1;
	}
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		memory.pushStack(memory.getPc());
		for (int i = 0; i < p.getProgramLength(); i++) {
			if (!p.getProgramLine(i).isCommand()) {
				if (p.getProgramLine(i).getLabel().equals(a1.getValue())) memory.setPc(i);
			}
		}
	}
	
}

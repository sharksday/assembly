package com.kassmon.assembly.runTime.objects.commands.controlCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Rsr extends Command {
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		memory.setPc(memory.popStack());
	}
	
}

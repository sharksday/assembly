package com.kassmon.assembly.runTime.objects.commands.outputCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.envirment.io.Output;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Olf extends Command {
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		Output.event(System.lineSeparator());
	}
	
}

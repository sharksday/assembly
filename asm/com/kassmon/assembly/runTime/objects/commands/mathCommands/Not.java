package com.kassmon.assembly.runTime.objects.commands.mathCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Not extends Command {
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		memory.setRegA(memory.getRegA() * (-1));
	}
	
}

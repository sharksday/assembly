package com.kassmon.assembly.runTime.objects.commands.busCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.argument.Argument;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Pul extends Command {
	
	public Pul (Argument a1) {
		this.a1 = a1;
	}
	
	private Argument a1;
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		this.setValue(memory, a1, memory.getBus().pull(memory.getRegB()));
	}
	
}

package com.kassmon.assembly.runTime.objects.commands.outputCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.argument.Argument;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.envirment.io.Output;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Onm extends Command {
	
	private Argument a1;
	
	public Onm(Argument a1) {
		this.a1 = a1;
	}
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		Output.event(String.valueOf(getValue(memory, a1)));
	}
	
}

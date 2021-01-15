package com.kassmon.assembly.runTime.objects.commands.busCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Clk extends Command {
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		memory.getBus().clock();
	}
	
}

package com.kassmon.assembly.runTime.objects.commands.mathCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.argument.Argument;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.objects.commands.Command;


public class Add extends Command {
	
	public Add (Argument a1) {
		this.a1 = a1;
	}
	
	private Argument a1;
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		memory.setRegA(memory.getRegA() + super.getValue(memory, a1));
	}
	
}

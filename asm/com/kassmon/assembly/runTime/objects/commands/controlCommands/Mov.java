package com.kassmon.assembly.runTime.objects.commands.controlCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.argument.Argument;
import com.kassmon.assembly.runTime.envirment.data.memory.*;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Mov extends Command {
	
	private Argument a1, a2;
	
	public Mov (Argument a1, Argument a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		setValue(memory, a2, getValue(memory, a1));
	}
	
}

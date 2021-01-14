package com.kassmon.assembly.runTime.objects.commands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.argument.*;
import com.kassmon.assembly.runTime.envirment.data.memory.*;

public abstract class Command {
	
	public void setValue (Memory memory, Argument arg, int value) throws RuntimeException {
		if (arg.getType().equals(ArgumentType.MEM_LOC)) {
			int loc = Integer.parseInt(arg.getValue().substring(1));
			if (arg.getValue().contains("a")) {
				if (memory.sizeOfA() > loc) {
					memory.setA(loc, value);
				}else {
					throw new RuntimeException("RunTime Error: Location out of bounds");
				}
			}
		}
	}
	
	public int getValue (Memory memory, Argument arg) throws RuntimeException {
		switch (arg.getType()) {
			case NUMBER:
				return Integer.parseInt(arg.getValue()); 
			case MEM_LOC:
				int loc = Integer.parseInt(arg.getValue().substring(1));
				if (arg.getValue().contains("a")) { 
					if (memory.sizeOfA() > loc) return memory.getA(loc);
					throw new RuntimeException("RunTime Error: Location out of bounds");
				}
				throw new RuntimeException("RunTime Error: Location error");
			default:
				return 0;
				
		}
	}
	
	public abstract void run(Memory memory, Flags flags) throws RuntimeException;
	
}
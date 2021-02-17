package com.kassmon.assembly.runTime.objects.commands.branchingCommands;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.runTime.envirment.data.memory.Flags;
import com.kassmon.assembly.runTime.envirment.data.memory.Memory;
import com.kassmon.assembly.runTime.objects.commands.Command;

public class Cmp extends Command{
	
	
	
	@Override
	public void run(Memory memory, Flags flags) throws RuntimeException {
		int check = memory.getRegA();
		if (check == 0) {
			flags.setEz(true);
			flags.setGtz(false);
			flags.setLtz(false);
			flags.setNez(false);
		}else if (check < 0) {
			flags.setEz(false);
			flags.setGtz(false);
			flags.setLtz(true);
			flags.setNez(true);
		}else if (check > 0) {
			flags.setEz(false);
			flags.setGtz(true);
			flags.setLtz(false);
			flags.setNez(true);
		}
	}
	
}

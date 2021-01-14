package com.kassmon.old.assembly.util;

import com.kassmon.old.assembly.logic.commands.branchingCommands.*;
import com.kassmon.old.assembly.logic.commands.busCommands.*;
import com.kassmon.old.assembly.logic.commands.controlCommands.*;
import com.kassmon.old.assembly.logic.commands.mathCommands.*;
import com.kassmon.old.assembly.logic.commands.outputCommands.*;

public class Vars {
	
	public static final com.kassmon.old.assembly.logic.commands.Command commandList[] = {
		//control
		new Nop(),
		new Mov(),
		new Jmp(),
		new Jsr(),
		new Rsr(),
		//math
		new Add(),
		new Sub(),
		new Mul(),
		new Div(),
		new Mod(),
		new Not(),
		new And(),
		new Or(),
		new Xor(),
		//branching
		new Cmp(),
		new Bjp(),
		new Jez(),
		new Jgz(),
		new Jlz(),
		new Jnz(),
		//buss
		new Psh(),
		new Pul(),
		new Clk(),
		//output
		new Olf(),
		new Och(),
		new Onm(),
		new Omv()
	};
	
}

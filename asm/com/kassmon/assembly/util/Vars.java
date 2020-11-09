package com.kassmon.assembly.util;

import com.kassmon.assembly.commands.branchingCommands.*;
import com.kassmon.assembly.commands.busCommands.*;
import com.kassmon.assembly.commands.controlCommands.*;
import com.kassmon.assembly.commands.mathCommands.*;
import com.kassmon.assembly.commands.outputCommands.*;

public class Vars {
	
	public static final com.kassmon.assembly.commands.Command commandList[] = {
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

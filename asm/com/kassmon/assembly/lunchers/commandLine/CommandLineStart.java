package com.kassmon.assembly.lunchers.commandLine;

import java.io.*;

import com.kassmon.assembly.commands.*;
import com.kassmon.assembly.commands.branchingCommands.*;
import com.kassmon.assembly.commands.busCommands.*;
import com.kassmon.assembly.commands.controlCommands.*;
import com.kassmon.assembly.commands.mathCommands.*;
import com.kassmon.assembly.commands.outputCommands.*;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class CommandLineStart {
	
	private static Command commandList[] = {
		//control
		new Nop(),
		new Mov(),
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
		new Jez(),
		new Jnz(),
		new Jgz(),
		new Jlz(),
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
	
	public static void main(String[] args) {
		com.kassmon.assembly.io.OutputControler.setTextMode(true);
		RunTime runtime = new RunTime();
		Program p = getProgram(new File(args[0]));
		if (p != null) {
			runtime.setProgram(p);
		}
		try {
			runtime.RunProgram(false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static Program getProgram(File file) {
		CommandTokenizer tokenizer = new CommandTokenizer(commandList);
		try {
			String string = "";
			String st = "";
			BufferedReader br = new BufferedReader(new FileReader(file));
			 while ((st = br.readLine()) != null) {
				 string = string + st + System.lineSeparator();
			 }
			 br.close();
			 tokenizer.setInput(string);
			 Program p = tokenizer.getProgram();
			 if (p != null) {
				 return p;
			 }
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (ParcerException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
}
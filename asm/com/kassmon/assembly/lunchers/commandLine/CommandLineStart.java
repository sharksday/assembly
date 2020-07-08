package com.kassmon.assembly.lunchers.commandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.kassmon.assembly.commands.*;
import com.kassmon.assembly.commands.branchingCommands.Jez;
import com.kassmon.assembly.commands.branchingCommands.Jgz;
import com.kassmon.assembly.commands.branchingCommands.Jlz;
import com.kassmon.assembly.commands.branchingCommands.Jmp;
import com.kassmon.assembly.commands.branchingCommands.Jnz;
import com.kassmon.assembly.commands.branchingCommands.Jsr;
import com.kassmon.assembly.commands.branchingCommands.Rsr;
import com.kassmon.assembly.commands.controlCommands.Clk;
import com.kassmon.assembly.commands.controlCommands.Mov;
import com.kassmon.assembly.commands.controlCommands.Nop;
import com.kassmon.assembly.commands.controlCommands.Psh;
import com.kassmon.assembly.commands.controlCommands.Pul;
import com.kassmon.assembly.commands.controlCommands.Rpc;
import com.kassmon.assembly.commands.controlCommands.Wpc;
import com.kassmon.assembly.commands.mathCommands.Add;
import com.kassmon.assembly.commands.mathCommands.And;
import com.kassmon.assembly.commands.mathCommands.Div;
import com.kassmon.assembly.commands.mathCommands.Mod;
import com.kassmon.assembly.commands.mathCommands.Mul;
import com.kassmon.assembly.commands.mathCommands.Not;
import com.kassmon.assembly.commands.mathCommands.Or;
import com.kassmon.assembly.commands.mathCommands.Sub;
import com.kassmon.assembly.commands.mathCommands.Xor;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.externalBuss.old.*;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.*;

import static com.kassmon.library.log.Log.newLogEntry;;

public class CommandLineStart {
	
	private static String path = "com.kassmon.assembly.lunchers.commandLine.CommandLiseStart";
	private static Command commandList[] = {
		new Nop(),
		new Mov(),
		new Add(),
		new Sub(),
		new Mul(),
		new Div(),
		new Mod(),
		new Not(),
		new And(),
		new Or(),
		new Xor(),
		new Jmp(),
		new Jez(),
		new Jlz(),
		new Jgz(),
		new Jnz(),
		new Jsr(),
		new Rsr(),
		new Wpc(),
		new Rpc(),
		new Psh(),
		new Pul(),
		new Clk()
};
	
	public static void main(String[] args) {
		Log.setReport(true);
		Log.setReportLevel(EntryType.WARNING);
		RunTime runtime = new RunTime();
		runtime.addBusItem(new Ram(0, 1));
		runtime.addBusItem(new Ram(2, 3));
		runtime.addBusItem(new Ram(4, 5));
		runtime.addBusItem(new TextDisplay(16,17));
		
		Program p = getProgram(new File(args[0]));
		if (p != null) {
			runtime.setProgram(p);
		}
		runtime.RunProgram(true);
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
			 } else {
				 
			 }
		}catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		} catch (ParcerException e) {
			
		}
		return null;
	}
	
}
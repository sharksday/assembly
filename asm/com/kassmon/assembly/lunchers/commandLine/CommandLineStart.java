package com.kassmon.assembly.lunchers.commandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.Ram;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.assembly.util.Vars;

public class CommandLineStart {
	
	public static void main(String[] args) {
		com.kassmon.assembly.io.OutputControler.setTextMode(true);
		RunTime runtime = new RunTime();
		Program p = getProgram(new File(args[0]));
		if (p != null) {
			runtime.setProgram(p);
			runtime.addBusItem(new Ram(0, 1));
		}
		try {
			runtime.RunProgram(false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static Program getProgram(File file) {
		CommandTokenizer tokenizer = new CommandTokenizer(Vars.commandList);
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
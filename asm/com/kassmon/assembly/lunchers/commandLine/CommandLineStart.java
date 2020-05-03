package com.kassmon.assembly.lunchers.commandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;
import com.kassmon.library.log.Log;

public class CommandLineStart {
	
	public static void main(String[] args) {
		Log.setReport(true);
		RunTime runtime = new RunTime();
		Tokenizer tokenizer = new Tokenizer();
		String st = "";
		String string = "";
		File file = new File(args[0]);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			 while ((st = br.readLine()) != null) {
				 string = string + st + System.lineSeparator();
			 }
			 br.close();
			 tokenizer.setInput(string);
			 runtime.setProgram(tokenizer.getProgram());
			 
			 while (runtime.getProgram().getProgramLength() > runtime.getPc()) {
				 runtime.runCommand(runtime.getPc());
				 runtime.printMem();
			 }
			
		} catch (FileNotFoundException e) {
			System.out.print("file not found");
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	
	
}
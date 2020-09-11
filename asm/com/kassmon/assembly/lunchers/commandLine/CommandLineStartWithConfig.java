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
import com.kassmon.assembly.tokenizer.ConfigTestTokenizer;
import com.kassmon.assembly.util.Vars;
import com.kassmon.library.tokenizers.Token;

public class CommandLineStartWithConfig {
	
	private static RunTime runtime = new RunTime();
	
	public static void main(String[] args) {
		com.kassmon.assembly.io.OutputControler.setTextMode(true);
		getProgram(new File(args[0]));
		try {
			runtime.RunProgram(false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static void getConfig(File config) {
		ConfigTestTokenizer tokenizer = new ConfigTestTokenizer();
		try {
			String string = "";
			String st = "";
			BufferedReader br = new BufferedReader(new FileReader(config));
			while ((st = br.readLine()) != null) {
				string = string + st + System.lineSeparator();
			}
			br.close();
			tokenizer.setInput(string);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		while (tokenizer.hasNextToken()) {
			Token busItem = tokenizer.getNextToken();
			Token commandADR = tokenizer.getNextToken();
			Token dataAdr = tokenizer.getNextToken();
			switch (busItem.getToken()) {
				case "ram":
					break;
				case "stack":
					break;
			}
		}
	}
	
	private static void getProgram(File file) {
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
				 runtime.setProgram(p);
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
	}
	
	
}

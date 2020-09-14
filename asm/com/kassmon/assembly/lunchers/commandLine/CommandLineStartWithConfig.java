package com.kassmon.assembly.lunchers.commandLine;

import java.io.*;

import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.*;
import com.kassmon.assembly.logic.Runtime;
import com.kassmon.assembly.program.Program;
import com.kassmon.assembly.tokenizer.*;
import com.kassmon.assembly.util.Vars;
import com.kassmon.library.tokenizers.Token;

public class CommandLineStartWithConfig {
	
	private static Runtime runtime = new Runtime();
	
	public static void main(String[] args) {
		com.kassmon.assembly.io.OutputControler.setTextMode(true);
		getProgram(new File(args[0]));
		try {
			runtime.runProgram(false);
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
			Token dataADR = tokenizer.getNextToken();
			switch (busItem.getToken()) {
				case "ram":
					runtime.addBusItem(new Ram(Integer.parseInt(commandADR.getToken()), Integer.parseInt(dataADR.getToken())));
					break;
				case "stack":
					runtime.addBusItem(new Stack(Integer.parseInt(commandADR.getToken()), Integer.parseInt(dataADR.getToken())));
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

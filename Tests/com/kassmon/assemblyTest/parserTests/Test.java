package com.kassmon.assemblyTest.parserTests;

import java.util.Scanner;

import com.kassmon.assembly.buildTime.BuildControler;
import com.kassmon.assembly.buildTime.tokenizer.Tokenizer;
import com.kassmon.assembly.exceptions.TokenizerExcption;
import com.kassmon.assembly.runTime.envirment.Program.Program;
import com.kassmon.assembly.runTime.objects.commands.Command;


public class Test {
	
	public static void main(String[] args) {
		
		BuildControler controler = new BuildControler();
		
		Tokenizer tokenizer = controler.getTokenizer();
		
		String in = "nop mov a0 a0 nop nop";
		
		//controler.setInput(in);
		tokenizer.setInput(in);
		
		Program p = controler.getProgram();
		System.out.println(p.getProgramLength());
		
		
		
		
	}
	
}
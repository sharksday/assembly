package com.kassmon.assembly.logic;

import java.util.ArrayList;
import java.util.Stack;

import com.kassmon.assembly.externalBuss.old.ExternalBusItem;
import com.kassmon.assembly.program.Program;
import com.kassmon.library.log.EntryType;
import com.kassmon.library.log.Log;

//@SuppressWarnings("unused")
public class RunTime {
	private String path = "com.kassmon.assembly.logic.Runtime";
	private Program program;
	// memory
	private int acc;
	private int a[];
	private int pc;
	private Stack<Integer> stack;
	//External items
	private int adr;
	private ArrayList<ExternalBusItem> bus;
	
	
	
	public RunTime() {
		a = new int[16];
		stack = new Stack<>();
		bus = new ArrayList<>();
	}
	
	public Program getProgram() {
		return program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
		if (this.program != null) {
			int temp = this.program.getProgramLength();
			Log.newLogEntry(EntryType.INFO, path, "program set   length = " + temp);
		}
		
	}
	
	public int getAcc() {
		return acc;
	}
	
	public void setAcc(int acc) {
		this.acc = acc;
	}
	
	public int getA(int i) {
		return a[i];
	}
	
	public void setA(int i, int a) {
		this.a[i] = a;
	}
	
	public int getPc() {
		return pc;
	}
	
	public void setPc(int pc) {
		this.pc = pc;
	}
	
	public int getAdr() {
		return adr;
	}
	
	public void setAdr(int adr) {
		this.adr = adr;
	}
	
	public int getALength() {
		return a.length;
	}
	
	public void pushToStack(int i) {
		this.stack.add(i);
	}
	
	public int popFromStack() {
		return this.stack.pop();
	}
	
	public void runCommand(int pc) {
		if (program.getProgramLine(pc).isCommand()) {
			program.getProgramLine(pc).getCommand().run(this);
		}
		this.pc++;
	}

	public void printMem() {
		System.out.println(acc + " " + (pc));
		for (Integer i: a) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(adr);
	} 
	
	public void addBusItem(ExternalBusItem item) {
		this.bus.add(item);
	}

	public ArrayList<ExternalBusItem> getBus() {
		return bus;
	}
	
	public void RunProgram(boolean reportAfterCommand) {
		while (this.program.getProgramLength() > this.getPc()) {
			this.runCommand(this.pc);
			if (reportAfterCommand) this.printMem();
		}
	}
	
}
package com.kassmon.assembly.logic;

import com.kassmon.library.log.EntryType;
import static com.kassmon.library.log.Log.newLogEntry;

import java.util.ArrayList;
import java.util.Stack;

import com.kassmon.assembly.external.BusItem;
import com.kassmon.assembly.program.Program;

@SuppressWarnings("unused")
public class RunTime {
	private final String path = "com.kassmon.projects.comp.logic.RunTime";
	private Program program;
	// memory
	private int acc;
	private int a[];
	private int pc;
	private Stack<Integer> stack;
	//External items
	private int adr;
	private ArrayList<BusItem> bus;
	// flags
	
	public RunTime() {
		a = new int[16];
		stack = new Stack<>();
	}
	
	public Program getProgram() {
		return program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
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
	
	private void runCommand(int pc) {
		if (program.getProgramLine(pc).isCommand()) {
			program.getProgramLine(pc).getCommand().run(this);
		}
		this.pc++;
	}
	
	public void addBussItem(BusItem item) {
		this.bus.add(item);
	}
	
	public void push(int value) {
		if (this.adr < this.bus.size()) {
			bus.get(this.adr).push(value);
		}else {
			newLogEntry(EntryType.WARNING, path, ("No adr at value" + String.valueOf(this.adr)));
		}
	}
	
	public void printMem() {
		System.out.println(acc + " " + pc);
		for (Integer i: a) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(adr);
	} 
	
	
	
}
package com.kassmon.assembly.logic;

import java.util.ArrayList;
import java.util.Stack;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.assembly.io.OutputControler;
import com.kassmon.assembly.program.Program;

public class RunTime {
	private Program program;
	//memory
	private int a[];
	private int pc;
	private Stack<Integer> stack;
	//External items
	private ArrayList<ExternalBusItem> bus;
	//flags
	private boolean jez = false;
	private boolean jnz = false;
	private boolean jgz = false;
	private boolean jlz = false;
	private boolean reportAfterCommand;
	
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
	
	public boolean isJez() {
		return jez;
	}

	public void setJez(boolean jez) {
		this.jez = jez;
	}

	public boolean isJnz() {
		return jnz;
	}

	public void setJnz(boolean jnz) {
		this.jnz = jnz;
	}

	public boolean isJgz() {
		return jgz;
	}

	public void setJgz(boolean jgz) {
		this.jgz = jgz;
	}

	public boolean isJlz() {
		return jlz;
	}

	public void setJlz(boolean jlz) {
		this.jlz = jlz;
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
	
	public void runCommand(int pc) throws RuntimeException {
		if (program.getProgramLine(pc).isCommand()) {
			program.getProgramLine(pc).getCommand().run(this);
		}
		this.pc++;
	}

	public void printMem() {
		OutputControler.output(String.valueOf(pc) + System.lineSeparator());
		for (Integer i: a) {
			OutputControler.output(String.valueOf(i) + " ");
		}
		OutputControler.output(System.lineSeparator());
		
	} 
	
	public void addBusItem(ExternalBusItem item) {
		this.bus.add(item);
	}

	public ArrayList<ExternalBusItem> getBus() {
		return bus;
	}
	
	public void RunProgram(boolean reportAfterCommand) throws RuntimeException {
		this.reportAfterCommand = reportAfterCommand;
		while (this.program.getProgramLength() > this.getPc()) {
			this.runCommand(this.pc);
			if (this.reportAfterCommand) this.printMem();
		}
	}

	public boolean isReportAfterCommand() {
		return reportAfterCommand;
	}

	public void setReportAfterCommand(boolean reportAfterCommand) {
		this.reportAfterCommand = reportAfterCommand;
	}
	
	
	
}
package com.kassmon.old.assembly.logic;

import java.util.ArrayList;
import java.util.Stack;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.old.assembly.logic.io.OutputControler;
import com.kassmon.old.assembly.logic.program.Program;

public class Runtime implements IRuntime {
	
	private Program program;
	//memory
	private int a[];
	private int pc;
	private Stack<Integer> stack;
	//External items
	private ArrayList<ExternalBusItem> bus;
	//flags
	private int dataFlags = 0;
	private int userFlags = 0;
	private boolean reportAfterCommand;
	
	public Runtime() {
		a = new int[16];
		stack = new Stack<>();
		bus = new ArrayList<>();
	}
	
	@Override
	public Program getProgram() {
		return program;
	}
	
	@Override
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@Override
	public int SiseOfA() {
		return a.length;
	}
	
	@Override
	public void setA(int adr, int value) {
		this.a[adr] = value;
	}
	
	@Override
	public int getA(int adr) {
		return a[adr];
	}
	
	@Override
	public void setPc(int pc) {
		this.pc = pc;
	}
	
	@Override
	public int getPc() {
		return pc;
	}
	
	@Override
	public void pushToStack(int value) {
		this.stack.add(value);
	}
	
	@Override
	public int popFromStack() {
		return this.stack.pop();
	}
	
	@Override
	public void runCommand(int pc) throws RuntimeException {
		if (program.getProgramLine(pc).isCommand()) {
			program.getProgramLine(pc).getCommand().run(this);
		}
		this.pc++;
	}
	
	@Override
	public void runProgram(boolean reportAfterCommand) throws RuntimeException {
		this.reportAfterCommand = reportAfterCommand;
		while (this.program.getProgramLength() > this.getPc()) {
			this.runCommand(this.pc);
			if (this.reportAfterCommand) this.printMem();
		}
	}
	
	@Override
	public void printMem() {
		OutputControler.output(String.valueOf(pc) + System.lineSeparator());
		for (Integer i: a) {
			OutputControler.output(String.valueOf(i) + " ");
		}
		OutputControler.output(System.lineSeparator());
		OutputControler.output("0b" +Integer.toBinaryString(this.dataFlags));
		OutputControler.output(System.lineSeparator());
	}
	
	@Override
	public void addBusItem(ExternalBusItem item) {
		this.bus.add(item);
	}
	
	@Override
	public ArrayList<ExternalBusItem> getBus() {
		return bus;
	}
	
	@Override
	public boolean isReportAfterCommand() {
		return reportAfterCommand;
	}
	
	@Override
	public void setReportAfterCommand(boolean reportAfterCommand) {
		this.reportAfterCommand = reportAfterCommand;
	}
	
	@Override
	public int getFlags() {
		return this.dataFlags;
	}
	
	@Override
	public void setFlags(int flag){
		this.dataFlags = flag;
	}

	@Override
	public int getUserFlags() {
		return this.userFlags;
	}

	@Override
	public void setUserFlags(int userFlags) {
		
	}

	
	
}

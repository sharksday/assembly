package com.kassmon.assembly.runTime.envirment.data.memory;

import java.util.Stack;

public class Memory {
	
	private int a[];
	private int regA; //acc
	private int regB;
	
	private int pc = 0;
	
	private Stack<Integer> stack;
	
	public Memory() {
		a = new int[16];
		stack = new Stack<>();
	}

	public int getA(int loc) {
		return a[loc];
	}

	public void setA(int loc, int value) {
		this.a[loc] = value;
	}

	public int getRegA() {
		return regA;
	}

	public void setRegA(int regA) {
		this.regA = regA;
	}

	public int getRegB() {
		return regB;
	}

	public void setRegB(int regB) {
		this.regB = regB;
	}

	public int popStack() {
		return stack.pop();
	}

	public void pushStack(int value) {
		this.stack.push(value);
	}
	
	public int sizeOfA() {
		return a.length;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}
	
	
	
}

package com.kassmon.assembly.logic;

import com.kassmon.library.log.EntryType;

import static com.kassmon.library.log.Log.newLogEntry;

@SuppressWarnings("unused")
public class RunTime {
	private final String path = "com.kassmon.projects.comp.logic.RunTime";
	//mem
	private int acc;
	private int a[];
	private int pc;
	//output ports
	private int adr;
	private int portA;
	private int portB;
	//flags
	
	public RunTime() {
		a = new int[16];
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

	public int getPortA() {
		return portA;
	}

	public void setPortA(int portA) {
		this.portA = portA;
	}

	public int getPortB() {
		return portB;
	}

	public void setPortB(int portB) {
		this.portB = portB;
	}
	
	public int getALength() {
		return a.length;
	}
	
}

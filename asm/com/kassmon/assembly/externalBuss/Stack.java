package com.kassmon.assembly.externalBuss;

import com.kassmon.assembly.exceptions.BusNoOutputExcption;

public class Stack implements ExternalBusItem {
	
	private int commandAdr = -1, dataAdr = -1;
	private int command = 0, data = 0;
	
	private java.util.Stack<Integer> s;
	
	public Stack(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		this.s = new java.util.Stack<>();
	}
	
	@Override
	public void push(int adr, int value) {
		if (adr == this.commandAdr) {
			this.command = value;
		}else if (adr == this.dataAdr) {
			this.data = value;
		}
	}
	
	@Override
	public int pull(int adr) throws BusNoOutputExcption {
		if (adr == this.commandAdr) return this.command;
		if (adr == this.dataAdr) return this.data;
		throw new BusNoOutputExcption("");
	}
	
	@Override
	public void clock() {
		switch (command) {
			case 1:
				this.s.push(data);
				this.data = 0;
				this.command = 0;
				break;
			case 2:
				data = this.s.pop();
				this.command = 0;
				break;
			default:
				break;
		}
	}
	
	@Override
	public int getCommandAdr() {
		return commandAdr;
	}
	
	@Override
	public int getDataAdr() {
		return dataAdr;
	}
	
}

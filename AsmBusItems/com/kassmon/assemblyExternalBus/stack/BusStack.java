package com.kassmon.assemblyExternalBus.stack;

import java.util.Stack;

import com.kassmon.assembly.exceptions.BusNoOutputExcption;
import com.kassmon.assembly.externalBus.ExternalBusItem;

public class BusStack implements ExternalBusItem {
	
	private int commandAdr, dataAdr, command, data;
	private Stack<Integer> stack;
	
	public BusStack(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		this.stack = new Stack<>();
	}
	
	@Override
	public void push(int adr, int value) {
		if (adr == commandAdr) {
			this.command = value;
		}else if (adr == dataAdr) {
			this.data = value;
		}
	}
	
	@Override
	public int pull(int adr) throws BusNoOutputExcption {
		if (this.dataAdr == adr) {
			return data;
		}else if (this.command == adr) {
			return command;
		}
		throw (new BusNoOutputExcption(""));
	}
	
	@Override
	public void clock() {
		if (command == 1) {
			stack.push(data);
		}else if (command == 2) {
			if (!stack.isEmpty()) {
				data = stack.pop();
			}else {
				data = 0;
			}
		}
	}
	
	@Override
	public int getCommandAdr() {
		return this.commandAdr;
	}
	
	@Override
	public int getDataAdr() {
		return this.dataAdr;
	}
	
}

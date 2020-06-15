package com.kassmon.assembly.externalBuss;

import java.util.Stack;

@SuppressWarnings("unused")
public class StackMem implements ExternalBusItem {
	
	private int commandAdr = -1, dataAdr = -1;
	private int command = 0, data = 0;
	
	private Stack<Integer> stack = new Stack<>();
	
	@Override
	public void push(int adr, int value) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
	}
	
	@Override
	public int pull(int adr) {
		return stack.pop();
	}
	
	@Override
	public boolean hasOutput(int adr) {
		return false;
	}
	
	@Override
	public void clock() {
		this.stack.push(this.data);
	}
	
}
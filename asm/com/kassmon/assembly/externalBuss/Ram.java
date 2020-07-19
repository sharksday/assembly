package com.kassmon.assembly.externalBuss;

import com.kassmon.assembly.exceptions.BusNoOutputExcption;

public class Ram implements ExternalBusItem {
	
	private int commandAdr = -1, dataAdr = -1;
	private int command = 0, data = 0;
	
	private int ramSise = 128;
	private int loc = 0;
	private int ram[];
	
	public Ram(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		this.ram = new int[this.ramSise];
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
				this.loc = this.data;
				this.data = 0;
				this.command = 0;
				break;
			case 2:
				this.ram[this.loc] = this.data;
				this.data = 0;
				this.command = 0;
				break;
			case 3:
				this.data = this.ram[this.loc];
				this.command = 0;
				break;
			default:
				break;
		}
	}
	
}

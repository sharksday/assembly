package com.kassmon.assemblyExternalBus.ram;

import com.kassmon.assembly.exceptions.BusNoOutputExcption;
import com.kassmon.assembly.externalBus.ExternalBusItem;

public class BusRam implements ExternalBusItem {
	
	private int commandAdr, dataAdr, command, data, cell;
	
	private int ram[];
	
	public BusRam(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		ram = new int [128];
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
		switch (command) {
			case 1:
				ram[cell] = data;
				break;
			case 2:
				data = ram[cell];
				break;
			case 3:
				if (data > -1) {
					if (data < 128) {
						cell = data;
					}
				}
				break;
			case 4:
				data = cell;
				break;
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

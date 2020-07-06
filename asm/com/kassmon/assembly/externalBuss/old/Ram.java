package com.kassmon.assembly.externalBuss.old;

public class Ram implements ExternalBusItem {
	
	private int commandAdr = -1, dataAdr = -1;
	
	private int command = 0, data = 0;
	private int loc = 0;
	private int ram[];
	public Ram(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		this.ram = new int[128];
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
	public int pull(int adr) {
		return data;
	}
	
	public boolean hasOutput(int adr) {
		//System.out.println(this.dataAdr == adr);
		return this.dataAdr == adr;
	}
	
	@Override
	public void clock() {
		switch (command) {
			case 0:
				break;
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
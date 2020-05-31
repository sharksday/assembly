package com.kassmon.assembly.externalBuss;

import javax.swing.*;

@SuppressWarnings("serial")
public class TextDisplay extends JFrame implements ExternalBusItem {
	
	private int commandAdr = -1, dataAdr = -1;
	private int command = 0, data = 0;
	private int charMap[][];
	private JLabel chars[][];
	
	private int column = 0, row = 0;
		
	public TextDisplay(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		this.charMap = new int[40][20];
		this.chars = new JLabel[40][20];
		for (int i = 0; i < 40;i++) {
			for (int i1 = 0; i1 < 20;i1++) {
				this.chars[i][i1] = new JLabel();
			}
		}
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
		return 0;
	}
	
	@Override
	public boolean hasOutput(int adr) {
		return this.dataAdr == adr;
	}
	
	@Override
	public void clock() {
		switch (command) {
			case 0:
				break;
			case 1:
				this.column = this.data;
				this.command = 0;
				this.data = 0;
				break;
			case 2:
				this.row = this.data;
				this.command = 0;
				this.data = 0;
				break;
			case 3:
				this.charMap[this.column][this.row] = 0;
				this.command = 0;
				this.row = 0;
				break;
			case 4:
				this.charMap[this.column][this.row] = this.data;
				this.command = 0;
				this.row = 0;
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				break;
			case 14:
				break;
			case 15:
				break;
		}
	}
	
	private void printScrean() {
		for (int i = 0; i < 40;i++) {
			for (int i1 = 0; i1 < 20;i1++) {
				this.chars[i][i1].setText(String.valueOf((char) this.charMap[i][i1]));
			}
		}
	}
	
}

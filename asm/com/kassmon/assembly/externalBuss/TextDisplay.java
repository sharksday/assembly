package com.kassmon.assembly.externalBuss;

import javax.swing.*;

@SuppressWarnings("serial")
public class TextDisplay extends JFrame implements ExternalBusItem {
	
	private int commandAdr = -1, dataAdr = -1;
	private int command = 0, data = 0;
	private int charMap[][];
	private JLabel chars[][];
	
	private int columnNumber = 40, rowNumber = 20;
	private int column = 0, row = 0;
		
	public TextDisplay(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		this.charMap = new int[this.columnNumber][this.rowNumber];
		this.chars = new JLabel[this.columnNumber][this.rowNumber];
		
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
				this.data = 0;
				break;
			case 5:
				this.column++;
				this.data = 0;
				this.command = 0;
				break;
			case 6:
				this.column--;
				this.data = 0;
				this.command = 0;
				break;
			case 7:
				this.row++;
				this.data = 0;
				this.command = 0;
				break;
			case 8:
				this.row--;
				this.data = 0;
				this.command = 0;
				break;
			case 9:
				for (int i = 0; i < this.rowNumber; i++) {
					if (i == this.rowNumber - 1) {
						for (int x = 0; x < this.columnNumber; x++) {
							this.charMap[x][this.columnNumber - 1] = 0;
						}
					}else {
						for (int x = 0; x < this.columnNumber; x++) {
							this.charMap[x][i] = this.charMap[x][i + 1];
						}
					}
				}
				this.data = 0;
				this.command = 0;
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
				this.printTextScrean();
				break;
			case 33:
				super.setVisible(true);
				break;
			case 34:
				super.setVisible(false);
				break;
			case 35:
				this.initDisplay();
				break;
		}
	}
	
	private void printTextScrean() {
		System.out.println("text out");
		for (int i = 0; i < this.columnNumber;i++) {
			for (int i1 = 0; i1 < this.rowNumber;i1++) {
				System.out.print(String.valueOf((char) this.charMap[i][i1]));
			}
			System.out.println();
		}
	}
	
	@SuppressWarnings("unused")
	private void printScrean() {
		for (int i = 0; i < this.columnNumber;i++) {
			for (int i1 = 0; i1 < this.rowNumber;i1++) {
				this.chars[i][i1].setText(String.valueOf((char) this.charMap[i][i1]));
			}
		}
	}
	
	private void initDisplay() {
		super.setVisible(true);
		for (int i = 0; i < this.columnNumber;i++) {
			for (int i1 = 0; i1 < this.rowNumber;i1++) {
				this.chars[i][i1] = new JLabel();
				super.add(this.chars[i][i1]);
				this.chars[i][i1].setText(" ");
				this.chars[i][i1].setSize(16, 20);
				
			}
		}
	}
	
}

package com.kassmon.assembly.externalBuss;

import javax.swing.*;

public class TextDisplay extends JFrame implements ExternalBusItem {
	
	private char charList[] = {
			' ','a','b','c','d','e','f','g','h','i',
			'j','k','l','m','n','o','p','q','r','s',
			't','u','v','w','x','y','z','A','B','C',
			'D','E','F','G','H','I','J','K','L','M',
			'N','O','P','Q','R','S','T','U','V','W',
			'X','Y','Z','1','2','3','4','5','6','7',
			'8','9','0','.'
			};
	
	private int commandAdr = -1, dataAdr = -1;
	
	private int command = 0, data = 0;
	private int loc = 0;
	
	private char charMap[][];
	
	
	
	public TextDisplay(int commandAdr, int dataAdr) {
		this.commandAdr = commandAdr;
		this.dataAdr = dataAdr;
		this.charMap = new char[40][20];
	}
	
	@Override
	public void push(int adr, int value) {
		
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
		
	}
	
}

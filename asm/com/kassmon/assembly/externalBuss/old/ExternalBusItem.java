package com.kassmon.assembly.externalBuss.old;

public interface ExternalBusItem {
	void push(int adr, int value);
	int pull(int adr);
	boolean hasOutput(int adr);
	void clock();
}

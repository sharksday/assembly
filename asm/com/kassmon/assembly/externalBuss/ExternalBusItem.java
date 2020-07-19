package com.kassmon.assembly.externalBuss;

import com.kassmon.assembly.exceptions.BusNoOutputExcption;

public interface ExternalBusItem {
	void push(int adr, int value);
	int pull(int adr) throws BusNoOutputExcption;
	void clock();
}

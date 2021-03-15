package com.kassmon.assembly.externalBus;

import com.kassmon.assembly.exceptions.BusNoOutputExcption;

public interface ExternalBusItem {
	void push(int adr, int value);
	int pull(int adr) throws BusNoOutputExcption;
	void clock();
	int getCommandAdr();
	int getDataAdr();
}

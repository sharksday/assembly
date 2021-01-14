package com.kassmon.assembly.externalBuss;

import java.util.ArrayList;

import com.kassmon.assembly.exceptions.BusNoOutputExcption;

public class BusMaster {
	
	private ArrayList<ExternalBusItem> busItems;
	
	public BusMaster() {
		busItems = new ArrayList<>();
	}
	
	public ArrayList<ExternalBusItem> getBusItems() {
		return busItems;
	}
	
	public void setBusItems(ArrayList<ExternalBusItem> array) {
		this.busItems = array;
	}
	
	public void addBusItem(ExternalBusItem obj) {
		this.busItems.add(obj);
	}
	
	public void push(int adr, int value) {
		for (ExternalBusItem obj: busItems) obj.push(adr, value);
	}
	
	public void pull(int adr) throws BusNoOutputExcption {
		for (ExternalBusItem obj: busItems) obj.pull(adr);
	}
	
	public void clock() throws BusNoOutputExcption {
		for (ExternalBusItem obj: busItems) obj.clock();
	}
	
	
	
}

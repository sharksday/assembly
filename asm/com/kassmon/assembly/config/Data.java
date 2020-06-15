package com.kassmon.assembly.config;

public class Data {
	
	private String key;
	private String data[];
	
	public Data(String key, String data[]) {
		this.key = key;
		this.data = data;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String[] getData() {
		return data;
	}
	
	public String getData(int i) {
		return data[i];
	}
	
	public int getNumberOfDataPoints() {
		return data.length;
	}
	
}

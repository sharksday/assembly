package com.kassmon.assembly.runTime.envirment.data.memory;

public class Flags {
	
	private boolean gtz;
	private boolean ltz;
	private boolean nez;
	private boolean ez;
	
	private boolean user[] = new boolean[128];
	
	public Flags () {
		
	}

	public boolean isGtz() {
		return gtz;
	}

	public void setGtz(boolean gtz) {
		this.gtz = gtz;
	}

	public boolean isLtz() {
		return ltz;
	}

	public void setLtz(boolean ltz) {
		this.ltz = ltz;
	}

	public boolean isNez() {
		return nez;
	}

	public void setNez(boolean nez) {
		this.nez = nez;
	}

	public boolean isEz() {
		return ez;
	}

	public void setEz(boolean ez) {
		this.ez = ez;
	}
	
	public void setUser(int index, boolean value) {
		this.user[index] = value;
	}
	
}

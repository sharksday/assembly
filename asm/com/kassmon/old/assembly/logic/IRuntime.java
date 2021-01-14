package com.kassmon.old.assembly.logic;

import java.util.ArrayList;

import com.kassmon.assembly.exceptions.RuntimeException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.old.assembly.logic.program.Program;

public interface IRuntime {
	
	Program getProgram();
	void setProgram(Program program);
	
	int SiseOfA();
	void setA(int adr, int value);
	int getA(int adr);
	
	void setPc(int pc);
	int getPc();
	
	void pushToStack(int value);
	int popFromStack();
	
	void runCommand(int pc) throws RuntimeException;
	void runProgram(boolean reportAfterCommand) throws RuntimeException;
	
	void printMem();
	
	void addBusItem(ExternalBusItem item);
	ArrayList<ExternalBusItem> getBus();
	
	boolean isReportAfterCommand();
	void setReportAfterCommand(boolean reportAfterCommand);
	
	int getFlags();
	void setFlags(int flag);
	
	int getUserFlags();
	void setUserFlags(int userFlags);
	
}

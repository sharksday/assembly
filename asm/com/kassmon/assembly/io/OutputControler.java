package com.kassmon.assembly.io;

import java.util.ArrayList;

public class OutputControler {
	
	private static ArrayList<OutputEvent> eventListeners;
	
	private static boolean textMode = false;
	private static boolean standAloneOutput = false;
	private static boolean outputEvent = false;
	
	public static void setTextMode(boolean textMode) {
		OutputControler.textMode = textMode;
	}
	
	public static void setStandAloneOutput(boolean standAloneOutput) {
		OutputControler.standAloneOutput = standAloneOutput;
	}
	
	public static void setOutputEvent(boolean outputEvent) {
		OutputControler.outputEvent = outputEvent;
	}
	
	public static void output(String text) {
		if (textMode){
			System.out.print(text);
		}else if (standAloneOutput) {
			
		}else if (outputEvent) {
			for (OutputEvent obj: eventListeners) {
				obj.output(text);
			}
		}else {
			
		}
	}
	
	public static void addOutputEventListener(OutputEvent obj) {
		eventListeners.add(obj);
	}
	
}

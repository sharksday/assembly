package com.kassmon.assembly.runTime.envirment.io;

import java.util.ArrayList;

public class Output {
	
	private static ArrayList<OutputEvent> outputL = new ArrayList<>();
	
	public static void addOutputListener(OutputEvent listener) {
		outputL.add(listener);
	}
	
	public static void event(String output) {
		for (OutputEvent obj: outputL) {
			obj.output(output);
		}
	}
	
}

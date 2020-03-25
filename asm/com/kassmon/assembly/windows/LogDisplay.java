package com.kassmon.assembly.windows;

import javax.swing.*;

import com.kassmon.library.log.Log;
import com.kassmon.library.log.LogEntry;
import com.kassmon.library.log.NewLogEntryEvent;

@SuppressWarnings("serial")
public class LogDisplay extends JInternalFrame implements NewLogEntryEvent {
	
	private JEditorPane log;
	
	public LogDisplay() {
		log = new JEditorPane();
		
		super.add(new JScrollPane(log));
		
		
	}
	
	
	
	@Override
	public void newError(LogEntry logEntry) {
		this.log.setText("<html><body>" + System.lineSeparator());
		for (LogEntry obj: Log.getLog()){
			this.log.setText(this.log.getText() + obj.getHTMLtext());
		}
		this.log.setText(this.log.getText() + "</body></html>");
	}
	
}
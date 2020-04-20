package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.Tokenizer;
import com.kassmon.library.log.EntryType;

import static com.kassmon.library.log.Log.newLogEntry;

public class Mov extends Command {
	private String path = "com.kassmon.assembly.program.commands.Mov";
	
	@Override
	public Command parse(Tokenizer t) {
		Argument a1 = null, a2 = null;
		Argument temp = super.getArg(t);
		if (!temp.isLabel()) {
			if (!temp.isNumber() & temp.getValue().equals("pc")) {
				newLogEntry(EntryType.ERROR, path, "not a valid argument");
				return null;
			}
			a1 = temp;
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument");
		}
		temp = super.getArg(t);
		if (!temp.isLabel()) {
			if (!temp.isNumber()) {
				a2 = temp;
			}else {
				newLogEntry(EntryType.ERROR, path, "not a valid argument");
				return null;
			}
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument");
			return null;
		}
		return new Mov(a1, a2);
	}
	
	@Override
	public String getPattern() {
		return "mov";
	}
	
	private Argument a1, a2;
	
	public Mov(Argument a1, Argument a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
	
	public Mov() {
		
	}

	@Override
	public void run(RunTime runtime) {
		if (a1.isNumber()) {
			if (a2.getValue().equals("acc")) {
				runtime.setAcc(Integer.parseInt(a1.getValue()));
			}else if (a2.getValue().equals("adr")) {
				runtime.setAdr(Integer.parseInt(a1.getValue()));
			}else if (a2.getValue().contains("a")) {
				if (runtime.getALength() > Integer.parseInt(a2.getValue().substring(1))) {
					runtime.setA(Integer.parseInt(a2.getValue().substring(1)), Integer.parseInt(a1.getValue()));
				}else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
				}
			}else {
				newLogEntry(EntryType.ERROR, path, "RunTime Error: destination error");
			}
		}else {
			if (a2.getValue().equals("acc")) {
				if (a1.getValue().equals("acc")) {
					runtime.setAcc(runtime.getAcc());
				}else if (a1.getValue().equals("adr")) {
					runtime.setAcc(runtime.getAdr());
				}else if (a1.getValue().contains("a")) {
					if (runtime.getALength() > Integer.parseInt(a1.getValue().substring(1))) {
						runtime.setAcc(runtime.getA(Integer.parseInt(a1.getValue().substring(1))));
					}else {
						newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
					}
				}
			}else if (a2.getValue().equals("adr")) {
				if (a1.getValue().equals("acc")) {
					runtime.setAdr(runtime.getAcc());
				}else if (a1.getValue().equals("adr")) {
					runtime.setAdr(runtime.getAdr());
				}else if (a1.getValue().contains("a")) {
					if (runtime.getALength() > Integer.parseInt(a2.getValue().substring(1))) {
						runtime.setAdr(runtime.getA(Integer.parseInt(a1.getValue().substring(1))));
					}else {
						newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
					}
				}
			}else if (a2.getValue().contains("a")) {
				if (runtime.getALength() > Integer.parseInt(a2.getValue().substring(1))) {
					if (a1.getValue().equals("acc")) {
						runtime.setA(runtime.getA(Integer.parseInt(a2.getValue().substring(1))), runtime.getAcc());
					}else if (a1.getValue().equals("adr")) {
						runtime.setA(runtime.getA(Integer.parseInt(a2.getValue().substring(1))), runtime.getAdr());
					}else if (a1.getValue().contains("a")) {
						if (runtime.getALength() > Integer.parseInt(a1.getValue().substring(1))) {
							runtime.setA(runtime.getA(Integer.parseInt(a2.getValue().substring(1))), runtime.getA(Integer.parseInt(a1.getValue().substring(1))));
						}else {
							newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
						}
					}
				}else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
				}
			}else {
				newLogEntry(EntryType.ERROR, path, "RunTime Error: destination error");
			}
		}
	}
	
}
package com.kassmon.assembly.logic;

import com.kassmon.assembly.program.*;
import com.kassmon.library.log.EntryType;

import static com.kassmon.assembly.program.LineTypes.*;
import static com.kassmon.library.log.Log.newLogEntry;

public class RunTime {
	private final String path = "com.kassmon.projects.comp.logic.RunTime";
	//mem
	private int acc;
	private int a[];
	private int pc;
	//output ports
	private int adr;
	private int portA;
	private int portB;
	//flags
	
	//program
	Program program;
	
	public RunTime() {
		a = new int[16];
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private void runProgram(Program program) {
		this.program = program;
		boolean run = true;
		while (pc < program.getSize()) {
			runCommand();
 		}
	}
	
	private void runCommand() {
		ProgramLine ln = program.getProgramLine(pc);
		if (ln.getLineType().equals(LABEL)){
			nop();
		}else if (ln.getLineType().equals(COMMAND)) {
			switch (ln.getCommandType()) {
				case NOP:
					nop();
					break;
				case MOV:
					mov();
					break;
				case ADD:
					add();
					break;
				case SUB:
					sub();
					break;
				case MUL:
					mul();
					break;
				case DIV:
					div();
					break;
				case JMP:
					jmp();
					break;
				case JEZ:
					jez();
					break;
				case JNZ:
					jnz();
					break;
				case JGZ:
					jgz();
					break;
				case JLZ:
					jlz();
					break;
				case RPC:
					rpc();
					break;
				case WPC:
					wpc();
					break;
				case LDA:
					lda();
					break;
				case SVA:
					sva();
					break;
				case LDB:
					ldb();
					break;
				case SVB:
					svb();
					break;
				case CLK:
					clk();
					break;
			}
		}
		pc++;
	}
	
	private void nop () {
	
	}
	
	private void mov(){
		Argument arg = program.getProgramLine(pc).getArgument(0);
		Argument des = program.getProgramLine(pc).getArgument(1);
		if (arg.isNumber()) {
			if (des.getValue().equals("acc")) {
				acc = Integer.parseInt(arg.getValue());
			} else if (des.getValue().equals("adr")) {
				adr = Integer.parseInt(arg.getValue());
			} else if (des.getValue().contains("a")) {
				if (16 > Integer.parseInt(des.getValue().substring(1))) {
					a[Integer.parseInt(des.getValue().substring(1))] = Integer.parseInt(arg.getValue());
				} else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
				}
			} else {
				newLogEntry(EntryType.ERROR, path, "RunTime Error: Mov error");
			}
		} else {
			if (des.getValue().equals("acc")) {
				if (arg.getValue().equals("null")){
					acc = -1;
				} else if (arg.getValue().equals("acc")) {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Tried to move acc to acc");
				} else if (arg.getValue().equals("adr")) {
					acc = adr;
				} else if (arg.getValue().contains("a")) {
					if (16 > Integer.parseInt(arg.getValue().substring(1))) {
						acc = a[Integer.parseInt(arg.getValue().substring(1))];
					} else {
						newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
					}
				} else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Mov error");
				}
			} else if (des.getValue().equals("adr")) {
				if (arg.getValue().equals("null")) {
					adr = -1;
				} else if (arg.getValue().equals("acc")) {
					adr = acc;
				} else if (arg.getValue().equals("adr")) {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Tried to move adr to adr");
				} else if (arg.getValue().contains("a")) {
					if (16 > Integer.parseInt(arg.getValue().substring(1))) {
						adr = a[Integer.parseInt(arg.getValue().substring(1))];
					} else {
						newLogEntry(EntryType.ERROR, path, "RunTime Error: Location out of bounds");
					}
				} else {
					newLogEntry(EntryType.ERROR, path, "RunTime Error: Mov error");
				}
			} else if (des.getValue().contains("a")) {
				if (arg.getValue().equals("acc")) {
					a[Integer.parseInt(des.getValue().substring(1))] = acc;
				}else if (arg.getValue().equals("adr")) {
					a[Integer.parseInt(des.getValue().substring(1))] = adr;
				}else if (arg.getValue().contains("a")) {
					a[Integer.parseInt(des.getValue().substring(1))] = a[Integer.parseInt(arg.getValue().substring(1))];
				}
			} else{
				newLogEntry(EntryType.ERROR, path, "RunTime Error on command mov");
			}
		}
	}
	
	private void add(){
		Argument arg = program.getProgramLine(pc).getArgument(0);
		if (arg.isNumber()) {
			acc += Integer.parseInt(arg.getValue());
		} else {
			if (arg.getValue().equals("acc")) {
				acc += acc;
			} else if (arg.getValue().equals("adr")) {
				acc += adr;
			} else if (arg.getValue().contains("a")) {
				if (16 > Integer.parseInt(arg.getValue().substring(1))) {
					acc += a[Integer.parseInt(arg.getValue().substring(1))];
				}
			}
		}
	}
	
	private void sub(){
		Argument arg = program.getProgramLine(pc).getArgument(0);
		if (arg.isNumber()) {
			acc -= Integer.parseInt(arg.getValue());
		} else {
			if (arg.getValue().equals("acc")) {
				acc -= acc;
			} else if (arg.getValue().equals("adr")) {
				acc -= adr;
			} else if (arg.getValue().contains("a")) {
				if (16 > Integer.parseInt(arg.getValue().substring(1))) {
					acc -= a[Integer.parseInt(arg.getValue().substring(1))];
				}
			}
		}
	}
	
	private void mul() {
		Argument arg = program.getProgramLine(pc).getArgument(0);
		if (arg.isNumber()) {
			acc *= Integer.parseInt(arg.getValue());
		} else {
			if (arg.getValue().equals("acc")) {
				acc *= acc;
			} else if (arg.getValue().equals("adr")) {
				acc *= adr;
			} else if (arg.getValue().contains("a")) {
				if (16 > Integer.parseInt(arg.getValue().substring(1))) {
					acc *= a[Integer.parseInt(arg.getValue().substring(1))];
				}
			}
		}
	}
	
	private void div() {
		Argument arg = program.getProgramLine(pc).getArgument(0);
		if (arg.isNumber()) {
			acc /= Integer.parseInt(arg.getValue());
		} else {
			if (arg.getValue().equals("acc")) {
				acc /= acc;
			} else if (arg.getValue().equals("adr")) {
				acc /= adr;
			} else if (arg.getValue().contains("a")) {
				if (16 > Integer.parseInt(arg.getValue().substring(1))) {
					acc /= a[Integer.parseInt(arg.getValue().substring(1))];
				}
			}
		}
	}
	
	
	
	private void jmp() {
		int pc = getPcForJumps(program.getProgramLine(this.pc).getLabel());
		if (pc != -1) this.pc = pc;
	}
	
	private void jez() {
		int pc = getPcForJumps(program.getProgramLine(this.pc).getLabel());
		if (acc == 0) {
			if (pc != -1) this.pc = pc;
		}
	}
	
	private void jnz() {
		int pc = getPcForJumps(program.getProgramLine(this.pc).getLabel());
		if (acc != 0) {
			if (pc != -1) this.pc = pc;
		}
	}
	
	private void jgz() {
		int pc = getPcForJumps(program.getProgramLine(this.pc).getLabel());
		if (acc < 0) {
			if (pc != -1) this.pc = pc;
		}
	}
	
	private void jlz() {
		int pc = getPcForJumps(program.getProgramLine(this.pc).getLabel());
		if (acc > 0) {
			if (pc != -1) this.pc = pc;
		}
	}
	
	
	
	private void rpc() {
		acc = pc;
	}
	
	private void wpc() {
		pc = acc;
	}
	
	
	private void lda() {
		portA = acc;
	}
	
	private void sva() {
		acc = portA;
	}
	
	private void ldb() {
		portB = acc;
	}
	
	private void svb() {
		acc = portB;
	}
	
	
	
	private void clk() {
	
	}
	
	//useful items
	private int getPcForJumps(String s) {
		for (int i = 0; i < program.getSize(); i++) {
			if (program.getProgramLine(i).getLineType().equals(LABEL)) {
				if (program.getProgramLine(i).getLabel().equals(s)) return i;
			}
		}
		return -1;
	}
	
	public int Test(Program program, int line) {
		if (line < program.getSize()) {
			this.pc = line;
			this.program = program;
			
			runCommand();
			
			System.out.print(this.acc);
			System.out.print(" ");
			System.out.print(this.adr);
			System.out.print(" ");
			System.out.print(this.pc);
			System.out.println(" ");
			System.out.print(this.portA);
			System.out.print(" ");
			System.out.print(this.portB);
			System.out.println(" ");
			for (int temp: a){
				System.out.print(temp);
				System.out.print(" ");
			}
			System.out.println(System.lineSeparator());
		}
		return this.pc;
	}
	
}

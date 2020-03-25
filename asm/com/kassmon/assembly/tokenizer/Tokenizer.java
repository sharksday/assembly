package com.kassmon.assembly.tokenizer;

import java.util.regex.Pattern;

import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.program.CommandTypes;
import com.kassmon.assembly.program.ProgramLine;
import com.kassmon.library.log.EntryType;
import com.kassmon.library.tokenizers.Token;

import static com.kassmon.library.log.Log.newLogEntry;
import static com.kassmon.assembly.program.CommandTypes.*;

public class Tokenizer extends com.kassmon.library.tokenizers.Tokenizer {
	private final String path = "com.kassmon.projects.comp.tokenizer.Tokenizer";
	
	public Tokenizer() {
		for (CommandTypes obj: CommandTypes.values()){
			super.addPattern(Pattern.compile("^("+obj.toString().toLowerCase()+")"), "commands");
		}
		
		super.addPattern(Pattern.compile("^(acc)"), "mem");
		super.addPattern(Pattern.compile("^(adr)"), "mem");
		super.addPattern(Pattern.compile("^(pc)"), "mem");
		super.addPattern(Pattern.compile("^(null)"), "mem");
		super.addPattern(Pattern.compile("^(a[0-9][0-9])"), "mem");
		super.addPattern(Pattern.compile("^([0-9]+)"), "value");
		super.addPattern(Pattern.compile("^(![a-zA-Z]+)"), "label");
		super.addPattern(Pattern.compile("^([a-zA-Z]+)"), "label");
		
		//super.addPattern(Pattern.compile("^([])"), "");
		
	}
	
	public ProgramLine getNextLine(){
		CommandTypes commandType = null;
		Token t = getNextToken();
		if (t != null) {
			if (t.getType().equals("label")) {
				return new ProgramLine(t.getToken().substring(1));
			}
			if (t.getType().equals("commands")) {
				
				for (CommandTypes obj: CommandTypes.values()) {
					if (t.getToken().equals(obj.toString().toLowerCase())) commandType = obj;
				}
				
				switch (commandType) {
					case NOP:
						return nop();
					case MOV:
						return mov();
					case ADD:
						return add();
					case SUB:
						return sub();
					case MUL:
						return mul();
					case DIV:
						return div();
					case JMP:
						return jmp();
					case JEZ:
						return jez();
					case JNZ:
						return jnz();
					case JGZ:
						return jgz();
					case JLZ:
						return jlz();
					
					case RPC:
						return rpc();
					case WPC:
						return wpc();
					
					case LDA:
						return lda();
					case SVA:
						return sva();
					case LDB:
						return ldb();
					case SVB:
						return svb();
					
					case CLK:
						return clk();
				}
				
			}
		}
		return null;
	}
	
	private ProgramLine nop () {
		return new ProgramLine(NOP);
	}
	
	private ProgramLine mov() {
		Argument a1;
		Argument a2;
		Token t = getNextToken();
		if (t.getType().equals("mem")){
			a1 = new Argument(false, t.getToken());
		}else if (t.getType().equals("value")){
			a1 = new Argument(true, t.getToken());
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument for mov");
			return null;
		}
		t = getNextToken();
		if (t.getType().equals("mem")){
			a2 = new Argument(false, t.getToken());
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument for mov");
			return null;
		}
		return new ProgramLine(MOV, new Argument[]{a1, a2});
	}
	
	private ProgramLine add() {
		Argument a1;
		Token t = getNextToken();
		if (t.getType().equals("mem")){
			a1 = new Argument(false, t.getToken());
		}else if (t.getType().equals("value")){
			a1 = new Argument(true, t.getToken());
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument for add");
			return null;
		}
		return new ProgramLine(ADD, new Argument[]{a1});
	}
	
	private ProgramLine sub() {
		Argument a1;
		Token t = getNextToken();
		if (t.getType().equals("mem")){
			a1 = new Argument(false, t.getToken());
		}else if (t.getType().equals("value")){
			a1 = new Argument(true, t.getToken());
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument for add");
			return null;
		}
		return new ProgramLine(SUB, new Argument[]{a1});
	}
	
	private ProgramLine mul() {
		Argument a1;
		Token t = getNextToken();
		if (t.getType().equals("mem")){
			a1 = new Argument(false, t.getToken());
		}else if (t.getType().equals("value")){
			a1 = new Argument(true, t.getToken());
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument for add");
			return null;
		}
		return new ProgramLine(MUL, new Argument[]{a1});
	}
	
	private ProgramLine div() {
		Argument a1;
		Token t = getNextToken();
		if (t.getType().equals("mem")){
			a1 = new Argument(false, t.getToken());
		}else if (t.getType().equals("value")){
			a1 = new Argument(true, t.getToken());
		}else {
			newLogEntry(EntryType.ERROR, path, "not a valid argument for add");
			return null;
		}
		return new ProgramLine(DIV, new Argument[]{a1});
	}
	
	private ProgramLine jmp() {
		Token t = getNextToken();
		if (t.getType().equals("label")) return new ProgramLine(JMP, t.getToken());
		newLogEntry(EntryType.ERROR, path, "no valid label enterd for jmp");
		return null;
	}
	
	private ProgramLine jez() {
		Token t = getNextToken();
		if (t.getType().equals("label")) return new ProgramLine(JEZ, t.getToken());
		newLogEntry(EntryType.ERROR, path, "no valid label enterd for jez");
		return null;
	}
	
	private ProgramLine jgz() {
		Token t = getNextToken();
		if (t.getType().equals("label")) return new ProgramLine(JGZ, t.getToken());
		newLogEntry(EntryType.ERROR, path, "no valid label enterd for jgz");
		return null;
	}
	
	private ProgramLine jnz() {
		Token t = getNextToken();
		if (t.getType().equals("label")) return new ProgramLine(JNZ, t.getToken());
		newLogEntry(EntryType.ERROR, path, "no valid label enterd for jnz");
		return null;
	}
	
	private ProgramLine jlz() {
		Token t = getNextToken();
		if (t.getType().equals("label")) return new ProgramLine(JLZ, t.getToken());
		newLogEntry(EntryType.ERROR, path, "no valid label enterd for jlz");
		return null;
	}
	
	
	
	private ProgramLine rpc() {
		return new ProgramLine(RPC);
	}
	
	private ProgramLine wpc() {
		return new ProgramLine(WPC);
	}
	
	
	
	private ProgramLine lda() {
		return new ProgramLine(LDA);
	}
	
	private ProgramLine sva() {
		return new ProgramLine(SVA);
	}
	
	private ProgramLine ldb() {
		return new ProgramLine(LDB);
	}
	
	private ProgramLine svb() {
		return new ProgramLine(SVB);
	}
	
	private ProgramLine clk(){
		return new ProgramLine(CLK);
	}
	
	
	
	public Token getNextToken(){
		Token token = super.getNextToken();
		if (!token.getType().equals("null")) {
			return token;
		}else {
			newLogEntry(EntryType.WARNING, path, "no token given");
			return null;
		}
	}
	
}

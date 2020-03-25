package com.kassmon.assembly.program;

public enum CommandTypes {
	NOP,	//no operation
	MOV,	//move
	ADD,	//add
	SUB,	//sub
	MUL,	//mul
	DIV,	//div
	JMP,	//jump
	JEZ,	//jump if acc == 0
	JNZ,	//jump if acc != 0
	JGZ,	//jump if acc > 0
	JLZ,    //jump if acc < 0
	
	RPC,    //read the pc to acc
	WPC,    //wright acc to pc
	
	LDA,    //save acc to port a
	SVA,    //save port a to acc
	LDB,    //save acc to port b
	SVB,    //save port b to acc
	
	CLK	    //sends clock to buss
}
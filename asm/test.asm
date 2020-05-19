jmp start

!writingCommandToRam
mov 0 adr
psh a00
mov 1 adr
psh a01
clk
rsr

!readFormRam
mov 0 adr
psh 3
mov 1 adr
clk
pul
rsr

!ramWriteToMem
mov 0 adr
psh 2
mov 1 adr
psh a00
clk
rsr

!ramChangeRamAdr
mov 0 adr
psh 1
mov 1 adr
psh a00
clk
rsr

!ramReadData
mov 0 adr
psh 3
clk
mov 1 adr
pul
rsr

!start
mov 10 adr
mov adr a00
mov a00 acc
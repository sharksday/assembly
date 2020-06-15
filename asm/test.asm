jmp init
!sendToDisplay
mov 0x10 adr
psh a00
mov 0x11 adr
psh a01
clk
rsr
!init

!start
mov 0x0f acc
xor 0x01

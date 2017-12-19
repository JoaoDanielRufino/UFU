#Nome: Joao Daniel A. Rufino
#Matricula: 11621BCC033

.data
	Message: .asciiz "Entre com um numero natural: "
	ResultMessage: .asciiz "O fatorial do numero eh: "
	Number: .word 0
	Result: .word 0
	
.text

	li $v0, 4
	la $a0, Message
	syscall
	
	li $v0, 5
	syscall
	
	sw $v0, Number
	
	lw $a0, Number 
	jal factorial
	sw $v0, Result
	
	li $v0, 4
 	la $a0, ResultMessage
	syscall

	li $v0, 1
	lw $a0, Result
	syscall
	
	li $v0, 10
	syscall
	
	
factorial:
	subu $sp, $sp, 8
	sw $ra, ($sp)
	sw $s0, 4($sp)
	
	li $v0, 1
	beq $a0, 0, factDone
	
	move $s0, $a0
	sub $a0, $a0, 1
	jal factorial
	
	mul $v0, $s0, $v0
	
	factDone:
		lw $ra, ($sp)
		lw $s0, 4($sp)
		addu $sp, $sp, 8
		jr $ra
			

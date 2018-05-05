.data
	buffer: .space 50
	str: .ascii "Entre com uma string (max 50 chars): "

.text
	li $v0, 4
	la $a0, str
	syscall
	
	li $v0, 8 # Ler string
	la $a0, buffer # Carregar a string
	li $a1, 50 # Reparte o espaco
	syscall
	
	jal contaDigitos
	
	li $v0, 1
	move $a0, $t0	
	syscall
	
	li $v0, 10
	syscall
	
	contaDigitos:
		move $a1, $a0
		while:
			lb $t1, 0($a1)
			beqz $t1, exit
			addi $a1, $a1, 1 # Incrementando o ponteiro da string
			j while
			
		exit:
			sub $t0, $a1, $a0
			sub $t0, $t0, 1
			jr $ra
	

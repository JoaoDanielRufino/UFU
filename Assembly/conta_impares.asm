.data
	myArray: .word 1,2,3,4,5

.text
	la $a0, myArray
	li $a1, 5 # Tamanho do array
	
	jal impares
	
	li $v0, 1
	move $a0, $t3
	syscall
	
	li $v0, 10
	syscall

	impares:
		addi $t0, $zero, 0
		addi $t3, $zero, 0
		
		while:
			beq $t0, $a1, exit
			lw $t1, 0($a0)
			and $t2, $t1, 1
			add $t3, $t3, $t2
			addi $a0, $a0, 4
			addi $t0, $t0, 1
			j while
			
		exit:
			jr $ra
				

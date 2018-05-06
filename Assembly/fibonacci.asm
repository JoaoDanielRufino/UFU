.data
	Num: .asciiz "Digite a posicao desejada da sequencia de fibonacci: "
	
.text
	li $v0, 4
	la $a0, Num
	syscall
	
	li $v0, 5
	syscall
	
	move $a0, $v0 # Armazena o inteiro lido m $a0
	
	jal fib
	
	li $v0, 1
	move $a0, $t0
	syscall
	
	li $v0, 10
	syscall
	
	fib:
		li $t0, 0 # $t0 corresponde a variavel resposta, fib
		beqz $a0, exit
		
		addi $t0, $t0, 1
		beq $a0, $t0, exit
		
		addi $t1, $zero, 1 # i
		move $t2, $t1 # j
		
		sub $a0, $a0, 2 # n -= 2
		
		while:
			beqz $a0, exit
			add $t0, $t1, $t2 # fib = i + j
			move $t2, $t1 # j = i
			move $t1, $t0 # i = fib
			sub $a0, $a0, 1 # n--
			j while
			
		
	exit:
		jr $ra

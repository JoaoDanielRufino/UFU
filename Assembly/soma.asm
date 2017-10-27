.data
	Num1: .asciiz "Digite o primeiro numero: "
	Num2: .asciiz "Digite o segundo numero: "
	Result: .asciiz "Resultado: "

.text
	li $v0,4 #Impressao de string.
	la $a0,Num1 #Mostra a string de Num1.
	syscall
	
	li $v0,5 #Le um intreiro.
	syscall
	
	move $t0,$v0 #Move $v0 para $t0, para armazenar o valor lido.
	
	li $v0,4 #Impressao de string.
	la $a0,Num1 #Mostra a string de Num2.
	syscall
	
	li $v0,5 #Le um intreiro.
	syscall
	
	move $t1,$v0 #Move $v0 para $t1, para armazenar o valor lido.
	
	add $t2,$t0,$t1 #Soma o valor de $to com $t1.
	
	li $v0,4 #Impressao de string.
	la $a0,Result #Mostra a string de Result.
	syscall
	
	li $v0,1 #Impressao de inteiros.
	move $a0,$t2 #Move $t2 para $a0 para printar o resultado.
	syscall

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
	la $a0,Num2 #Mostra a string de Num2.
	syscall
	
	li $v0,5 #Le um intreiro.
	syscall
	
	move $t1,$v0 #Move $v0 para $t1, para armazenar o valor lido.
	
	mult $t0,$t1 #Multiplica o valor de $t0 com $t1.
	
	mflo $s3 #Numeros pequenos.
	
	li $v0,4 #Impressao de string.
	la $a0,Result #Mostra a string de Result.
	syscall
	
	li $v0,1 #Impressao de inteiros.
	move $a0,$s3 #Move $s3 para $a0 para printar o resultado.
	syscall
	
	li $v0,10 #Comando de exit.
	syscall

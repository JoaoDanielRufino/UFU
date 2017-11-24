#Nome: Joao Daniel A. Rufino
#Matricula: 11621BCC033

.data
	Frase: .asciiz "Digite um numero: "
	Espaco: .asciiz " "
	
.text

	li $t1, 2 #Valores para uso no decorrer do programa.
	li $t2, 3
	li $t3, 1

	li $v0, 4 
	la $a0, Frase #Imprime a frase.
	syscall
	
	li $v0, 5 #Le um inteiro.
	syscall
	
	move $t0, $v0 #Armazena o conteudo de $v0 em $t0.
	
loop:
	li $v0, 1
	move $a0, $t0 #Impressao de inteiro.
	syscall
	
	li $v0, 4
	la $a0, Espaco #Imprime espaco.
	syscall
	
	jal verificaNumero
	bgt $t0, $t3, loop #$t0 < $t3 ? sai do loop : loop
	
	li $v0, 1
	move $a0, $t0 #Impressao de inteiro.
	syscall
	
	li $v0, 10
	syscall #Exit.
	
verificaNumero:
	div $t0, $t1 
	mfhi $t4 #Guarda o resto da divisao por 2.
	beqz $t4, numPar #$t4 == 0 ? numPar : numImpar
	j numImpar
	
numPar:
	div $t0, $t0, $t1 #Armazena a divisao $t0/$t1.
	jr $ra #Retorna.
	
numImpar:
	mul $t0, $t0, $t2 #Armazena a multiplicacao $t0 * $t1.
	addi $t0, $t0, 1 #Armazena a soma $t0 + 1.
	jr $ra #Retorna.

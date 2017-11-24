#Nome: Joao Daniel A. Rufino
#Matricula: 11621BCC033

.data
	Frase: .asciiz "Digite um numero: "
	Erro: .asciiz "Erro, digite um numero maior que 0!!\n"
	Espaco: .asciiz " "
	
.text

leNatural:
	li $v0, 4 
	la $a0, Frase #Imprime a frase.
	syscall
			
	li $v0, 5 #Le um inteiro.
	syscall
	
	move $t0, $v0 #Armazena o conteudo de $v0 em $t0.
		
	bgtz $t0, loop #$t0 > 0 ? loop : leNatural
	
	li $v0, 4 
	la $a0, Erro #Imprime a frase.
	syscall
	
	j leNatural
	
loop:
	li $v0, 1
	move $a0, $t0 #Impressao de inteiro.
	syscall
	
	li $v0, 4
	la $a0, Espaco #Imprime espaco.
	syscall
	
	jal verificaNumero
	bgt $t0, 1, loop #$t0 > 1 ? loop : sai do loop
	
	li $v0, 1
	move $a0, $t0 #Impressao de inteiro.
	syscall
	
	li $v0, 10
	syscall #Exit.
	
verificaNumero:
	li $t1, 2
	div $t0, $t1 
	mfhi $t4 #Guarda o resto da divisao por 2.
	beqz $t4, numPar #$t4 == 0 ? numPar : numImpar
	j numImpar
	
numPar:
	div $t0, $t0, 2 #Armazena a divisao $t0/$t1.
	jr $ra #Retorna.
	
numImpar:
	mul $t0, $t0, 3 #Armazena a multiplicacao $t0 * 3.
	addi $t0, $t0, 1 #Armazena a soma $t0 + 1.
	jr $ra #Retorna.

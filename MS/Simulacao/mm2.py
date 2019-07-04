import random
import numpy as np
import math
import statistics as st

def roleta(a, b, media, distProb):
  r = np.random.uniform()
  if distProb == "uniforme":
    return round(a + (b-a)*r, 0)
  elif distProb == "exponencial":
    return round(-(1/(1/media))*math.log(1-r))

def geraMatriz(a, b, media, clientes, carac, distProb):
  tabelaSimulacao = [[0 for i in range(11)] for j in range(clientes)]
  finalServicoOP1, finalServicoOP2 = 0, 0
  for cliente in range(clientes):
    if carac == "aleatorio":
      tabelaSimulacao[cliente][0] = roleta(a, b, media, distProb) # Tempo desde a ultima chegada
    elif carac == "deterministico":
      tabelaSimulacao[cliente][0] = a # Tempo desde a ultima chegada

    if cliente == 0:
      tabelaSimulacao[cliente][1] = tabelaSimulacao[cliente][0] # Tempo de chegada no relogio
      tabelaSimulacao[cliente][3] = tabelaSimulacao[cliente][1] # Tempo de inicio do servico no relogio
      tabelaSimulacao[cliente][6] = tabelaSimulacao[cliente][1] # Tempo livre do operador1
      tabelaSimulacao[cliente][9] = tabelaSimulacao[cliente][1] # Tempo livre do operador2
    else:
      tabelaSimulacao[cliente][1] = tabelaSimulacao[cliente][0] + tabelaSimulacao[cliente-1][1] # Tempo de chegada no relogio
      if tabelaSimulacao[cliente][1] >= finalServicoOP1: # Checando se operador 1 livre
        tabelaSimulacao[cliente][3] = tabelaSimulacao[cliente][1] # Tempo de inicio do servico no relogio
      else:
        if tabelaSimulacao[cliente][1] >= finalServicoOP2: # Checando se operador 2 livre
          tabelaSimulacao[cliente][7] = tabelaSimulacao[cliente][1]
        else: # Procurando o operador com menor tempo de termino de servico
          if finalServicoOP1 <= finalServicoOP2:
            tabelaSimulacao[cliente][3] = finalServicoOP1 # Tempo de inicio do servico no relogio
          else:
            tabelaSimulacao[cliente][7] = finalServicoOP2

      if tabelaSimulacao[cliente][3] > 0: # Calculo do tempo livre dos operadores
        tabelaSimulacao[cliente][6] = tabelaSimulacao[cliente][3] - finalServicoOP1 # Tempo livre do operador1
      if tabelaSimulacao[cliente][7] > 0:
        if finalServicoOP2 == 0:
          tabelaSimulacao[cliente][9] = 0
        else:
          tabelaSimulacao[cliente][9] = tabelaSimulacao[cliente][7] - finalServicoOP2 # Tempo livre do operador2

    if carac == "aleatorio":
      tabelaSimulacao[cliente][2] = roleta(a, b, media, distProb) # Tempo do servico
    elif carac == "deterministico":
      tabelaSimulacao[cliente][2] = b # Tempo do servico    

    if tabelaSimulacao[cliente][3] > 0:
      tabelaSimulacao[cliente][5] = tabelaSimulacao[cliente][2] + tabelaSimulacao[cliente][3] # Tempo final do servico operador1
      finalServicoOP1 = tabelaSimulacao[cliente][5]
      tabelaSimulacao[cliente][4] = tabelaSimulacao[cliente][3] - tabelaSimulacao[cliente][1] # Tempo do cliente na fila
      tabelaSimulacao[cliente][10] = tabelaSimulacao[cliente][5] - tabelaSimulacao[cliente][1] # Tempo do cliente no sistema
    if tabelaSimulacao[cliente][7] > 0:
      tabelaSimulacao[cliente][8] = tabelaSimulacao[cliente][2] + tabelaSimulacao[cliente][7] # Tempo final do servico operador2
      finalServicoOP2 = tabelaSimulacao[cliente][8]
      tabelaSimulacao[cliente][4] = tabelaSimulacao[cliente][7] - tabelaSimulacao[cliente][1] # Tempo do cliente na fila
      tabelaSimulacao[cliente][10] = tabelaSimulacao[cliente][8] - tabelaSimulacao[cliente][1] # Tempo do cliente no sistema

    
    #print(tabelaSimulacao[cliente])

  return tabelaSimulacao

def geraEstatisticas(tabelaSimulacao):
  tempoEsperaFila, nroClientesEspera, tempoLivreOperador1, tempoLivreOperador2, tempoServico, tempoNoSistema = 0, 0, 0, 0, 0, 0
  nroClientes = len(tabelaSimulacao)
  for i in range(nroClientes):
    tempoEsperaFila += tabelaSimulacao[i][4]
    tempoLivreOperador1 += tabelaSimulacao[i][6]
    tempoLivreOperador2 += tabelaSimulacao[i][9]
    tempoServico += tabelaSimulacao[i][2]
    tempoNoSistema += tabelaSimulacao[i][10]
    if tabelaSimulacao[i][4] > 0:
      nroClientesEspera += 1

  tempoMedioEsperaFila = tempoEsperaFila / nroClientes
  probClienteFila = nroClientesEspera / nroClientes
  probOperador1Livre = tempoLivreOperador1 / (tabelaSimulacao[nroClientes-1][1] + tabelaSimulacao[nroClientes-1][2] + tabelaSimulacao[nroClientes-1][4])
  probOperador2Livre = tempoLivreOperador2 / (tabelaSimulacao[nroClientes-1][1] + tabelaSimulacao[nroClientes-1][2] + tabelaSimulacao[nroClientes-1][4])
  tempoMedioServico = tempoServico / nroClientes
  tempoMedioDespendido = tempoNoSistema / nroClientes

  return (tempoMedioEsperaFila, probClienteFila, probOperador1Livre, probOperador2Livre, tempoMedioServico, tempoMedioDespendido)

def printDados(tabelaSimulacao, estatisticas):
  print("Cliente\t\tTEC\t\tTECRelogio      TS           TS-I-OP1       TempoFila        TS-F-OP1   TL-OP1       TS-I-OP2         TS-F-OP2       TL-OP2      TempoClienteSistema")
  for i in range(len(tabelaSimulacao)):
    print(str(i+1) + "\t\t", end = '')
    for data in tabelaSimulacao[i]:
      print(str(int(data)) + "\t\t", end = '')
    print("")

  (tempoMedioEsperaFila, probClienteFila, probOperador1Livre, probOperador2Livre, tempoMedioServico, tempoMedioDespendido) = estatisticas

  print("\nTempo medio de espera na fila: " + str(tempoMedioEsperaFila))
  print("Probabilidade de um cliente esperar na fila: " + str(probClienteFila))
  print("Probabilidade do operador 1 livre: " + str(probOperador1Livre))
  print("Probabilidade do operador 2 livre: " + str(probOperador2Livre))
  print("Tempo medio de servico: " + str(tempoMedioServico))
  print("Tempo medio despendido no sistema: " + str(tempoMedioDespendido))

def realizaSimulacao(a, b, media, clientes, carac, distProb):
  numRep = 10
  tempoMedioEsperaFila, probClienteFila, probOperador1Livre, probOperador2Livre, tempoMedioServico, tempoMedioDespendido = [0]*numRep, [0]*numRep, [0]*numRep, [0]*numRep, [0]*numRep, [0]*numRep
  for i in range(numRep):
    tabelaSimulacao = geraMatriz(a, b, media, clientes, carac, distProb)
    (tempoMedioEsperaFila[i], probClienteFila[i], probOperador1Livre[i], probOperador2Livre[i], tempoMedioServico[i], tempoMedioDespendido[i]) = geraEstatisticas(tabelaSimulacao)
    printDados(tabelaSimulacao, geraEstatisticas(tabelaSimulacao))
    print("\n")

  print("Desvio padrao dos dados:")
  print("Tempo medio espera fila: " + str(st.stdev(tempoMedioEsperaFila)))
  print("Probabilidade de um cliente esperar na fila: " + str(st.stdev(probClienteFila)))
  print("Probabilidade do operador 1 livre: " + str(st.stdev(probOperador1Livre)))
  print("Probabilidade do operador 2 livre: " + str(st.stdev(probOperador2Livre)))
  print("Tempo medio de servico: " + str(st.stdev(tempoMedioServico)))
  print("Tempo medio despendido no sistema: " + str(st.stdev(tempoMedioDespendido)))

  print("\nMedia dos dados:")
  print("Tempo medio espera fila: " + str(st.mean(tempoMedioEsperaFila)))
  print("Probabilidade de um cliente esperar na fila: " + str(st.mean(probClienteFila)))
  print("Probabilidade do operador 1 livre: " + str(st.mean(probOperador1Livre)))
  print("Probabilidade do operador 2 livre: " + str(st.mean(probOperador2Livre)))
  print("Tempo medio de servico: " + str(st.mean(tempoMedioServico)))
  print("Tempo medio despendido no sistema: " + str(st.mean(tempoMedioDespendido)))

def main():
  clientes = int(input("Digite a quantidade de clientes: "))
  carac = input("Tempos de chegada e saida (aleatorio / deterministico)? ")
  if carac == "deterministico":
    tc = int(input("Digite o tempo de entrada: "))
    ts = int(input("Digite o tempo de saida: "))
    tabelaSimulacao = geraMatriz(tc, ts, None, clientes, carac, None) # Gerando matriz de simulacao
    printDados(tabelaSimulacao,geraEstatisticas(tabelaSimulacao))
  elif carac == "aleatorio":
    a, b, media = None, None, None
    distProb = input("Digite a distribuicao de probabilidade (uniforme / exponencial): ")
    if distProb == "uniforme":
      a = int(input("Digite o intervalo lower bound: "))
      b = int(input("Digite o intervalo upper bound: ")) 
    elif distProb == "exponencial":
      media = int(input("Digite a media: "))
    else:
      print('Digite os dados corretamente')
      return
    realizaSimulacao(a, b, media, clientes, carac, distProb)
  else:
    print("Digite os dados corretamente")


if __name__ == "__main__":
  main()

import random
import numpy as np
import math

def roletaChegada(a, b, distProb):
  if distProb == "uniforme":
    r = np.random.uniform()
    return round(a + (b-a)*r, 0)
  elif distProb == "exponencial":
    r = np.random.uniform()
    return round(-1*math.log(1-r))

def roletaSaida(a, b, distProb):
  if distProb == "uniforme":
    r = np.random.uniform()
    return round(a + (b-a)*r, 0)
  elif distProb == "exponencial":
    r = np.random.uniform()
    return round(-1*math.log(1-r))

def geraMatriz(a, b, clientes, carac, distProb):
  tabelaSimulacao = [[0 for i in range(8)] for j in range(clientes)]
  for cliente in range(clientes):
    if carac == "aleatorio":
      tabelaSimulacao[cliente][0] = roletaChegada(a, b, distProb) # Tempo desde a ultima chegada
    elif carac == "deterministico":
      tabelaSimulacao[cliente][0] = a # Tempo desde a ultima chegada

    if cliente == 0:
      tabelaSimulacao[cliente][1] = tabelaSimulacao[cliente][0] # Tempo de chegada no relogio
      tabelaSimulacao[cliente][3] = tabelaSimulacao[cliente][1] # Tempo de inicio do servico no relogio
      tabelaSimulacao[cliente][7] = tabelaSimulacao[cliente][1] # Tempo livre do operador
    else:
      tabelaSimulacao[cliente][1] = tabelaSimulacao[cliente][0] + tabelaSimulacao[cliente-1][1] # Tempo de chegada no relogio
      if tabelaSimulacao[cliente][1] >= tabelaSimulacao[cliente-1][5]:
        tabelaSimulacao[cliente][3] = tabelaSimulacao[cliente][1] # Tempo de inicio do servico no relogio
      else:
        tabelaSimulacao[cliente][3] = tabelaSimulacao[cliente-1][5] # Tempo de inicio do servico no relogio
      tabelaSimulacao[cliente][7] = tabelaSimulacao[cliente][3] - tabelaSimulacao[cliente-1][5] # Tempo livre do operador

    if carac == "aleatorio":
      tabelaSimulacao[cliente][2] = roletaSaida(a, b, distProb) # Tempo do servico
    elif carac == "deterministico":
      tabelaSimulacao[cliente][2] = b # Tempo do servico

    tabelaSimulacao[cliente][4] = tabelaSimulacao[cliente][3] - tabelaSimulacao[cliente][1] # Tempo do cliente na fila
    tabelaSimulacao[cliente][5] = tabelaSimulacao[cliente][2] + tabelaSimulacao[cliente][3] # Tempo final do servico no relogio
    tabelaSimulacao[cliente][6] = tabelaSimulacao[cliente][5] - tabelaSimulacao[cliente][1] # Tempo do cliente no sistema
    #print(tabelaSimulacao[cliente])

  return tabelaSimulacao

def geraEstatisticas(tabelaSimulacao):
  tempoEsperaFila, nroClientesEspera, tempoLivreOperador, tempoServico, tempoNoSistema = 0, 0, 0, 0, 0
  nroClientes = len(tabelaSimulacao)
  for i in range(nroClientes):
    tempoEsperaFila += tabelaSimulacao[i][4]
    tempoLivreOperador += tabelaSimulacao[i][7]
    tempoServico += tabelaSimulacao[i][2]
    tempoNoSistema += tabelaSimulacao[i][6]
    if tabelaSimulacao[i][4] > 0:
      nroClientesEspera += 1

  tempoMedioEsperaFila = tempoEsperaFila / nroClientes
  probClienteFila = nroClientesEspera / nroClientes
  probOperadorLivre = tempoLivreOperador / tabelaSimulacao[nroClientes-1][5]
  tempoMedioServico = tempoServico / nroClientes
  tempoMedioDespendido = tempoNoSistema / nroClientes

  return (tempoMedioEsperaFila, probClienteFila, probOperadorLivre, tempoMedioServico, tempoMedioDespendido)

def printDados(tabelaSimulacao, estatisticas):
  print("Cliente\t\tTEC\t\tTECRelogio      TS        TSRelogio      TempoFila   TempoFinalServico  TempoClienteSistema  TempoLivreOperador")
  for i in range(len(tabelaSimulacao)):
    print(str(i+1) + "\t\t", end = '')
    for data in tabelaSimulacao[i]:
      print(str(data) + "\t\t", end = '')
    print("")

  (tempoMedioEsperaFila, probClienteFila, probOperadorLivre, tempoMedioServico, tempoMedioDespendido) = estatisticas

  print("\nTempo medio de espera na fila: " + str(tempoMedioEsperaFila))
  print("Probabilidade de um cliente esperar na fila: " + str(probClienteFila))
  print("Probabilidade do operador livre: " + str(probOperadorLivre))
  print("Tempo medio de servico: " + str(tempoMedioServico))
  print("Tempo medio despendido no sistema: " + str(tempoMedioDespendido))

def main():

  clientes = int(input("Digite a quantidade de clientes: "))
  carac = input("Tempos de chegada e saida (aleatorio / deterministico)? ")
  if carac == "deterministico":
    tc = int(input("Digite o tempo de entrada: "))
    ts = int(input("Digite o tempo de saida: "))
    tabelaSimulacao = geraMatriz(tc, ts, clientes, carac, None) # Gerando matriz de simulacao
  elif carac == "aleatorio":
    distProb = input("Digite a distribuicao de probabilidade (uniforme / exponencial): ")
    a = int(input("Digite o intervalo lower bound: "))
    b = int(input("Digite o intervalo upper bound: "))
    tabelaSimulacao = geraMatriz(a, b, clientes, carac, distProb) # Gerando matriz de simulacao
  else:
    input("Digite os dados corretamente")

  printDados(tabelaSimulacao, geraEstatisticas(tabelaSimulacao))


if __name__ == "__main__":
  main()

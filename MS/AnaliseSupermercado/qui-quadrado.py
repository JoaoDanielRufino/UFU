from scipy.stats import chisquare

f = open("supermercado.txt", "r")

data = []
for x in f.read().split():
  data.append(int(x))

# Removendo o outlier
data.remove(728)

sum = 0
for x in data:
  sum += x

# A media na distribuicao exponencial eh 1/lambda
media = sum / len(data)
la = 1/media # lambda

# Utilizando os valores de Pk calculados pelo livro, pois nao
# encontrei como calcular a integral da funcao de densidade de probabilidade
pk = [0.5022, 0.25, 0.1244, 0.0620, 0.0308, 0.0154, 0.0076, 0.0038, 0.0038]

# Para calcular o Tk temos que, Tk = {Total de observacoes}*Pk
tk = []
for x in pk:
  tk.append(round(len(data) * x))

# Como as clases 6, 7, 8, e 9 tem frequencias teoricas(Tk) menores do que 5,
# devem ser agrupadas com a quinta classe
tmp = 0
for x in range(5, len(tk)):
  tk[4] += tk[x]

del tk[5:]

print("Tk: ", tk)

# Para o a frequencia observada(Ok), deve-se tambem agrupar as classes 6, 7, 8, e 9
ok = [0]*5
for x in data:
  if x < 4.8:
    ok[0] += 1
  elif x >= 4.8 and x < 9.6:
    ok[1] += 1
  elif x >= 9.6 and x < 14.3:
    ok[2] += 1
  elif x >= 14.3 and x < 19.1:
    ok[3] += 1
  elif x >= 19.1:
    ok[4] += 1

print("Ok: ", ok)

(st, pv) = chisquare(ok, tk)

print("Teste qui-quadrado: ", st)
print("Pvalor: ", pv)
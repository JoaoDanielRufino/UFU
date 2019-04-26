import math

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

# Calculando a frequencia observada
freqObs = [0]*44
for x in data:
  freqObs[x] += 1

# Realizando o teste Kolmogorov-Smirnov
frqAc, sx, fesq, fdir, desq, ddir = 0, 0, 0, 0, 0, 0
maior = -1
for x, i in zip(freqObs, range(1, 45)):
  frqAc += x
  sx = round(frqAc / len(data), 3)
  fesq = fdir
  fdir = round(1 - math.exp(-la * i), 3)
  desq = round(abs(fesq - sx), 3)
  ddir = round(abs(fdir - sx), 3)
  maior = max(maior, max(desq, ddir))
  print("Frq.Ac: " + str(frqAc) + "  S(x): " + str(sx) + "  Fesq: " + str(fesq) 
  + "  Fdir: " + str(fdir) + "  Desq: " + str(desq) + "  Ddir: " + str(ddir) + "  D: " + str(max(desq, ddir)))

print("Dmax: " + str(maior))

dcritico = 1.36 / math.sqrt(len(data))
print("Dcritico: " + str(dcritico))
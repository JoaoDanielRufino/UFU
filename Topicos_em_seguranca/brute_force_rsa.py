import sys
import time

def getParams(x):
  if x == 1:
    return (79, 1325147, 172534) # e, n, c
  elif x == 2:
    return (351047, 13339787, 3842393)
  elif x == 3:
    return (11, 87411743, 48965079)
  elif x == 4:
    return (365, 153988391, 100659095)
  elif x == 5:
    return (80105, 642281891, 632206883)
  elif x == 6:
    return (80273, 3662937263, 2888533502)
  elif x == 7:
    return (78703, 2461987247, 589004655)
  elif x == 8:
    return (118297, 10988963221, 7937786621)
  elif x == 9:
    return (9764819, 9979645019, 9849187543)
  elif x == 10:
    return (89, 36207914857, 10054591300)


(e, n, c) = getParams(int(sys.argv[1]))

start_time = time.time()

p = 1
while True:
  res = (p**e)%n

  if res == c:
    print(f'Plain text: {p}')
    print(f'Tempo execucao: {time.time() - start_time} segundos')
    break

  if p >= n:
    print('Nao existe resposta')
    break
  
  p += 1

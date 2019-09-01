import sys
import hashlib
import random
import string
import matplotlib.pyplot as plt

def generateRandomString(size):
  letters = string.ascii_letters
  return ''.join(random.choice(letters) for _ in range(size))

def getAverageIterationsBirthParadox(verifyRange, rep):
  average = 0
  for _ in range(rep):
    dic = {}
    iterations = 0
    while True:
      randomString = generateRandomString(15)
      hs = hashlib.sha1(randomString.encode('utf-8')).hexdigest()
      sub_hs = hs[:verifyRange]

      if sub_hs in dic:
        print(f'Colisao na iteracao: {iterations}')
        print(f'String da iteracao: {randomString}')
        print(f'String armazenada no hash: {dic[sub_hs]}')
        print(f'Hash obtido: {hs}')
        average += iterations/rep
        break
      
      dic[sub_hs] = randomString
      iterations += 1

  return average

def getAverageIterationsNaive(verifyRange, rep):
  average = 0
  for _ in range(rep):
    iterations = 0
    while True:
      randomString1 = generateRandomString(15)
      randomString2 = generateRandomString(15)
      hs1 = hashlib.sha1(randomString1.encode('utf-8')).hexdigest()
      hs2 = hashlib.sha1(randomString2.encode('utf-8')).hexdigest()
      sub_hs1 = hs1[:verifyRange]
      sub_hs2 = hs2[:verifyRange]

      if sub_hs1 == sub_hs2:
        print(f'Colisao na iteracao: {iterations}')
        print(f'String 1: {randomString1}')
        print(f'String 2: {randomString2}')
        print(f'Hash obtido: {hs1}')
        average += iterations/rep
        break
      
      iterations += 1

  return average

def main():
  resParadox, resNaive = [], []
  rangeOfVerifyRange = int(sys.argv[1]) # Quantidade de digitos a serem verificados (de 1 ate o valor informado)
  repetitions = int(sys.argv[2]) # O numero de repeticoes para o calculo da media

  for verifyRange in range(1, rangeOfVerifyRange+1):
    resParadox.append(getAverageIterationsBirthParadox(verifyRange, repetitions))
    resNaive.append(getAverageIterationsNaive(verifyRange, repetitions))

  xAxis = [i for i in range(1, rangeOfVerifyRange+1)]

  plt.ylabel('Media iteracoes para colisao')
  plt.xlabel('Quantidade de digitos iguas')
  plt.plot(xAxis, resParadox, 'ro', xAxis, resNaive, 'bs') # O vermelho eh usando o paradoxo, o azul o ingenuo
  plt.show()


if __name__ == '__main__':
  main()

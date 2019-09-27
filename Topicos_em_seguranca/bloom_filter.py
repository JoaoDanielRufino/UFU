import math
import mmh3
from bitarray import bitarray
import random
import time

class BloomFilter(object):
  def __init__(self, qty_items, prob):
    self.prop = prob
    self.size = self.getSize(qty_items, prob)
    self.qty_hash = self.getQtyHash(self.size, qty_items)
    self.bitarray = bitarray(self.size)
    self.bitarray.setall(0)

  def insert(self, item):
    for i in range(self.qty_hash):
      t = mmh3.hash(bytes(item), i) % self.size
      self.bitarray[t] = True

  def check(self, item):
    for i in range(self.qty_hash):
      t = mmh3.hash(bytes(item), i) % self.size
      if self.bitarray[t] == False:
        return False
    
    return True

  @classmethod
  def getSize(self, qty_items, prob): # Formula to calculate the size of bitarray
    m = -(qty_items * math.log(prob)) / (math.log(2)**2) 
    return int(m)

  @classmethod
  def getQtyHash(self, size, qty_items):
    k = (size/qty_items) * math.log(2)
    return int(k)


def generateRandomList(size, a, b):
  return random.sample(range(a, b), size)

def linearSearch(item, myList):
  for number in myList:
    if item == number:
      return True
  return False

def binarySearch(item, myList, l, r):
  if l > r:
    return False

  mid = int((l + r) / 2)
  if item > myList[mid]:
    return binarySearch(item, myList, mid+1, r)
  elif item < myList[mid]:
    return binarySearch(item, myList, l, mid-1)
  else:
    return True

if __name__ == '__main__':
  n = 1600
  p = 0.05
  bloomFilter = BloomFilter(n, p)
  print(f'Number of entries: {n}')
  print(f'Probability: {p}')
  print(f'Number of hash functions: {bloomFilter.qty_hash}')

  randomNumbersPresent = generateRandomList(n, 1, 1605)
  randomNumbersNotPresent = generateRandomList(n, 1610, 4000)

  # print('Random Numbers Present: ', randomNumbersPresent)
  # print('Random Numbers not present: ', randomNumbersNotPresent)
  
  for number in randomNumbersPresent:
    bloomFilter.insert(number)

  testNumbers = randomNumbersPresent + randomNumbersNotPresent

  # print('Test numbers: ', testNumbers)

  # for number in testNumbers:
  #   if bloomFilter.check(number):
  #     if number in randomNumbersNotPresent:
  #       print(f'{number} is a false positive')
  #     else:
  #       print(f'{number} is probably present')
  #   else:
  #     print(f'{number} is definitely not present')

  start_time = time.time()
  for number in testNumbers:
    bloomFilter.check(number)

  print('Bloom Filter execution time: ', (time.time() - start_time), 'seconds')

  start_time = time.time()
  for number in testNumbers:
    linearSearch(number, testNumbers)

  print('Linear search time: ', (time.time() - start_time), 'seconds')

  testNumbers.sort()
  start_time = time.time()
  for number in testNumbers:
    binarySearch(number, testNumbers, 0, len(testNumbers)-1)

  print('Binary search time: ', (time.time() - start_time), 'seconds')
  

import matplotlib.pyplot as plt

f = open("supermercado.txt", "r")

data = []
for x in f.read().split():
  data.append(int(x))

# Removendo o outlier
data.remove(728)

# (x1,x2); (x2,x3); (x3,x4);... (xn-1, xn)
plt.scatter(data[:len(data)-1], data[1:])

plt.show()
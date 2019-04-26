import matplotlib.pyplot as plt

f = open("supermercado.txt", "r")

data = []
for x in f.read().split():
  data.append(int(x))

# Removendo o outlier
data.remove(728)

plt.hist(data)
plt.ylabel("Frequencia")
plt.xlabel("Classes")
plt.show()